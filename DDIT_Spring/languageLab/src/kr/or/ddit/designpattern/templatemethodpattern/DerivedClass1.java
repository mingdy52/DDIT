package kr.or.ddit.designpattern.templatemethodpattern;

public class DerivedClass1 extends TemplateClass {

	@Override
	protected void stepTwo() {
		System.out.println("A형태의 2단계");
	}

}
