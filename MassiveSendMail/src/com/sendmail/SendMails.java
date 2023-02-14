package com.sendmail;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sendmail.config.Config;
import com.sendmail.controller.MassSendMail;


public class SendMails {

	public static void main(String[] args) {
		
		Config.loadMoreConfig();
		
		String filename = "data/EmailsList.txt";
		try
		{
			File myObj = new File(filename);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine())
			{
				String data = myReader.nextLine();
				ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
				newCachedThreadPool.execute(new Runnable()
				{
					@Override
					public void run()
					{
						MassSendMail mail = new MassSendMail();
						mail.start(data);
					}
				});
			}
			myReader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
