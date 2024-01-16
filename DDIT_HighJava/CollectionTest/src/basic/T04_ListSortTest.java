package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T04_ListSortTest {
	public static void main(String[] args) {
		List<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "변학도", "010-1111-2222"));
		memList.add(new Member(9, "성춘향", "010-1111-3333"));
		memList.add(new Member(3, "이순신", "010-1111-4444"));
		memList.add(new Member(6, "강감찬", "010-1111-5555"));
		memList.add(new Member(2, "일지매", "010-1111-6666"));
		
		System.out.println("정렬전");
		for (Member member : memList) {
			System.out.println(member);
		}
		System.out.println("---------------------------------------------");
		
		Collections.sort(memList); // 정렬하기
		
		System.out.println("정렬 후(이름 오름차순)");
		for (Member member : memList) {
			System.out.println(member);
		}
		System.out.println("---------------------------------------------");
		
		Collections.sort(memList, new SortNumDesc());
		
		System.out.println("정렬 후(번호 내림차순)");
		for (Member member : memList) {
			System.out.println(member);
		}
		System.out.println("---------------------------------------------");
		
	}
}

class Member implements Comparable<Member> {

	private int num; // 번호
	private String name; // 이름
	private String tel; // 전화번호

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	
	

	public int getNum() {
		return num;
	}



	public void setNum(int num) {
		this.num = num;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}


	// 이름을 기준으로 오름차순 정렬이 되도록 설정한다. 
	@Override
	public int compareTo(Member o) {
		return getName().compareTo(o.getName());
	}

}

// membere 의 번호(num)의 내림차순으로 정렬하기
class SortNumDesc implements Comparator<Member>{

	@Override
	public int compare(Member o1, Member o2) {
//		if(o1.getNum() > o2.getNum()) {
//			return -1;
//		} else if (o1.getNum() == o2.getNum()) {
//			return 0;
//		} else {
//			return 1;
//		}
		
		// wrapper 클래스에서 제공하는 메서드를 이용하는 방법
		return new Integer(o1.getNum()).compareTo(o2.getNum()) * -1;
	}
	
}