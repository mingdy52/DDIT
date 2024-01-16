package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;



public class SortListExample {

	public static void main(String[] args) {

		SortList sl = new SortList(101, "김태리", 80, 50, 30);
		SortList sl2 = new SortList(104, "남주혁", 60, 70, 80);
		SortList sl3 = new SortList(102, "이주명", 90, 95, 100);
		SortList sl4 = new SortList(103, "최현욱", 40, 30, 45);
		SortList sl5 = new SortList(105, "한지민", 80, 45, 35);
		
		
		List<SortList> list = new ArrayList();

		list.add(sl);
		list.add(sl2);
		list.add(sl3);
		list.add(sl4);
		list.add(sl5);

		System.out.println(list);
		
		Collections.sort(list);
		System.out.println("\n학번 오름차순");
		System.out.println(list + "\n");
		
		int a = sl.getTotal();
		int a2 = sl2.getTotal();
		int a3 = sl3.getTotal();
		int a4 = sl4.getTotal();
		int a5 = sl5.getTotal();
		System.out.println(a+"|"+a2+"|"+a3+"|"+a4+"|"+a5);
		
		Collections.sort(list, new Desc2());
		System.out.println("외부생성자 총점 내림차순");
		System.out.println(list);

	}
	

}

class Desc2 implements Comparator<SortList> {
	
	@Override
	public int compare(SortList o1, SortList o2) {
		if(o1.getTotal() < o2.getTotal()) {
			return o1.getTotal() * -1;
		} else if(o1.getTotal() == o2.getTotal()) {
			
			if (o1.getStudentNum() < o2.getStudentNum()) {
				return 1;
			} else if (o1.getStudentNum() == o2.getStudentNum()) {
				return 0;
			} else {
				return -1;
			}
			
		}
		return 0;
	}
	
}
