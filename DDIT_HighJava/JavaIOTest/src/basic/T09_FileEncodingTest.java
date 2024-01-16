package basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
	/*
	 * 인코딩 방식에 대하여
	 * 
	 * 한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식 두가지로 나뉜다.
	 * 원래 한글윈도우는 CP949방식을 사용했는데 윈도우를 개발한 마이크로소프트에서 EUC-KR 방식에서 
	 * 확장하였기 때문에 MS949라고도 부른다.
	 * 한글 windos의 메모장에서 말하는 ANSI인코딩이란 CP949(Code Page 949)를 말한다.
	 * CP949는 EUC-KR의 확장이며, 하위 호환성이 있다.
	 * 
	 * - MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI 계열)
	 * - UTF-8 => 유니코드 UTF-8 인코딩 방식(영문자 및 숫자: 1byte, 한글: 3byte) => 가변적
	 * - US-ASCII => 영문 전용 인코딩 방식
	 * 
	 * ANSI는 영어를 표기하기 위해 만든 코드로 규격 자체에 한글이 없었다가 
	 * 나중에 여기에 EUC-KR, CP949라는식으로 한글이 포함되었음
	 * 
	 * 참고)
	 * 	ASCII => extended ASCII(ISO 8859-1) => 조합형, 완성형(KSC 5601)
	 * -------------------------------------------------------------------
	 * 	=> 윈도우 계열: CP949(확장 완성형) - 일부문자(8824자)를 추가함.
	 * 	=> 유닉스 계열: EUC-KR(확장 유닉스 코드)
	 * -------------------------------------------------------------------	
	 * 	=> ANSI계열 => EUC-KR
	 * 	=> 유니코드(UTF-8)
	 * -------------------------------------------------------------------
	 */
	
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		InputStreamReader isr = null;

		try {
			/*
			 * FileInputStream객체를 생성 한 후 이 객체를 매개변수로 받는 InputStreamReader객체를 생성한다.
			 */

			fis = new FileInputStream("d:/D_Other/test_ansi.txt");

			// 파일의 인코딩정보를 이용하여 읽어오기
			// InputStreamReader객체는 파일의 인코딩 방식을 지정할 수 있다.
			// 형식) new InputStreamReader(바이트 기반스트림, 인코딩방식);
			isr = new InputStreamReader(fis, "MS949"); //  fis에 저장된 바이트를 CP949방식으로 읽겟다고 지정

			int data = 0;
			while ((data = isr.read()) != -1) {
				System.out.print((char) data);
			}
			System.out.println();
			System.out.println("출력 끝....");
			
			fis = new FileInputStream("d:/D_Other/test_utf8.txt");

			// 파일의 인코딩정보를 이용하여 읽어오기
			// InputStreamReader객체는 파일의 인코딩 방식을 지정할 수 있다.
			// 형식) new InputStreamReader(바이트 기반스트림, 인코딩방식);
			isr = new InputStreamReader(fis, "UTF-8"); //  fis에 저장된 바이트를 CP949방식으로 읽겟다고 지정

			data = 0;
			while ((data = isr.read()) != -1) {
				System.out.print((char) data);
			}
			System.out.println();
			System.out.println("출력 끝....");
			
			// => 인코딩의 정보를 맞춰야한다.
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 보조 스트림만 닫아도 같이 사용하고 있던 기본 스트림도 같이 닫아줌
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

