package practice;

import java.util.Arrays;

public class HorseRace {
	public static void main(String[] args) {
		Horses[] horses = new Horses[] { 
				new Horses("01번말"), 
				new Horses("02번말"), 
				new Horses("03번말"), 
				new Horses("04번말"),
				new Horses("05번말"), 
				new Horses("06번말"), 
				new Horses("07번말"), 
				new Horses("08번말"), 
				new Horses("09번말"),
				new Horses("10번말") };
		Playing state = new Playing(horses);
		for (Horses h : horses) {
			h.start();
		}
		state.start();
		for (Horses h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}
		try {
			state.join();
		} catch (InterruptedException e) {
		}
		System.out.println();
		System.out.println("경기 끝........");
		System.out.println(); // 경기 종료 후 등수순으로 정렬하기
		Arrays.sort(horses);
		System.out.println("경기 결과");
		for (Horses h : horses) {
			System.out.println(h);
		}
	}

}

class Horses extends Thread implements Comparable<Horses>{
	public static int currentRank = 0;// 현재등수
	private String horsename;// 말이름
	private int rank; // 등수
	private int location; // 위치

	public Horses(String horsename) {
		this.horsename = horsename;
	}

	public String getHorsename() {
		return horsename;
	}

	public void setHorsename(String horsename) {
		this.horsename = horsename;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마 : " + horsename + ", 등수 : " + rank;

	}

	public int compareTo(Horses horse) {
		return Integer.compare(rank, horse.getRank()); // 등수비교
	}

	public void run() {
		for (int i = 1; i <= 50; i++) {
			location = i;
			try {
				Thread.sleep((int) (Math.random() * 400));
			} catch (InterruptedException e) {
			}
		}
		rank = ++Horses.currentRank;
	}
} // 경기 중의 현재 상황을 출력하는 쓰레드

class Playing extends Thread{
	private Horses[] horses; //생성자
	public Playing(Horses[] horses) {
		this.horses=horses;
	}
	
	@Override
	public void run() {
		while(true) {
			if(Horses.currentRank==horses.length) {
				break;
			}
			for(int i=1; i<=10; i++) {
				System.out.println();
			}
			for(int i = 0; i< horses.length; i++) {
				System.out.println(horses[i].getHorsename()+" : ");
				for(int j =1; j<=50; j++) {
					if(horses[i].getLocation()==j) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}
	}
}
