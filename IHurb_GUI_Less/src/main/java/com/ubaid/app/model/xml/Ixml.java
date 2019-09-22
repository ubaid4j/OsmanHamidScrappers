package com.ubaid.app.model.xml;



public interface Ixml
{
	static final String IMAGE_LINK = "imageLink";
	static final String PRODUCT_LINK = "productLink";
	static final String PRODUCT_NAME = "productName";
	static final String PRODUCT_NAME_IN_ENGLISH = "productNameInEnglish";
	static final String PRODUCT_PRICE = "price";
	static final String RECORD = "record";
	static final String RECORDS = "records";
	static final String ID = "id";
	static final String PAGENUMBER = "pageNumber";
	
/*	
	static final String TASKS = "tasks";
	static final String TASK = "task";
	static final String REQUIRED_STEP = "requiredStep";
	static final String USER_STEP = "userStep";
	static final String CONSUMED_TIME = "consumedTime";
	static final String COMPLETED = "completed";
*/	
	boolean createFile(String filePath);
	boolean createStructure();
	int getLastRecordID();
	int createRecord();
}
