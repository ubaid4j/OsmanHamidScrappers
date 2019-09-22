package com.ubaid.app.model.export;

import java.io.File;
import java.util.List;

import com.ubaid.app.model.object.Products;

public interface Export
{
	Boolean export(File file, List<Products> list) throws Exception;
}
