package basic;

public class T14_ThreadShareDataTest {
	/*
	 * 스레드에서 데이터를 공통으로 사용하는 방법 
	 * 1. 공통으로 사용하 ㄹ데이터를 클래스로 정의한다. 
	 * 2. 공통으로 사용할 클래스의 인스턴스를 만든다. 
	 * 3. 이 인스턴스를 각각의 스레드에 넘겨준다. 
	 * 4. 각각의 스레드는 이 인스턴스의 참조값을 저장한 변수를 이용하여 공통 데이터를 사용한다.
	 * 
	 * 예) 원주율을 계산하는 스레드가 있고, 계산된 원주율을 출력하는 스레드가 있다. 
	 * 	     원주율을 계산한 후 이 값을 출력하는 프로그램을 작성하시오. (이 때 원주율을 저장하는 객체가 필요하다.)
	 */

	public static void main(String[] args) {
		ShareData sd = new ShareData();
		
		CalcPIThread cTh = new CalcPIThread(sd);
		PrintPIThread pTh = new PrintPIThread(sd);
		
		cTh.start();
		pTh.start();
	}
}

// 원주율 결과를 관리하는 클래스
class ShareData {
	public double result; // 원주율이 저장될 변수
	/*
	 * volatile => 선언된 변수를 컴파일러의 최적화 대상에서 제외시킨다. 
	 * 			      즉, 값이 변경되는 즉시 변수에 저용시킨다. 다중 스레드에서
	 * 하나의 변수가 완변하게 한번에 작동하도록 보장하는 키워드(일종의 동기화)
	 */

	// 원주율 계산이 완료되었는지를 나타내는 변수
	volatile boolean isOk = false; // 휘발성 데이터. 원본데이터를 보고 접근함(속도 느림, 정확함)
}

// 원주율을 계산하는 스레드
class CalcPIThread extends Thread {
	private ShareData sd;

	CalcPIThread(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		/*
		 * 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 .....) *4; 1 - 3 + 5 - 7 + 9 => 분모 0 1 2 3
		 * 4 => 분모를 2로 나눈 몫
		 */

		double sum = 0.0;
		for (int i = 1; i <= 1500000; i += 2) {
			if (((1 / 2) % 2) == 0) { // 2로 나눈 몫이 짝수이면 +
				sum += (1.0 / i);
			} else { // 2로 나눈 몫이 짝수이면 -
				sum -= (1.0 / i);
			}

		}

		sd.result = sum * 4; // 계산된 원주율을 공통객체의 멤버변수에 저장
		sd.isOk = true; // 계산이 완료되었음을 알려줌

	}

}

// 계산된 원주율을 출력하는 스레드
class PrintPIThread extends Thread {
	private ShareData sd;

	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		while (true) {
			// 원주율 계산이 완료되었는지 확인
			if (sd.isOk) {
				break;
			}
		}
		
		System.out.println();
		System.out.println("계산된 원주율 : " + sd.result);
		System.out.println("PI : " + Math.PI);
	}

}
