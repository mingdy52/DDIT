package basic;

/*
 * 싱글 스레드 프로그램
 */
public class T01_ThreadTest {
	public static void main(String[] args) {
		for (int i = 0; i <= 200; i++) {
			System.out.print("*");
		}

		System.out.println();

		for (int i = 0; i <= 200; i++) {
			System.out.print("$");
		}

	}
}

