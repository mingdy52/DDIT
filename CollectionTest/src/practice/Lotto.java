package practice;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Lotto {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random rd = new Random();
		
		
		while (true) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입\n2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("Lotto 구입 시작\n");
				System.out.println("(1000원에 로또번호 하나입니다.)");
				System.out.print("금액입력 : ");
				int price = sc.nextInt();
				System.out.println();
				int amount = price / 1000;
				int exchange = price % 1000;
				if(amount > 0) {
					System.out.println("행운의 로또번호는 아래와 같습니다.");
					for (int i=1; i<=amount; i++) {
						Set<Integer> hs = new HashSet<Integer>();
						TreeSet<Integer> ts = new TreeSet<Integer>();
						for(int j=0; j <= 5; j++) {
							int num = rd.nextInt(45)+1;
							if(!hs.add(num)) {
								--j;
							} else {
								hs.add(num);
							}
							Iterator<Integer> it = hs.iterator();
							while(it.hasNext()) {
								ts.add(it.next());
							}
							
						}
						System.out.print("로또번호" + i +" : ");
						System.out.println(ts);
					}
					System.out.printf("\n받은 금액은 %d원이고 거스름돈은 %d원입니다.\n", price, exchange);
				}
			} else {
				System.out.println("감사합니다.");
				break;
			}

		}

	}
}
