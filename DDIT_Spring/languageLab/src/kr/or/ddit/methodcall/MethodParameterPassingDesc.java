package kr.or.ddit.methodcall;

/**
 * 메소드 호출시 인자(argument) 전달 방법
 * parameter vs argument
 * 	ex) int number = 3; -- 기본형 변수. 레퍼런스(주소)가 존재하지 않음
 * 		Object obj = new Object(); 메모리 3개
 * 		1. 클래스에 먼저 적제(가비지 안돼)
 * 		2. 힙 메모리에 레퍼런스 하나 만들어짐.
 * 		3. 그 레퍼런스를 obj 에게 줌. 이 때 이 obj에 메모리 공간을 줌.
 * 
 * call by value : parameter 타입이 기본형. (copy value)
 * call by reference : parameter 타입이 참조형. (copy reference)
 * 
 */
public class MethodParameterPassingDesc {

	public static void main(String[] args) {
//		파라미터 =  식별자
//		인자 = 식별자를 통해 전달되는 데이터
		int number = 10;
		method1(number);
		System.out.printf("number : %d", number);
		
		StringBuffer sb = new StringBuffer("ORIGINAL");
//		1. sb는 original 객체의 주소값을 가지고 있음.
		
		method2(sb);
//		2. sb의 주소값을 넘김
		
		System.out.printf("sb : %s", sb);
//		4. sb를 가져오니 이미 어펜드 되어있음
	}
	
	private static void method1(int number) {
		number = number + 1;
	}
	
	// 여기서 넘버는 값만 복사했으니까. 각자 다른 넘버 메모리 영역을 가지고 있음.
	
	private static void method2(StringBuffer sb) {
		sb.append(" APPEND ");
//		3. 넘어온 sb 주소값 객체에 어펜드함
	}
	
	// 값이냐 주소냐.
	
//	리턴 타입이 없어도 call by reference 가 가능함
}
