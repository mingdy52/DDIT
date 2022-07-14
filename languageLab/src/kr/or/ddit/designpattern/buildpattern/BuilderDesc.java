package kr.or.ddit.designpattern.buildpattern;


/**
 * 
 * Design Pattern
 * 생성 패턴 : Singleton pattern, Factory[Method] pattern, Builder pattern
 * 구조 패턴 : Adapter(Wrapper) pattern, Facade pattern
 * 행위 패턴 : Template Method pattern, Stategy pattern
 *
 */
public class BuilderDesc {

	public static void main(String[] args) {
/*
//		1. 생성자 사용.
		SampleVO sample = new SampleVO("1번의 값", "2번의 값", "3번의 값");
		// 어떤 프로퍼티 어떤 문자가 들어가도 상관 없음(타입 안정성 없음). 자바에겐 그저 스트링 세개일 뿐임
		
//		2. javaBean 구조 사용
		SampleVO sample2 = new SampleVO();
		sample2.setProp1("1번의 값");
		sample2.setProp3("3번의 값");
//		1) 타입 안정성 보장
		2) 
//		단점 :  객체를 생성과 값 부여하는 코드, 두줄 이상 필요함.

*/
		
//		3. Builder 패턴
		
		SampleVO sample3 = SampleVO.builder().prop1("1번의 값").prop3("3번의 값").prop2("2번의 값").build();
		System.out
		.println(sample3);
		
	}
	
}
