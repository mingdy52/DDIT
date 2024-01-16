package kr.or.ddit.basic;

import java.util.Comparator;

public class Student implements Comparable<Student> {
	private int studentNum;
	private String name;
	private int sub1;
	private int sub2;
	private int sub3;
	private int totalNum;
	private int grade;
	
	public Student(int studentNum, String name, int sub1, int sub2, int sub3) {
		super();
		this.studentNum = studentNum;
		this.name = name;
		this.sub1 = sub1;
		this.sub2 = sub2;
		this.sub3 = sub3;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSub1() {
		return sub1;
	}

	public void setSub1(int sub1) {
		this.sub1 = sub1;
	}

	public int getSub2() {
		return sub2;
	}

	public void setSub2(int sub2) {
		this.sub2 = sub2;
	}

	public int getSub3() {
		return sub3;
	}

	public void setSub3(int sub3) {
		this.sub3 = sub3;
	}

	public int getTotalNum() {
		totalNum=sub1+sub2+sub3;
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "\n"+studentNum +", "+name+", "+sub1+", "+sub2+", "+sub3+"\n";
	}

	@Override
	public int compareTo(Student o) {
		if(studentNum>o.getStudentNum()) {
			return getStudentNum()*1;
		}else if(studentNum<o.getStudentNum()) {
			return getStudentNum()*-1;
		}
		if(totalNum==((Student) o).getTotalNum()) {
			return getStudentNum()*-1;
		}
		return 1;
	}
}
