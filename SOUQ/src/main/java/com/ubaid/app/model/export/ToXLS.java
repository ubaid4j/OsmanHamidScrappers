package com.ubaid.app.model.export;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.ubaid.app.model.logger.Queue;
import com.ubaid.app.model.object.Products;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class ToXLS implements Export
{

	public ToXLS(File file, List<Products> list, Queue q) throws Exception
	{
		if(export(file, list, q))
		{
			Platform.runLater(new Alert_(file, q));
		}
		else
		{
			q.setText(new Exception("File not created\nThere is some "), q.getErrorIndex());
		}
	}
	
	
	@Override
	public Boolean export(File file, List<Products> list, Queue q) throws Exception
	{
		//work book creation
		Workbook workbook = new HSSFWorkbook();

		
		Sheet sheet = workbook.createSheet("Products");
		
		//header font
		Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
		
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        Row headerRow = sheet.createRow(0);
        
        String[] headers = {"ID", "Product Name", "Product Name (English)", "Product Link", "Image Link", "Price", "Product Type"};
        
        for(int i = 0; i < headers.length; i++)
        {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        int rowNum = 1;
        for(Products product: list)
        {
            Row row = sheet.createRow(rowNum++);

            row.createCell(0)
                    .setCellValue(rowNum - 1);

            row.createCell(1)
                    .setCellValue(product.getProductName());

            row.createCell(2)
            		.setCellValue(product.getProductNameInEnglish());

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
	
	
	private class Alert_ implements Runnable
	{

		private File file;
		private Queue queue;
		
		public Alert_(File file, Queue q)
		{
			this.file = file;
			this.queue = q;
		}
			
		@Override
		public void run()
		{
			try
			{
				showAlert();				
			}
			catch(Exception exp)
			{
				queue.setText(exp, queue.getErrorIndex());
			}
		}
		
		private void showAlert() throws Exception
		{
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("XLSX file created");
			alert.setHeaderText("Your file have been created");
			alert.setContentText("Your file have created in this path: " + file.getAbsolutePath());

			ButtonType openFile = new ButtonType("Open File");
			ButtonType openFolder = new ButtonType("Open Folder");
			ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

			alert.getButtonTypes().setAll(openFile, openFolder, cancel);

			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == openFile)
			{
				Desktop.getDesktop().open(file);
			}
			else if (result.get() == openFolder)
			{
				Desktop.getDesktop().open(file.getParentFile());
			}
			else if (result.get() == cancel)
			{
				alert.close();
			}
		}

	}
	
}
