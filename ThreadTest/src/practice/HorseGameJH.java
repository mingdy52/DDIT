package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class HorseGameJH {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HorseRun().start();
	}

}

// 말들 정보 담는 클래스
class HorseInfo implements Comparable<HorseInfo> {

	private String name;
	private int rank;
	private int location;
	boolean setOk;

	public HorseInfo(String name) {
		this.name = name;
		this.rank = 1;
		this.location = 0;
		this.setOk = false;
	}

	/**
	 * @return the location
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(int location) {
		this.location += location;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(HorseInfo horse) {
		// TODO Auto-generated method stub
		if (this.rank > horse.rank)
			return 1;
		else if (this.rank < horse.rank)
			return -1;
		else
			return 0;
	}

}

// 경주클래스
class HorseRun extends Thread {
	private int resultRank = 1;
	private static final int LINE = 50;
	List<HorseInfo> list;
	Map<Integer, HorseInfo> result;
	private int size;

	public HorseRun() {
		list = new ArrayList<HorseInfo>();
		result = new HashMap<Integer, HorseInfo>();
		init();
	}

	public void init() {
		list.add(new HorseInfo("1번"));
		list.add(new HorseInfo("2번"));
		list.add(new HorseInfo("3번"));
		list.add(new HorseInfo("4번"));
		list.add(new HorseInfo("5번"));
		list.add(new HorseInfo("6번"));
		list.add(new HorseInfo("7번"));
		list.add(new HorseInfo("8번"));
		list.add(new HorseInfo("9번"));
		list.add(new HorseInfo("10번"));
		size = list.size();
	}

	public void GameSituation() {
		System.out.println("==========================");
		System.out.println("        === 경마  ===");
		System.out.println("==========================\n");
		for (HorseInfo info : list) {
			// 이름 출력
			System.out.print(info.getName());
			// 라인 그리기
			for (int i = 0; i < LINE; i++) {
				if (i == info.getLocation()) {
					// 말 위치 그리기
					if (info.getLocation() < LINE)
						System.out.print(">");
				} else
					System.out.print("-");
			}
			System.out.println();
		}
		System.out.println("==========================\n");
	}

	public void setLocation() {
		Random rd = new Random();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setLocation(rd.nextInt(14) + 1); // 이동거리
		}
	}

	public void setRank() {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getLocation() >= LINE) {
				list.get(i).setLocation(LINE);
				result.put(resultRank, list.get(i));
				list.remove(i);
				resultRank++;
			}
		}
	}

	public boolean getStop() {
		return (result.size() == size);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!getStop()) {
			GameSituation();
			setRank();
			setLocation();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		resultView();
	}

	public void resultView() {
		Set<Integer> key = result.keySet();
		Iterator<Integer> mapKey = key.iterator();
		while (mapKey.hasNext()) {
			int rank = mapKey.next();
			System.out.println(result.get(rank).getName() + "말, " + rank + "등");
		}
	}

	/**
	 * @return the resultRank
	 */
	public int getResultRank() {
		return resultRank;
	}

	/**
	 * @param resultRank the resultRank to set
	 */
	public void setResultRank(int resultRank) {
		this.resultRank = resultRank;
	}
}