package basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class T02_PropertiesTest {

	public static void main(String[] args) {
		// 읽어온 정보를 저장할 Properties 객체 생성
		Properties prop = new Properties();
		
		// 읽어올 파일명을 이용한 File객체 생성
		File file = new File("res/db.properties");
		
		try {
			// 파일 읽기를 수행할 FileInputStream 객체 생성하기
			FileInputStream fis = new FileInputStream(file);
			
			
			//Properties 객체로 파일 내용 읽기
			//파일 내용을 읽어와 key와 value값으로 분류한 후 Properties객체에 담아준다.
			prop.load(fis);
			
			//읽어온 자료 출력하기
			Enumeration<String> keys = (Enumeration<String>) prop.propertyNames();
			
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key + " : " + value);
			}
			
			System.out.println("출력 끝");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
