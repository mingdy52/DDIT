package kr.or.ddit.designpattern.templatemethodpattern;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemplateClassTest {

	@Test
	public void testTemplate() {
		TemplateClass Derived1 = new DerivedClass1();
		TemplateClass Derived2 = new DerivedClass2();
		TemplateClass[] array = new TemplateClass[] {Derived1, Derived2};
		
		for(TemplateClass single : array) {
			single.template();
		}
	}

}
