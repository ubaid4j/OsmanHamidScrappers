package com.ubaid.app.model.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class XML implements Ixml
{
	
	
	
	File file;
	Document currentDoc;
	
	public XML()
	{
		
	}

	@Override
	public boolean createFile(String filePath)
	{
		try
		{
			
			file = new File(filePath);
			if(!file.exists())
			{
				file.createNewFile();
				currentDoc = getDoc();
				Element rootElement = currentDoc.createElement(Ixml.RECORDS);
				currentDoc.appendChild(rootElement);
				appendResult(currentDoc);
				System.out.println(file.getAbsolutePath() + " Created");
				return true;
			}
			return false;
		}
		catch(NullPointerException exp)
		{
			exp.printStackTrace();
			return false;
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean createStructure()
	{
		return false;
	}
	
	boolean appendResult(Document doc)
	{
		try
		{
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
			return true;
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return false;
		}
	}

	
	Document getDoc()
	{
		DocumentBuilder builder = null;
		
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			return doc;
			
		}
		catch(ParserConfigurationException exp)
		{
			exp.printStackTrace();
			return null;
		}
		catch(SAXException exp)
		{
//			exp.printStackTrace();
			return builder.newDocument();
		}
		catch(IOException exp)
		{
			exp.printStackTrace();
			return null;
		}
		catch(Exception exp)
		{
			return null;
		}
		
	}
	
	private Element getRootElement()
	{
		try
		{
			return currentDoc.getDocumentElement();
		}
		catch(Exception exp)
		{
			return null;
		}
	}
	
	@Override
	public int getLastRecordID()
	{
		try
		{
			NodeList tests = getRootElement().getElementsByTagName(Ixml.RECORD);
			int max = 0;
			
			for(int i = 0; i < tests.getLength(); i++)
			{
				Node node = tests.item(i);
				
				if(node.getNodeType() != Node.TEXT_NODE)
				{
					Element element = (Element) node;
					String _id = element.getAttribute(Ixml.ID);
					int id = Integer.parseInt(_id);
					if(id > max)
						max = id;
				}
			}
			
			return max;
		}
		catch(Exception exp)
		{
			return -1;
		}
	}

	@Override
	public int createRecord()
	{
		try
		{
			Document doc = currentDoc;
			doc.normalize();
			Element record = doc.createElement(Ixml.RECORD);
			record.setAttribute(Ixml.ID, Integer.toString(getLastRecordID() + 1));
			Element name = doc.createElement(Ixml.PRODUCT_NAME);
			Element name_english = doc.createElement(Ixml.PRODUCT_NAME_IN_ENGLISH);
			Element product_link = doc.createElement(Ixml.PRODUCT_LINK);
			Element image_link = doc.createElement(Ixml.IMAGE_LINK);
			Element price = doc.createElement(Ixml.PRODUCT_PRICE);
			Element pageNumber = doc.createElement(Ixml.PAGENUMBER);
			
			record.appendChild(name);
			record.appendChild(name_english);
			record.appendChild(product_link);
			record.appendChild(image_link);
			record.appendChild(price);
			record.appendChild(pageNumber);
			
			doc.getDocumentElement().appendChild(record);
			appendResult(doc);
			return Integer.parseInt(record.getAttribute("id").trim());
			
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			return -1;
		}
	}
	
	Element getRecord(int id)
	{
		Element rootElement = currentDoc.getDocumentElement();
		NodeList tests = rootElement.getElementsByTagName(Ixml.RECORD);
		for(int i = 0; i < tests.getLength(); i++)
		{
			Element test = (Element) tests.item(i);
			if(test.getAttribute(Ixml.ID).equals(Integer.toString(id)))
				return test;
		}
		return null;
	}
	

}
