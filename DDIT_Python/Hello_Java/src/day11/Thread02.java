package day11;

public class Thread02 {

	public static void showNum() {
	
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i <= 1000; i++) {
					System.out.print(i);
					if(i%100 == 0) {
						System.out.println();
					}
				}
			}
		}).start();
		
	}
	
	public static void showAscii() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 1; i <= 1000; i++) {
					System.out.print((char)i);
					if(i%100 == 0) {
						System.out.println();
					}
				}
			}
		}).start();
	}
	
	public static void main(String[] args) {
		
		showNum();
		showAscii();
		
	}
	
}
