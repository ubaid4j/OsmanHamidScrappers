package com.ubaid.app;


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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/View.fxml"));
		
			//root is borderpane
			Parent root = loader.load();
			
			//scene
			Scene scene = new Scene(root);

			//adding style sheets for logger
			scene.getStylesheets().add
			(
			    this.getClass().getResource("log-view.css").toExternalForm()
			);

			primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
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
}
