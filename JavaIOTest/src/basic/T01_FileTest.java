package basic;

import java.io.File;
import java.io.IOException;

public class T01_FileTest {

	public static void main(String[] args) throws IOException {
		// File 객체 만들기 연습
		
		// 1. new File(String 파일 또는 경로명)
		//	  => 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분문자는 
		//		 '\'를 사용하거나 '/'를 사용할 수 있다.
		
		File file = new File("d:/D_Other/test.txt");
		
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : " + file.isFile());
		System.out.println("디렉토리(폴더) 여부 : " + file.isDirectory());
		System.out.println("------------------------");
		
		File file2 = new File("d:\\D_Other");
		System.out.print(file2.getName() + "은 ");
		if(file2.isFile()) {
			System.out.println("파일");
		} else if (file2.isDirectory()) {
			System.out.println("디렉토리(폴더)");
		}
		System.out.println("------------------------");
		
		// 2. new File(File parent, String child)
		//	  => 'parent' 디렉토리 안에 있는 'child' 파일 또는 디렉토리를 갖는다.
		File file3 = new File(file2, "test.txt"); // file2 경로에서 test를 가져오겠다.
		System.out.println(file3.getName() + "의 용량(크기) : " 
							+ file3.length() + "bytes");
		
		// 3. new File(String parent, String child)
		File file4 = new File(".\\D_Other\\test\\..", "test.txt"); // 상대경로(디스크드라이브)
		System.out.println("절대 경로 : " + file4.getAbsolutePath());
		System.out.println("경로 : " + file4.getPath()); // 입력한 경로 가져옴
		System.out.println("표준 경로 : " + file4.getCanonicalPath());
		System.out.println("------------------------");
		
		/*
		 * 디렉토리(폴더) 만들기
		 * 
		 * 1. mkdir() 
		 * 		=> File 객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		 * 	              중간의 경로는 모두 미리 만들어져 있어야 한다. 
		 * 
		 * 2. mkdirs() 
		 * 		=> 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 디렉토리를 만들어 준다. 
		 * 
		 * => 위 두 메서드 모두 만들기를 성공하면 true, 실패하면 false를 반환한다. 
		 * 
		 */
		
		File file5 = new File("d:/D_Other/연습용"); // 두번 만들 수 없음
		if(file5.mkdir()) {
			System.out.println(file5.getName() + " 만들기 성공!");
		} else {
			System.out.println(file5.getName() + " 만들기 실패!");
		}
		
		File file6 = new File("d:/D_Other/test/java/src"); // test와 java 폴더가 없어서 실패
		if(file6.mkdir()) {
			System.out.println(file6.getName() + " 만들기 성공!");
		} else {
			System.out.println(file6.getName() + " 만들기 실패!");
		}
	}
}
