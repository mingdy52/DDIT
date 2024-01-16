package basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T07_FileWriterTest {
	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기

		// 콘솔(표준 입출력 장치)과 연결된 입력용 문자 스트림 생성
		// InputStreamReader => 바이트 기반 스트림을 문자기반 스트림으로 변환해 주는 보조스트림
		// 일반 스트림들을 보조해주는 역할
		// EX : 내가 A를 입력시 해당 문자의 바이트로 저장후 출력할 때 바이트에 대응하는 것을 출력
		// 스트림 형태로 넣는데 Reader가 붙어있어 문자형태로 읽어오겟다라는 느낌임
		InputStreamReader isr = new InputStreamReader(System.in);
		
		// 파일 출력용 문자기반 스트림(FileWriter)
		FileWriter fw = null;

		try {
			// 파일 출력용 문자 스트림 객체 생성
			fw = new FileWriter("d:/D_Other/testChar.txt");
			
			int data = 0;

			System.out.println("아무거나 입력하세오.");

			// 콘솔에서 입력할 때 입력의 끝 표시는 Ctrl + Z 키를 누른다.
			while ((data = isr.read()) != -1) {
				fw.write(data); // 콘솔에서 입력받은 값을 파일에 출력하기
			}

			System.out.println("작업 끝...");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
