package com.ubaid.app;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import com.ubaid.app.controller.Controller;
import com.ubaid.app.model.logger.Queue;
import com.ubaid.app.view.View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class SOUQ_SCRAPPER extends Application
{
	public static void main(String [] args)
	{
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		//this is Queue class, having blocking queue
		//storing information about (info, exception, warnings)
		Queue queue = new Queue();
		
		
		try
		{	

			
			//loader
			
			URL url = getClass().getResource("View.fxml");
			System.out.println(url);
			FXMLLoader loader = new FXMLLoader(url);
			
		
//			@SuppressWarnings("deprecation")
//			URL url = getFileFromResources("page.fxml").toURL();
		
			
			
//			FXMLLoader loader = new FXMLLoader(url);
		
			
			//root is borderpane
			Parent root = loader.load();
			
			//scene
			Scene scene = new Scene(root);

			//adding style sheets for logger
//			@SuppressWarnings("deprecation")
//			URL url2 = getFileFromResources("log-view.css").toURL();

			URL url2 = getClass().getResource("log-view.css");
			
			scene.getStylesheets().add
			(			 
				url2.toExternalForm()
			);

//			File iconFile = getFileFromResources("icon.png");
			URL url3 = getClass().getResource("icon.png");
			File file = new File(url3.getFile());
			InputStream stream = new FileInputStream(file);
			
			primaryStage.getIcons().add(new Image(stream));
			primaryStage.setScene(scene);
			primaryStage.setTitle("Scrapper");
			primaryStage.show();

			
			//controller
			new Controller(queue, (View) loader.getController());
			
			//listener, when closing window, then it terminating all the 
			//threads using System.exit(0)
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
			{

		         public void handle(WindowEvent event)
		         {
		             Platform.runLater(new Runnable()
		             {

		                 public void run()
		                 {
		                     System.exit(0);
		                 }
		             });
		         }
		     });

		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			queue.setText(exp, queue.getErrorIndex());
		}
		

	}
	
//	 // get file from classpath, resources folder
//    private File getFileFromResources(String fileName) {
//
//        ClassLoader classLoader = getClass().getClassLoader();
//
//        URL resource = classLoader.getResource(fileName);
//        if (resource == null) {
//            throw new IllegalArgumentException(fileName + " file is not found!");
//        } else {
//            return new File(resource.getFile());
//        }
//
//    }
}
