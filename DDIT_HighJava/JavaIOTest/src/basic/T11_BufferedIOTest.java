package basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 파일시스템, 파일드라이버등 컴퓨터의 시스템을 사용할 수 있게하는 것이 운영체제

/*
 	성능향상을 위한 보조스트림 예제
 	(바이트 기반의 Buffered스트림 사용 예제)
 */

public class T11_BufferedIOTest {
	public static void main(String[] args) {
		// 입출력 성은ㅇ 향상을 위해서 버퍼를 이용하는 보조 스트림

		FileOutputStream fos = null;
		// byte기반 출력기능 향상해주는 버퍼 -> 이 기능을 도와주는 보조 스트림인 Reader, Writer도존재
		BufferedOutputStream bos = null;

		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");

			// 버퍼의 크기를 지정하지 않으면 기본적으로 버퍼의 크기가
			// 8192byte(8Kb)로 설정된다.

			// 버퍼의 크기가 5byte인 스트림 생성하기
			// 기본스트림과 크기가 5
			bos = new BufferedOutputStream(fos, 5);
			for (int i = '1'; i <= '9'; i++) { // 숫자 자체를 문자로 저장하기 위해서...
				bos.write(i);
			} // -> 버퍼 크기가 5byte여서 한번에 5개씩됨(5개가 다차면 바로 운영체재로 내보냄)

			bos.flush(); // 작업을 종료하기 전(버퍼공간에 데이터가 다 차기전에)에 버퍼에 남아있는 데이터를 모두 출력시킨다.
							// (close시 자동으로 호출 됨)

			System.out.println("작업 끝...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
