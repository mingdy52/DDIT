package basic;

import java.io.FileInputStream;
import java.io.IOException;

public class T05_FileStreamTest {
	public static void main(String[] args) {

		
		FileInputStream fis = null;

		try {
			// 해당 경로의 파일의 객체를 생성함
			fis = new FileInputStream("d:/D_Other/test.txt"); //데이터 출발지

			int data = 0;

			// 읽어온 값이 -1이면 파일의 끝까지 다 읽었다는 의미이다.
			// read()에 매개변수 작성안할시 1개씩 읽어옴
			while ((data = fis.read()) != -1) {
				// 읽어온 자료 출력하기
				System.out.println((char) data);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
