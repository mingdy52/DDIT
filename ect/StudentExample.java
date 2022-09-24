package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentExample {
	public static void main(String[] args) {

		

		List<Student> list = new ArrayList<Student>();
		list.add(new Student(20220301, "김김김", 50, 50, 50));
		list.add(new Student(20220304, "이이이", 80, 80, 80));
		list.add(new Student(20220303, "박박박", 10, 10, 10));
		list.add(new Student(20220305, "최최최",10, 10, 10));
		System.out.println("정렬 전 출력: " + list);

		Collections.sort(list);
		System.out.println("학번 오름차순 출력:" + list);

		Collections.sort(list, new Desc2());
		System.out.println("총점 역순 출력: " + list);

	}
}
class Desc2 implements Comparator<Student> {
	@Override
	public int compare(Student st1, Student st2) {
		if (st1.getTotalNum() > st2.getTotalNum()) {
			return st1.getTotalNum()*-1;
		}
		else if(st1.getTotalNum() == st2.getTotalNum()) {
			return st1.getStudentNum()*-1;
		}
		return 0;
	}
}