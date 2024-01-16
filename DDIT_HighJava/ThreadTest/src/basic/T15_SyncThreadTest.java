package basic;

public class T15_SyncThreadTest { // 메소드에 써주거나 블럭을 만들던가
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkThread th1 = new WorkThread("1번 스레드", sObj);
		WorkThread th2 = new WorkThread("2번 스레드", sObj);
		
		th1.start();
		th2.start();
	}
}

// 공통으로 사용할 객체
class ShareObject {
	private int sum = 0;
	
	public  void add() {
		
		synchronized (this) { // 자기 자신(객체. ShareObject)에 대해서 동기화. 이 부분이 실행될 동안 동기화
			for (int i = 0; i < 1000000000; i++) {} // 시간 지연용
			int n = sum;
			n += 10;
			sum = n;
			
		}
		
		System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		
	}
}

// 작업을 수행하는 스레드
class WorkThread extends Thread {
	private ShareObject sObj;
	
	public WorkThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			
			// 동기화 하는 방법2 : 동기화 블럭으로 처리하기
			synchronized (sObj) { // ShareObject 에 대해서 동기화
				sObj.add();
			}
		}
	}
}