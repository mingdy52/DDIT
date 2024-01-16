package basic;

import java.util.Random;

import javax.swing.JOptionPane;

public class T07_ThreadGame {
	public static boolean inputChk = false;

	public static void main(String[] args) {

		Thread th1 = new RSP();
		Thread th2 = new Timer();

		th1.start();
		th2.start();

	}
}

class RSP extends Thread {
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("무엇을 내시겠습니까?");

		T07_ThreadGame.inputChk = true;

		Random random = new Random();
		int num = random.nextInt(3);
		String computer = null;

		switch (num) {

		case 0:
			computer = "가위";
			break;

		case 1:
			computer = "바위";
			break;

		case 2:
			computer = "보";
			break;
		}

		String result;

		if (str.equals(computer)) {
			result = "무승부입니다.";
		} else if (str.equals("가위") && num == 2 || str.equals("바위") && num == 0 || str.equals("보") && num == 1) {
			result = "당신이 이겼습니다.";
		} else {
			result = "컴퓨터가 이겼습니다.";
		}

		System.out.println("=== 결 과 ===");
		System.out.println("컴퓨터 : " + computer);
		System.out.println("당 신 : " + str);
		System.out.println("결 과 : " + result);

	}
}

class Timer extends Thread {

	@Override
	public void run() {

		for (int i = 5; i >= 1; i--) {
			System.out.println(i);

			if (T07_ThreadGame.inputChk) {
				return;
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		System.out.println("5초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}
