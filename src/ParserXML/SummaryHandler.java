package ParserXML;

import SQLite.DB;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SummaryHandler extends DefaultHandler {
    private final DB db;
    private boolean bDoc = false;
    private boolean bId = false;
    private boolean bTitle = false;
    
    private final int expressionId;
    private int id;
    private String title;
    
    public SummaryHandler(DB db, int expressionId) {
        this.db = db;
        this.expressionId = expressionId;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("DocSum")) {
            id = 0;
            title = "";
            bDoc = true;
        } else if (qName.equalsIgnoreCase("Id")) {
            bId = true;
        } else if (qName.equalsIgnoreCase("Item")) {
            if(attributes.getValue("Name").equalsIgnoreCase("Title")) {
                bTitle = true;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("DocSum")) {
            bDoc = false;
            if(id != 0 && !"".equals(title))
                db.addSummary(id, expressionId, title);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (bDoc && bId) {
            id = Integer.parseInt(new String(ch, start, length));
            bId = false;
        }
        if (bDoc && bTitle) {
            title = new String(ch, start, length);
            bTitle = false;
        }
    }
}
