package kr.or.ddit.encoding;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 인코딩(Encoding)
 * 목적 - 데이터를 전송하거나 저장하기 위해서 매체가 인지할 수 있는 방식으로 데이터의 표현 방법을 바꾸는 작업. (데이터를 전송 저장하려면 매체가 필요함)
 * ex) URLEncoding(network), Base64(범용 인코딩)
 *
 */
public class EncodingDesc {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String original = "ABC123한글";
		String urlEncoded = URLEncoder.encode(original, "UTF-8");
		// 서버에 보내주려고 인코딩함.다시 한글로 보려면 디코딩해야함.
		
		System.out.println(urlEncoded);
	}
	
}
