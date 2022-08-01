package basic;

public class T09_ThreadDaemonTest {

	public static void main(String[] args) {

		Thread th = new AutoSaveThread();
		// 데몬 스레드로 설정하기(start()를 호출하기 전에 설정해야 한다.)
		th.setDaemon(true);
		th.start();
		
		try {
			for (int i = 1; i <= 20; i++) {
				System.out.println("직업 - " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		System.out.println("메인스레드 종료");
		
	}

}

/*
 * 자동저장 기능을 제공하는 스레드
 */
class AutoSaveThread extends Thread {
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save(); // 저장기능 호출
		}
	}
	
	public void save() {
		System.out.println("작업 내용을 저장합니다.");
	}
}