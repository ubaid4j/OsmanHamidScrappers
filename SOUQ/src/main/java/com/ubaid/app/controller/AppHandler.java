package com.ubaid.app.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.nodes.Document;

import com.ubaid.app.model.document.Documents;
import com.ubaid.app.model.document.IDocument;
import com.ubaid.app.model.export.ToXLS;
import com.ubaid.app.model.scrapper.Scrapper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//app handler
public class AppHandler
{
	
	
	public AppHandler(Controller controller)
	{
		try
		{
			//getting list of urls
			List<String> urls = getURLList();
			
			//starting progress bar and time
			controller.getModel().startProgressBar();
			controller.getModel().startTimer();
			
			//creating threads
//			ExecutorService threads_ = Executors.newFixedThreadPool(urls.size());
			
			//creating a vector list (avaoding synchronization)

			controller.getViewController().getPuase().setOnAction(new EventHandler<ActionEvent>()
			{

				@Override
				public void handle(ActionEvent event)
				{
					boolean value = Controller.isPause();
					Controller.setPause(!value);
					if(value)
						controller.getViewController().getPuase().setText("Pause");
					else
						controller.getViewController().getPuase().setText("Resume");
				}
				
			});
			
			
			controller.getViewController().getExcel().setOnAction(new EventHandler<ActionEvent>()
			{
				
				@Override
				public void handle(ActionEvent event)
				{
					//after this: creating a excel file 
					try
					{
						System.out.println("Handling button");
						ExecutorService service = Executors.newFixedThreadPool(1);
						service.execute(new Runnable() {
							
							@Override
							public void run()
							{
								try
								{
									new ToXLS(new File(getFileName()), Controller.getList(), controller.getQueue());
								}
								catch (Exception e)
								{
									e.printStackTrace();
								}								
							}
						});
					}
					catch (Exception e)
					{
						controller.getQueue().setText(e, controller.getQueue().getErrorIndex());
					}					
				}
			});

			
			
			//only for one
			for(int i = 0; i < urls.size(); i++)
			{
				controller.getQueue().setIndex(urls.get(i));
				new Scrapper_(controller, urls.get(i));
			}
	
//			threads_.shutdown();


			
			
/*			
			//a loop terminated when pool execution end
			while(true)
			{
				if(threads_.isTerminated())
					break;
				Thread.sleep(2000);
			}
*/
			
			
			
			//stoping progress bar and timer
			controller.getModel().stopProgressBar();
			controller.getModel().stopTimer();
		}
		catch(Exception exp)
		{
			controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
		}
	}
	
	private String getFileName()
	{
		return generateRandomWords() + ".xls";
	}
	
	
	private String generateRandomWords()
	{
	    Random random = new Random();
        char[] word = new char[random.nextInt(16)+5]; 
        for(int j = 0; j < word.length; j++)
        {
            word[j] = (char)('a' + random.nextInt(26));
        }
        
	    return new String(word);
	}

	private List<String> getURLList()
	{
		List<String> list = new ArrayList<>();
//		list.add("https://saudi.souq.com/sa-en/bakeware/kitchen-accessories-316%7Cbakeware---and---accessories-526/a-t/s/?sortby=sr&page=1&ref=nav");
		list.add("https://saudi.souq.com/sa-en/women/dresses-465/a-t/s/?page=1&ref=nav");
		return list;		
	}
	
	private class Scrapper_ implements Runnable
	{

		Controller controller;
		String url;
		
		public Scrapper_(Controller controller, String url)
		{
			this.controller = controller;
			this.url = url;
			run();
			
		}
		
		@Override
		public void run()
		{
			try
			{
				//creating document 
				IDocument docs = new Documents(url, controller);
				
				//getting list of docs
				List<Document> docList = docs.getDocuments();
				
				for(int i = 0; i < docList.size(); i++)
				{
					new Scrapper(docList.get(i), controller.getQueue());
				}	
			}
			catch(Exception exp)
			{
				controller.getQueue().setText(exp, controller.getQueue().getErrorIndex());
			}
		}
				
	}
	
}
