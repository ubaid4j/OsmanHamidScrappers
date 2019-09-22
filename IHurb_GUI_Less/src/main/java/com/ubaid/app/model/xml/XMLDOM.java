package com.ubaid.app.model.xml;



import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLDOM extends XML implements IxmlInsertion
{

	
	public XMLDOM()
	{
		String userPath = System.getProperty("user.home");
		String filePath = userPath + "\\" + "clothing.xml";
		createFile(filePath);
		currentDoc = getDoc();

	}

	@Override
	public void setName(String name, int recordID)
	{
		Element record = getRecord(recordID);
		NodeList names = record.getElementsByTagName(Ixml.PRODUCT_NAME);
		names.item(0).setTextContent(name);
		appendResult(currentDoc);		
	}

	@Override
	public void setNameInEnglish(String name, int recordID)
	{
		Element record = getRecord(recordID);
		NodeList names = record.getElementsByTagName(Ixml.PRODUCT_NAME_IN_ENGLISH);
		names.item(0).setTextContent(name);
		appendResult(currentDoc);				
	}

	@Override
	public void setProductLink(String productLink, int recordID)
	{
		Element record = getRecord(recordID);
		NodeList names = record.getElementsByTagName(Ixml.PRODUCT_LINK);
		names.item(0).setTextContent(productLink);
		appendResult(currentDoc);		
		
	}

	@Override
	public void setImageLink(String link, int recordID)
	{
		Element record = getRecord(recordID);
		NodeList names = record.getElementsByTagName(Ixml.IMAGE_LINK);
		names.item(0).setTextContent(IMAGE_LINK);
		appendResult(currentDoc);		
		
	}

	@Override
	public void setPrice(String price, int recordID)
	{
		Element record = getRecord(recordID);
		NodeList names = record.getElementsByTagName(Ixml.PRODUCT_PRICE);
		names.item(0).setTextContent(price);
		appendResult(currentDoc);		

	}

	@Override
	public void setPageNumber(int number, int recordID)
	{
		Element record = getRecord(recordID);
		NodeList names = record.getElementsByTagName(Ixml.PAGENUMBER);
		names.item(0).setTextContent(Integer.toString(number));
		appendResult(currentDoc);		
	}


}
