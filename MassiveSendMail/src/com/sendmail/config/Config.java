package com.sendmail.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
	private static final String CONFIG = "config/config.properties";
	private static String EMAIL;
	private static String CODE;
	public static void loadMoreConfig(){
		try{
			Properties otherSettings = new Properties();
			InputStream is = new FileInputStream(new File(CONFIG));
			otherSettings.load(is);
			is.close();
			
			EMAIL = otherSettings.getProperty("Email", "");
			CODE = otherSettings.getProperty("Code", "");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getEMAIL() {
		return EMAIL;
	}
	public static String getCODE() {
		return CODE;
	}
}	
