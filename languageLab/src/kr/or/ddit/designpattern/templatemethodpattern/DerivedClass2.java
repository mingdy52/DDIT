package kr.or.ddit.designpattern.templatemethodpattern;

public class DerivedClass2 extends TemplateClass {

	@Override
	protected void stepTwo() {
		System.out.println("B형태의 2단계");
	}
}
