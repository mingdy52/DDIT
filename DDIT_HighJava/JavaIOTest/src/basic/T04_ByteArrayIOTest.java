package basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	public static void main(String[] args) throws IOException {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		byte[] outSrc = null; // 입력된 것을 여기로 움직여서 붙여넣을 거임

		byte[] temp = new byte[4];

		// 배열 입출력 스트림 : 해당 main안에서 입력받은 것을 넣고 출력함
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		int data = 0;

		while ((data = bais.read(temp)) != -1) {
			
			// 0번째 부터 data의 갯수만큼 읽어라(data에 들어간 갯수 만큼 읽게함)
			baos.write(temp, 0, data);

			System.out.println("temp => " + Arrays.toString(temp));
			/*
			자바에서 배열의 변수 그대로 출력하게 되면 배열의 주소값이 출력
			배열의 내용을 출력하기 위해 Arrays.toString()을 사용해야 함
			*/
		}

		outSrc = baos.toByteArray();

		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));

		bais.close();
		baos.close();
	}
}
