package com.ubaid.app.model.xml;

public interface IxmlInsertion
{
	void setName(String name, int recordID);
	void setNameInEnglish(String name, int recoredID);
	void setProductLink(String productLink, int recordID);
	void setImageLink(String link, int recordID);
	void setPrice(String price, int recordID);
	void setPageNumber(int number, int recordID);
}
