package ParserXML;

import SQLite.DB;
import Lucene.Lucene;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

public class PubMed {

    private final DB db;
    private XMLReader xml;

    private String searchURL = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&term=";
    private String fetchURL = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&retmode=xml&id=";

    public PubMed(DB db) {
        this.db = db;
        try {
            xml = XMLReaderFactory.createXMLReader();
        } catch (SAXException ex) {
            Logger.getLogger(PubMed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getResultSize(String expression) {
        SearchHandler search = new SearchHandler();
        try {
            String url = searchURL + expression + "&retmax=1";
            xml.setContentHandler(search);
            xml.parse(new InputSource(new URL(url).openStream()));
        } catch (IOException | SAXException ex) {
            Logger.getLogger(PubMed.class.getName()).log(Level.SEVERE, null, ex);
        }
        return search.getCount();
    }

    public int getResults(String expression, int retstart, int range) {
        String url;
        Lucene index = new Lucene();
        SearchHandler search = new SearchHandler();
        FetchHandler fetch = new FetchHandler(index);
        try {
            /* get the search result (summaries id) */
            url = searchURL + expression + "&retmax=" + Integer.toString(range) + "&retstart=" + Integer.toString(retstart);
            xml.setContentHandler(search);
            xml.parse(new InputSource(new URL(url).openStream()));
            /* get the summaries data */
            url = fetchURL + search.getIdList();
            xml.setContentHandler(fetch);
            xml.parse(new InputSource(new URL(url).openStream()));
        } catch (IOException | SAXException ex) {
            Logger.getLogger(PubMed.class.getName()).log(Level.SEVERE, null, ex);
        }
        index.close();
        return search.getLeftCount();
    }
}
