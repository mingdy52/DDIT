package kr.or.ddit.designpattern.adapterpattern;

public class Adaptee {
	public void specificRequest() {
		System.out.println("Target 과 전혀 관계가 없는 다른 구조의 객체에 의해 요청이 처리됨.");
	}
}
