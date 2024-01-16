package kr.or.ddit.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertiesFileRead {
	
	public static void main(String[] args) {
//		useProterties();
		useResourceBundle();
	}
	
	private static void useResourceBundle() {
		String baseName = "kr.or.ddit.properties.message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.ENGLISH);
		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = bundle.getString(key);
			System.out.printf("%s : %s \n", key, value);
		}
	}

	private static void useProterties() {
		String cpPath = "/kr/or/ddit/properties/sample.properties";
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (
				InputStream is = loader.getResourceAsStream(cpPath);
		){
			Properties properties = new Properties();
			properties.load(is);
			
			for (Entry<Object, Object> entry : properties.entrySet()) {
				System.out.printf("%s : %s \n", entry.getKey(), entry.getValue());
			}
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
