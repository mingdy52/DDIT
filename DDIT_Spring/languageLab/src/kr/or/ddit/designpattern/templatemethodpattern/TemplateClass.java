package kr.or.ddit.designpattern.templatemethodpattern;

/**
 * 
 * Template method pattern
 * 	: 특정 작업의 단계가 명확히 고정되어있고, 해당 단계 내의 세부 작업의 내용이 여러 형태가 존재할 때 사용하는 구조 패턴.
 * 
 * 1. template class : 작업의 단계를 정의하고 있는 클래스로 재정의 하지 않음.
 * 		1) template method : 작업의 단계 정의
 * 		2) hook method(abstract) : 단계 내의 세부 작업 정의
 * 2. concrete class : template 내에 정의된 단계에서 어느 한 세부 단계의 작업을 정의하고 있는 클래스.
 *
 */
public abstract class TemplateClass {
//	하나의 추상클래스에 하나 이상의 추상 메소드가 있어야함
	
	public final/*오버라이딩 불가*/ void template() {
		// 실제 일은 안하지만 일을하는 순서만 결정
		stepOne();
		stepTwo();
		stepThree();
	}
	
	private void stepOne() {
		System.out.println("1단계");
	}
	
	protected/*상속 구조 내에서만 접근 허용*/ abstract void stepTwo();
	
	private void stepThree() {
		System.out.println("3단계");
	}
	
}
