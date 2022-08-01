package basic;

import java.lang.reflect.Array;
import java.util.Arrays;

public class T03_ByteArrayIOStream {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		// 직접 복사하는 방법
		outSrc = new byte[inSrc.length]; // 배열 공간 확보 
		
		for (int i = 0; i < outSrc.length; i++) {
			outSrc[i] = inSrc[i];
		}
		
		System.out.println("직접 복사 후 outScr => " + Arrays.toString(outSrc));
		
		
		
		
	}
}
