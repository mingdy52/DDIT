package pactice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class Hotel2 implements Serializable {
	static Scanner sc = new Scanner(System.in);
	static Map<Integer, String> map = new HashMap<Integer, String>();
	static ObjectOutputStream oos = null;
	static ObjectInputStream ois = null;

	
	public static void main(String[] args) {

		open();
		
		System.out.println("쓰기 작업 완료");
		
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************\n");

		while (true) {
			System.out.println("*******************************************");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			System.out.println("*******************************************");
			System.out.print("메뉴선택 => ");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				checkIn();
				break;

			case 2:
				checkOut();
				break;

			case 3:
				roomState();
				break;

			case 4:
				close();
				return;
				
			default:
				System.out.println("잘못된 입력입니다.\n");
				break;
				
			}

		}

	}
	private static void open() {
		try {
			ObjectInputStream ois;
			ois = new ObjectInputStream(
					new BufferedInputStream(
					new FileInputStream("d:/D_Other/hotel.bin")));
		
			Object obj;
			
			if ((obj = ois.readObject())!=null) {
				map = (Map<Integer, String>) obj;
			}
			ois.close();
			
		}catch (IOException e) {
	
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static void close() {
		
		 try {
			 	ObjectOutputStream oos = new ObjectOutputStream(
						new BufferedOutputStream( // 부스터
								new FileOutputStream("d:/D_Other/hotel.bin")));
				oos.writeObject(map);				
				oos.close();
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
		System.out.println();
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
		return;
	}
	private static void checkIn() {
		System.out.println("\n어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = sc.nextInt();
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String name = sc.next();

		if (map.get(roomNum) != null) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
		}

		map.put(roomNum, name);
		System.out.println("체크인 되었습니다.");
		System.out.println();

	}

	private static void checkOut() {
		System.out.println("\n어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = sc.nextInt();

		if (map.get(roomNum) == null) {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			return;
		} else {
			map.remove(roomNum);
			System.out.println("체크아웃 되었습니다.\n");
		}
	}

	private static void roomState() {
		Set<Integer> keySet = map.keySet();

		if (keySet.size() == 0) {
			System.out.println("숙박중인 고객이 없습니다.");
		} else {

			int cnt = 1;
			for (Integer key : keySet) {
				System.out.println(cnt + "\t방번호 : " + key + "\t투숙객 : " + map.get(key));
				cnt++;
			}
		}
		System.out.println();

	}

}
