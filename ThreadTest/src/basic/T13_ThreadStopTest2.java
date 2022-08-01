package basic;

public class T13_ThreadStopTest2 {
	/*
	 * Thread의 stop() 메서드를 호출하면 스레드가 바로 멈춘다. 이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 나중에
	 * 실행되는 프로그램에 영향을 줄 수 있다. 그래서 현재는 stop()은 비추천(Deprecated)으로 되어 있다.
	 */

	public static void main(String[] args) {
//	      ThreadStopEX1 th = new ThreadStopEX1();
//	      th.start();

		ThreadStopEX2 th2 = new ThreadStopEX2();
		th2.start();

		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	      th.setStop(true);
		th2.interrupt(); // 인터럽트 걸기(살짝 꼼수)
	}
}

class ThreadStopEX1 extends Thread {
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while (!stop) {
			System.out.println("스레드 처리 중");
		}
		System.out.println("자원 정리 중");
		System.out.println("실행 종료");
	}
}

// interrupt()를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopEX2 extends Thread {

//	@Override
//	public void run() {
//		
//	 방법 1 => sleep()이나 join() 등을 사용했을 때 
//	 * 		    interrupt()를 호출하면 interruptedException이 발생한다. 
//		 
//		
//		try {
//			while(true) {
//				System.out.println("스레드 처리 중");
//					Thread.sleep(1);
//			}
//		} catch (InterruptedException e) {
//			
//		}
//	}

// 방법2 => interrupt()메서드가 호출 되었는지 검사하기
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(true)
			
		{
			System.out.println("스레드 처리 중");
			
//			// 검사방법1 => 스레드의 인스턴스용 메서드를 이용하는 방법
//			if(this.isInterrupted()) {
//				System.out.println("인스턴스 용 isInterrupted() 호출됨.");
//				break;
//			}
//			
			// 검사방법2 => 스레드의 정적 메서드를 이용하는 방법
			if (Thread.interrupted()) {
				System.out.println("정적 메서드 interrupted() 호출됨");
				System.out.println();
				break;
			}
		}	
	System.out.println("자원 정리 중");
	System.out.println("실행 종료");
	}

}
