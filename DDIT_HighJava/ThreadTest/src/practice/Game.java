package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
	
	static int GRank = 1;

	public static void main(String[] args) {
		List<Horse> h = new ArrayList<Horse>();
		
		h.add(new Horse("01"));
		h.add(new Horse("02"));
		h.add(new Horse("03"));
		h.add(new Horse("04"));
		h.add(new Horse("05"));
		h.add(new Horse("06"));
		
		for (Horse horse : h) {
			horse.start();
		}

		for (Horse hs : h) {
			try {
				hs.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Collections.sort(h);
		System.out.println("경기끝 ....");
		System.out.println("======================================================");
		System.out.println();
		System.out.println(" 경기 결과 ");

		for (Horse ho : h) {
			System.out.println(ho.getHName() + " " + ho.getRank() + "등");
		}
	}
	
}

class Horse extends Thread implements Comparable<Horse> {
	private String name;
	private int rank;
	private int location;

	public Horse() {
	}
	
	public Horse(String name) {
		this.name = name;
	}

	public String getHName() {
		return name;
	}
	
	public int getLocation() {
		return location;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}
	@Override
	public int compareTo(Horse o) {
		if (this.rank > o.rank) {
			return 1;
		} else if (this.rank < o.rank) {
			return -1;
		} else {
			return 0;
		}
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			System.out.print("\n" + name + " ");
			for (int j = 0; j < i; j++) {
				System.out.print("-");
			}
			System.out.print(">");

			for (int j = 49; j > i; j--) {
				System.out.print("-");
			}

			try {
				Thread.sleep((int) (Math.random() * 500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
		System.out.println(name + " 끝");

		setRank(Game.GRank);
		Game.GRank++;
	}
	
}

