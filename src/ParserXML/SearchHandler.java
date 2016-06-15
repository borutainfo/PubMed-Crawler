package ParserXML;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SearchHandler extends DefaultHandler {
    private boolean bIdList = false;
    private boolean bId = false;
    private boolean bCount = false;
    private boolean bRetMax = false;
    private boolean bRetStart = false;
    private boolean bStats = false;
    
    private int id = 0;
    private int count = 0;
    private int retmax = 0;
    private int retstart = 0;
    
    private String idList;
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("eSearchResult")) {
            bStats = true;
        }
        else if (qName.equalsIgnoreCase("IdList")) {
            idList = "";
            bIdList = true;
            bStats = false;
        }
        else if (qName.equalsIgnoreCase("Id")) {
            bId = true;
        }
        else if (qName.equalsIgnoreCase("Count")) {
            bCount = true;
        } 
        else if (qName.equalsIgnoreCase("RetMax")) {
            bRetMax = true;
        }
        else if (qName.equalsIgnoreCase("RetStart")) {
            bRetStart = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("IdList")) {
            bIdList = false;
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (bIdList && bId) {
            id = Integer.parseInt(new String(ch, start, length));
            idList += ","+Integer.toString(id);
            bId = false;
        }
        else if (bStats && bCount) {
           count = Integer.parseInt(new String(ch, start, length));
           bCount = false;
        }
        else if (bStats && bRetMax) {
           retmax = Integer.parseInt(new String(ch, start, length));
           bRetMax = false;
        }
        else if (bStats && bRetStart) {
           retstart = Integer.parseInt(new String(ch, start, length));
           bRetStart = false;
        }
    }
    
    public int getCount() {
        return count;
    }
    
    public int getLeftCount() {
        return count - (retstart + retmax);
    }
    
    public String getIdList() {
        return idList.substring(1);
    }
}