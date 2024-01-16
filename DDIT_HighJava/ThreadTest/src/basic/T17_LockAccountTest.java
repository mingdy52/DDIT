package basic;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 은행의 입출금을 스레드로 처리하는 예제
 * (Lock 객체를 이용한 동기화 처리)
 */
public class T17_LockAccountTest {

	/*
	 * 락 기능을 제공하는 클래스
	 * 
	 * ReentrantLock => Read 및 Write 구분없이 사용하기 위한 락 클래스로 동기화 처리를 위해 사용됨.
	 * synchronized를 이용한 동기화 처리보다 부가적인 기능이 제공됨. ex) Fairness 설정 등 => 가장 오래 기다린 스레드가
	 * 가장 먼저 락을 획득하게 함.
	 * 
	 * ReentrantReadWriteLock => Read 및 Write락을 구분하여 사용 가능함 재귀적으로 여러번 Read나 Write 락을
	 * 획득할 수 있다.(재진입가능) 여러 스레드가 동시에 read 작업은 가능하지만, write 작업은 단지 하나의 스레드만
	 * 가능함(exclusive) => Write보다 Read 위주의 작업이 많이 발생하는 경우에 사용하면 처리량(Throughput)이
	 * 좋아진다.
	 */

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock(true); // 락 객체 생성
		
		LockAccount lAcc = new LockAccount(lock); // 공유객체 생성
		lAcc.deposit(10000); // 입금처리
		
		BankThread2 th1 = new BankThread2(lAcc);
		BankThread2 th2 = new BankThread2(lAcc);
		
		th1.start();
		th2.start();
	}
}

// 입출금을 담당하는 클래스
class LockAccount {
	private int balance;

	// Lock 객체 생성 => 되도록이면 private fianl로 만든다.
	private final Lock lock;

	public LockAccount(Lock lock) {
		this.lock = lock;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	// 입금하는 메서드
	public void deposit(int money) {
		// Lock객체의 lock() 메서드가 동기화의 시작이고, unlock()메서드가 동기화의 끝을 나타낸다.
		// lock() 메서드로 동기화를 설정한 곳에서는 반드시 unlock()메서드로 해제해 주어야 한다.
		lock.lock(); // 동기화 시작 (락을 획득하기 전까지 BLOCKED 됨.)
		balance += money; // 동기화 처리 부분
		lock.unlock(); // 동기화 끝
	}

	// 출금하는 메서드(출금성공: true, 실패: false)
	public boolean withdraw(int money) {
		boolean chk = false;
		
//		if(lock.tryLock()) { // 락 획득 시도 (성공: true, 실패: flase)
//			System.out.println(Thread.currentThread().getName() + " : 락 획득 성공!");
			
		lock.lock();
			// try ~ catch 블럭을 사용할 경우에는
			// unlock() 호출은 finally 블럭에서 하도록 한다.
			try {
				if(balance >= money) {
					for (int i = 1 ; i <= 1000000000; i++) {} // 시간 지연용
					balance -= money;
					System.out.println("메서드 안에서 balance = " + getBalance());
					chk = true;
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				lock.unlock(); // 락 해제(동기화 끝)
				System.out.println("락 해제(반납) 완료");
			}
//		} else { // 락 획득 실패시
//			System.out.println(Thread.currentThread().getName() + " : 락 획득 실패");
//		}
		return chk;
	}

}

// 은행 업무를 처리하는 스레드
class BankThread2 extends Thread {
	private LockAccount lAcc;

	public BankThread2(LockAccount lAcc) {
		this.lAcc = lAcc;
	}
	
	@Override
	public void run() {
		boolean result = lAcc.withdraw(6000);
		System.out.println(Thread.currentThread().getName() + " 스레드 안에서 result = "
							+ result + ", balance = " + lAcc.getBalance());
	}
}
