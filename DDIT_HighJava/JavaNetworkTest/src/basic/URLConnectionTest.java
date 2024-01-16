package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	public static void main(String[] args) throws IOException {
		// URLConnection => 애플리케이션과 URL간의 통신 연결을 위한 추상 클래스
		
		// 특정 서버(예: naver 서버)의 정보와 파일 내용을 출력하는 예제
		URL url = new URL("http://www.naver.com/index.html");
		
		// Header 정보 가져오기
		
		/*
		 URLConnection 클래스
		 	:URL이 가리키는 리소스에 대한 활성화된 연결(connection)을 나타내는 추상 클래스
		 - 서버(특히 HTTP 서버)와 통신하는데 있어 URL클래스보다 더 나은 제어 방법을 제공한다.
		 - URIStreamHandler 클래스를 비롯한 자바의 프로토콜 핸들러 메커니즘의 한 부분으로서 동작한다.
		 - 원격자원에 접근하는데 필요한 정보를 갖고 있다.
		 - 추상 클래스로 openConnection() 메소드를 사용해서 객체를 생성해여 한다.
		 - connect()메소드를 호출해야 객체가 완성된다.
		 */

		URLConnection urlConn = url.openConnection();
		
		System.out.println("Content-Type : " + urlConn.getContentType());
		System.out.println("Encoding : " + urlConn.getContentEncoding());
		System.out.println("Content : " + urlConn.getContent());
		System.out.println();
		
		// 전체 Header정보 출력하기
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		/*
		 * Iterator
		 * 반복기는 개발자가 컨테이너, 특히 리스트를 순회할 수 있게 해주는 객체
		 * 객체 지향적 프로그래밍에서 배열이나 그와 유사한 자료구조의 내부요소를 순회하는 객체다
		 */
		Iterator<String> it = headerMap.keySet().iterator();
		while(it.hasNext()) {
//			hasNext()는 boolean 타입으로 반환.
//			다음에 가져올 값이 있으면 True, 없으면 False.
//			next()는 "매개변수 혹은 iterator 되는 타입"으로 반환
			String key = it.next();
			System.out.println(key + " : " + headerMap.get(key));
		}
		System.out.println("------------------------------------------------");
		
		// 해당 호스트의 페이지 내용 가져오기
		InputStreamReader isr = 
//				new InputStreamReader((InputStream)urlConn.getContent());
//				new InputStreamReader(url.openStream());
				new InputStreamReader(url.openConnection().getInputStream());
		
		BufferedReader br = new BufferedReader(isr);
		
		// 내용 출력하기
		String str = "";
		while((str = br.readLine()) != null) {
			System.out.println(str);
		}
		
		// 스트림 닫기
		br.close();
	}
}
