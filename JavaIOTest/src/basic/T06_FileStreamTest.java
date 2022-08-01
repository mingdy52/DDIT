package basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class T06_FileStreamTest {
	public static void main(String[] args) {
		// 파일에 출력하기
		FileOutputStream fos = null;

		try {
			// 출력용 스트림 객체 생성
			fos = new FileOutputStream("d:/D_Other/test.txt");
			for (char ch = 'A'; ch < 'Z'; ch++) {
				fos.write/*void*/(ch);
			}
			System.out.println("파일 쓰기 작업 완료");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("=================================================");
		
		FileInputStream fis = null;

		try {
			fis = new FileInputStream("d:/D_Other/test.txt");

			int data = 0;

			// 읽어온 값이 -1이면 파일의 끝까지 다 읽었다는 의미이다.
			// read()에 매개변수 작성안할시 1개씩 읽어옴
			while ((data = fis.read/*int*/()) != -1) {
				// 읽어온 자료 출력하기
				System.out.print((char) data);
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
