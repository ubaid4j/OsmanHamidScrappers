package com.ubaid.app.model.export;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ubaid.app.model.object.Products;


public class ToXLS implements Export
{

	public ToXLS(File file, List<Products> list) throws Exception
	{
		if(export(file, list))
		{
			System.out.println(file.getAbsolutePath() + " File has been created successfully");
		}
		else
		{
			System.out.println("File not created successfully");
		}
	}
	
	
	@Override
	public Boolean export(File file, List<Products> list) throws Exception
	{
		//work book creation
		Workbook workbook = new XSSFWorkbook();

		
		Sheet sheet = workbook.createSheet("Products");
		
		//header font
		Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
		
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        Row headerRow = sheet.createRow(0);
        
        String[] headers = {"ID", "Product Name in English", "Product Name in Arabic", "Product Link", "Image Link", "Price", "Product Type"};
        
        for(int i = 0; i < headers.length; i++)
        {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        int rowNum = 1;
        for(Products product: list)
        {
        	System.out.println(rowNum);
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(rowNum - 1);
            
            row.createCell(1)
            		.setCellValue(product.getProductNameInEnglish());

            row.createCell(2)
            		.setCellValue(product.getProductName());
            
            row.createCell(3)
            		.setCellValue(product.getProductLink());

            row.createCell(4)
            		.setCellValue(product.getImageLink());

            row.createCell(5)
            		.setCellValue(product.getProductPrice());

            row.createCell(6)
    				.setCellValue(product.getProdctType());

        }

		// Resize all columns to fit the content size
        for(int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        
        FileOutputStream fileOut = new FileOutputStream(file.getName());
        workbook.write(fileOut);
        fileOut.close();

        workbook.close();
        
		return true;
	}
	
}
