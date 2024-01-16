package basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
	성능향상을 위한 보조스트림 예제2
	(문자 기반의 Buffered스트림 사용 예제)
*/

public class T12_BufferedIOTest {
	public static void main(String[] args) throws FileNotFoundException{

		// 문자열기반으로 자료를 처리 할때 사용(읽어 오려는 자료가 char같은 문자일 때 사용)
		// FileinputStream과 기능은 거의 동일
		FileReader fr = null;
//		try {
//
//			// 이클립스에서 만든 자바 프로그램이 실행되는 기본 위치는
//			// 해당 '프로젝트 폴더'가 기본 위치가 된다.
//			fr = new FileReader("./src/kr/or/ddit/basic/T11_BufferedIOTest.java");
//
//			int data = 0;
//			while ((data = fr.read()) != -1) {
//				System.out.print((char) data);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				fr.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		fr = new FileReader("./src/kr/or/ddit/basic/T11_BufferedIOTest.java");
		
		// 한줄씩 읽을 수 있도록 해주는 readLine을 이용하기 위해 BufferedReader 사용
		BufferedReader br = new BufferedReader(fr);

		String temp = "";

		try {
			for (int i = 1; (temp = br.readLine()) != null; i++) {
				System.out.printf("%4d : %s\n", i, temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
