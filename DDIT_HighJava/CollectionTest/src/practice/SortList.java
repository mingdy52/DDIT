package practice;

import java.util.Comparator;

public class SortList implements Comparable<SortList> {
								// 정렬의 기준을 정하기 위해 사용
	private int studentNum;
	private String name;
	private int korean;
	private int english;
	private int math;
	private int total;
	private int rank;

	public SortList(int studentNum, String name, int korean, int english, int math) {
		this.studentNum = studentNum;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
	}
	

	public int getTotal() {
		total = korean + english + math;
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}

	

	public int getStudentNum() {
		return studentNum;
	}


	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}


	@Override
	public String toString() {
		return "studentNum=" + studentNum + " name=" + name + " korean=" + korean + " english=" + english + " math="
				+ math + "";
	}

	@Override
	public int compareTo(SortList o) {
		if (this.studentNum < o.studentNum) { // studentNum이 정렬의 기준
			return -1;
		} else if (this.studentNum == o.studentNum) {
			return 0;
		} else {
			return 1;
		}

	}
	

}
