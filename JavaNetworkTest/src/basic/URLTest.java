package basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


public class URLTest {
	public static void main(String[] args) throws MalformedURLException, URISyntaxException{
		// URL클래스 => 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스
		
		URL url = new URL("http", "ddit.or.kr", 80, "/main/index.html?ttt=123&name=apple#kkk");
//		URL url = new URL("http://ddit.or.kr:80/main/index.html?ttt=123#kkk");
		
		
		System.out.println("전체 URL주소 : " + url.toString());
		
		System.out.println("protocol : " + url.getProtocol());
		System.out.println("host : " + url.getHost());
		System.out.println("query : " + url.getQuery());
		System.out.println("file : " + url.getFile()); // 쿼리 정보(? 뒤) 포함
		System.out.println("path : " + url.getPath()); // 쿼리 정보 미포함
		System.out.println("port : " + url.getPort());
		System.out.println("ref : " + url.getRef()); // # 뒤
		System.out.println();
		
		// 전체 URL의 문자열 객체를 리턴
		System.out.println(url.toExternalForm());
		
		// 전체 URL을 담은  USVString을 반환(url을 string으로 반환)
		System.out.println(url.toString());
		
		// 전체 경로 URI를 반환 
		System.out.println(url.toURI().toString());
				
	}
}
