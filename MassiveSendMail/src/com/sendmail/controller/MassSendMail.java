package com.sendmail.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.sendmail.config.Config;

public class MassSendMail {

	public void start(String mail)
	{
		String filename = "data/MassiveMail.html";
		try
		{
			ProcessBuilder pb = new ProcessBuilder("curl", "--url", "\"smtps://smtp.gmail.com:465\"", "--ssl-reqd", "--mail-from", "\""+Config.getEMAIL()+"\"", "--mail-rcpt", "\"" + mail + "\"", "--upload-file", "" + filename + "", "--user", "\""+Config.getEMAIL()+":"+Config.getCODE()+"\"");
			pb.redirectErrorStream(true);
			Process proc = pb.start();
			InputStream ins = proc.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(ins));
			StringBuilder sb = new StringBuilder();
			read.lines().forEach(line -> {
				sb.append(line);
			});
			read.close();
			proc.waitFor();
			proc.destroy();
		}
		catch (UnsupportedOperationException | IOException e)
		{
			e.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("Email successfully sent to:" + mail);
	}
}
