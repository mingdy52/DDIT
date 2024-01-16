package basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T10_FileEncodingTest {

	/*
	 * OutputStreamWriter 객체 => 바이트기반의 출력용 객체를 문자기반 출력용 객체로 변환해주는 보조 스트림 객체 => 이 객체도
	 * 출력할 때 '인코딩 방식'을 지정해서 출력할 수 있다.
	 */

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 키보드로 입력한 내용을 파일로 저장하는데
		// out_utf8.txt 파일은 'UTF-8' 인코딩 방식으로
		// out_ansi.txt 파일은 'MS949' 인코딩 방식으로 저장한다.

		// 키보드로 콘솔창에 입력 받기 때문에 System의 input창에 넣음
		InputStreamReader isr = new InputStreamReader(System.in);

		// 파일 출력용 스트림 객체생성
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");

		// 출력할 때 문자 인코딩 방식 선택
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "UTF-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "MS949");

		int data = 0;

		System.out.println("아무거나 입력하세요...");

		// Ctrl + z : 빠져나오기
		while ((data = isr.read()) != -1) {
			osw1.write(data);
			osw2.write(data);
		}

		System.out.println("작업 완료...");

		isr.close();
		osw1.close();
		osw2.close();
	}

}
