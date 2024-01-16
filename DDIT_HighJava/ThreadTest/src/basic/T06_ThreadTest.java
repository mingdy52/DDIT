package basic;

import javax.swing.JOptionPane;

public class T06_ThreadTest {

	public static boolean inputChk = false;

	public static void main(String[] args) {
		Thread th1 = new DataInput();
		Thread th2 = new CountDown();

		th1.start();
		th2.start();
	}
}

class DataInput extends Thread {

	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");

		// 입력이 완료되면 true로 설정한다.
		T06_ThreadTest.inputChk = true;

		System.out.println("입력한 값은 " + str + "입니다.");
	}
}

class CountDown extends Thread {
	@Override
	public void run() {

		for (int i = 10; i >= 1; i--) {
			System.out.println(i);

			// 입력이 완료 되었는지 여부를 검사하고 입력이 완료되면 run()를 종료시킨다.
			// 즉 현재 스레드를 종료 시킨다.

			if (T06_ThreadTest.inputChk) {
				return; // run() 메서드 종료
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		System.exit(0);

	}
}