package com.ubaid.app.model.object;

public class NewProducts extends AbstractProducts
{
	protected String product_type;
	
	public NewProducts(String name, String nameEnglish, String productLink, String imageLink, String productPrice, String productType)
	{
		super(name, nameEnglish, productLink, imageLink, productPrice);
		this.product_type = productType;
	}

	@Override
	public String getProdctType()
	{
		return product_type;
	}

	@Override
	public String getBASEURI()
	{
		return baseURI;
	}

	@Override
	public void setBASEURI(String uri)
	{
		super.baseURI = uri;
	}

}
