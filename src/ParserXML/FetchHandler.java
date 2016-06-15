package ParserXML;

import Structs.Summary;
import Lucene.Lucene;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class FetchHandler extends DefaultHandler {
    private final Lucene index;
    private Summary summary;

    private boolean bArticle = false;
    private boolean bId = false;
    private boolean bTitle = false;
    
    private boolean bAbstract = false;
    private boolean bAbstractText = false;
    
    private boolean bAuthorList = false;
    private boolean bAuthor = false;
    private boolean bLastName = false;
    private boolean bForeName = false;
    
    private boolean bMeshHeadingList = false;
    private boolean bMeshHeading = false;
    private boolean bDescriptorName = false;
    
    private boolean bDateRevised = false;
    private boolean bYear = false;
    private boolean bMonth = false;
    private boolean bDay = false;
    
    private boolean bJournal = false;
    private boolean bISSN = false;
    private boolean bISOAbbreviation = false;
    
    private String tempAuthor;

    public FetchHandler(Lucene index) {
        this.index = index;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("PubmedArticle")) {
            this.summary = new Summary();
            bArticle = true;
        } else if (qName.equalsIgnoreCase("PMID")) {
            bId = true;
        } else if (qName.equalsIgnoreCase("ArticleTitle")) {
            bTitle = true;
        } else if (qName.equalsIgnoreCase("Abstract")) {
            bAbstract = true;
        } else if (qName.equalsIgnoreCase("AbstractText")) {
            bAbstractText = true;
        } else if (qName.equalsIgnoreCase("AuthorList")) {
            bAuthorList = true;
        } else if (qName.equalsIgnoreCase("Author")) {
            bAuthor = true;
            tempAuthor = "";
        } else if (qName.equalsIgnoreCase("LastName")) {
            bLastName = true;
        } else if (qName.equalsIgnoreCase("ForeName")) {
            bForeName = true;
        } else if (qName.equalsIgnoreCase("MeshHeadingList")) {
            bMeshHeadingList = true;
        } else if (qName.equalsIgnoreCase("MeshHeading")) {
            bMeshHeading = true;
        } else if (qName.equalsIgnoreCase("DescriptorName")) {
            bDescriptorName = true;
        } else if (qName.equalsIgnoreCase("DateCreated")) {
            bDateRevised = true;
        } else if (qName.equalsIgnoreCase("Year")) {
            bYear = true;
        } else if (qName.equalsIgnoreCase("Month")) {
            bMonth = true;
        } else if (qName.equalsIgnoreCase("Day")) {
            bDay = true;
        } else if (qName.equalsIgnoreCase("Journal")) {
            bJournal = true;
        } else if (qName.equalsIgnoreCase("ISSN")) {
            bISSN = true;
        } else if (qName.equalsIgnoreCase("ISOAbbreviation")) {
            bISOAbbreviation = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("PubmedArticle")) {
            bArticle = false;
            if (this.summary.id != 0 && !"".equals(this.summary.title)) {
                this.index.addSummary(this.summary);
            }
        } else if (qName.equalsIgnoreCase("Abstract")) {
            bAbstract = false;
        } else if (qName.equalsIgnoreCase("AuthorList")) {
            bAuthorList = false;
        } else if (qName.equalsIgnoreCase("Author")) {
            bAuthor = false;
            this.summary.author.add(tempAuthor);
        } else if (qName.equalsIgnoreCase("MeshHeadingList")) {
            bMeshHeadingList = false;
        } else if (qName.equalsIgnoreCase("MeshHeading")) {
            bMeshHeading = false;
        } else if (qName.equalsIgnoreCase("DateCreated")) {
            bDateRevised = false;
        } else if (qName.equalsIgnoreCase("Journal")) {
            bJournal = false;
        } 
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (bArticle) {
            if (bId) {
                this.summary.id = Integer.parseInt(new String(ch, start, length));
                bId = false;
            } else if (bTitle) {
                this.summary.title = new String(ch, start, length);
                bTitle = false;
            } else if (bAbstract && bAbstractText) {
                this.summary.text.add(new String(ch, start, length));
                bAbstractText = false;
            } else if (bAuthorList && bAuthor && bLastName) {
                tempAuthor = new String(ch, start, length);
                bLastName = false;
            } else if (bAuthorList && bAuthor && bForeName) {
                tempAuthor = new String(ch, start, length) + " " + tempAuthor;
                bForeName = false;
            } else if (bMeshHeadingList && bMeshHeading && bDescriptorName) {
                this.summary.mesh.add(new String(ch, start, length));
                bDescriptorName = false;
            } else if (bDateRevised && bYear) {
                this.summary.date = new String(ch, start, length);
                bYear = false;
            } else if (bDateRevised && bMonth) {
                this.summary.date = ((new String(ch, start, length).length()<=1)?"0"+(new String(ch, start, length)):new String(ch, start, length)) + "/" + this.summary.date;
                bMonth = false;
            } else if (bDateRevised && bDay) {
                this.summary.date = ((new String(ch, start, length).length()<=1)?"0"+(new String(ch, start, length)):new String(ch, start, length)) + "/" + this.summary.date;
                bDay = false;
            } else if (bJournal && bISSN) {
                this.summary.issn = new String(ch, start, length);
                bISSN = false;
            } else if (bJournal && bISOAbbreviation) {
                this.summary.journal = new String(ch, start, length);
                bISOAbbreviation = false;
            }
        }
    }
}
