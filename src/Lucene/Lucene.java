package Lucene;

import Structs.Results;
import Structs.Summary;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class Lucene {

    private Path path;
    private Directory index;
    private StandardAnalyzer analyzer;
    private IndexWriterConfig config;
    private IndexWriter writer;

    private int size = 5;
    private boolean writeMode = true;

    public Lucene() {
        this(true);
    }

    public Lucene(boolean writeMode) {
        try {
            this.writeMode = writeMode;
            this.path = Paths.get("lucene_data");
            this.index = FSDirectory.open(this.path);
            this.analyzer = new StandardAnalyzer();
            if (writeMode) {
                this.config = new IndexWriterConfig(this.analyzer);
                this.writer = new IndexWriter(this.index, this.config);
            }
        } catch (IOException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            if(writeMode) {
                this.writer.close();
                if(new File("lucene_data/write.lock").exists())
                    new File("lucene_data/write.lock").delete();
            }
            this.analyzer.close();
            this.index.close();
        } catch (IOException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addSummary(Summary summary) {
        try {
            Document doc = new Document();
            doc.add(new StringField("id", Integer.toString(summary.id), Field.Store.YES));
            doc.add(new TextField("title", summary.title, Field.Store.YES));
            doc.add(new TextField("issn", summary.issn, Field.Store.YES));
            doc.add(new TextField("journal", summary.journal, Field.Store.YES));
            doc.add(new TextField("date", summary.date, Field.Store.YES));

            summary.text.stream().forEach((text) -> {
                doc.add(new TextField("abstract", text, Field.Store.YES));
            });
            summary.author.stream().forEach((author) -> {
                doc.add(new TextField("author", author, Field.Store.YES));
            });
            summary.mesh.stream().forEach((mesh) -> {
                doc.add(new TextField("mesh", mesh, Field.Store.YES));
            });
            this.writer.updateDocument(new Term("id", Integer.toString(summary.id)), doc);
        } catch (IOException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Results search(String term) {
        return this.search(term, 1, this.size);
    }

    public Results search(String term, int page) {
        return this.search(term, page, this.size);
    }

    public Results search(String term, int page, int size) {
        Results results = new Results();
        try {
            IndexReader reader = DirectoryReader.open(this.index);
            Query q = new QueryParser("abstract", this.analyzer).parse(term);
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs docs;
            ScoreDoc[] hits;
            if (page > 1) {
                docs = searcher.search(q, size * page);
                hits = docs.scoreDocs;
                docs = searcher.searchAfter(hits[hits.length - 1], q, size);
                hits = docs.scoreDocs;
            } else {
                docs = searcher.search(q, size);
                hits = docs.scoreDocs;
            }
            results.size = hits.length;
            if (results.size > 0) {
                results.totalSize = docs.totalHits;
                results.page = page;
                results.sizeForPage = size;
                //System.out.println("Found " + hits.length + " hits.");
                for (int i = 0; i < hits.length; ++i) {
                    int docId = hits[i].doc;
                    Document d = searcher.doc(docId);
                    Summary sum = new Summary();
                    sum.id = Integer.parseInt(d.get("id"));
                    sum.title = d.get("title");
                    sum.journal = d.get("journal");
                    sum.issn = d.get("issn");
                    sum.date = d.get("date");
                    sum.text = new ArrayList<>(Arrays.asList(d.getValues("abstract")));
                    sum.mesh = new ArrayList<>(Arrays.asList(d.getValues("mesh")));
                    sum.author = new ArrayList<>(Arrays.asList(d.getValues("author")));
                    results.summary.add(sum);
                    //System.out.println((i + 1) + ". " + d.get("title") + "\t" + d.get("id"));
                }
            } else if(page > 1)
                results = search(term, page -1);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }
    
    public long getTotalSize() {
        try {
            IndexReader reader = DirectoryReader.open(this.index);
            return reader.maxDoc();
        } catch (IOException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
