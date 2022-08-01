package basic;

import java.util.ArrayList;
import java.util.List;

/*
 * 상한 및 하한 제한 와일드 카드 예제
 */
public class T05_WildCardTest {
	public void printMemberInfo(List<? extends Member> list) {
		/*
		 * extends 키워드를 이용한 상한 제한(Upper Bounds) 예제 list 안의 객체는 반드시 Member 타입의 객체임을 보장할 수
		 * 있다.
		 */

		for (Member mem : list) {
			System.out.println(mem);
		}
	}
	
	 // 값을 보여줄때는  해당 데이터 타입(Member)와 같거나 작은 것에서만 꺼내서 출력가능함
	public void printMemberInfo2(List<? extends Member> list) {
		/*
		 * super 키워드를 이용한 하한 제한(lower bounds) Member 타입의 변수를 이용하여 list로부터 객체를 꺼내올 수 없다.
		 */

		for (Member mem : list) {
			System.out.println(mem);
		}
	}
	 //하지만 값을 list에 넣을때는 Member보다 하위에는 넣지못하지만 상위로는 넣을 수 있음(자동 캐스팅이 됨)
	
	/*
	 * 회원정보 등록
	 * 
	 * @param list
	 */
	public void registerMemberInfo(List<? extends Member> list) {
		/*
		 * Member 타입의 객체라고 항상 list에 추가할 수 있음을 보장해 주지 않는다. 
		 * (Member 타입 또는 Member를 상속한 그 어떤 타입을 의미하므로 아직 구체적인 타입이 정해지지 않았다.) 
		 *  => 컴파일 에러 발생
		 */

		Member mem = new Member("홍길동", 33);
		list.add(mem); // Member 타입 객체 추가 불가
	}

	 // 자기보다 상위 타입은 뭐가 나올지 모름
	public void registerMemberInfo2(List<? super Member> list) {
		/*
		 * super키워드를 이용한 하한 제한 예제 list는 Member 타입의 객체를 포함한다는 것을 보장 받는다. 
		 * => Member 타입 또는  Member 타입의 슈퍼타입을 담은 리스트를 의미함
		 */

		Member mem = new Member("홍길동", 33);
		list.add(mem); // Member 타입 객체 추가 가능함

	}
	
	public static void main(String[] args) {
		T05_WildCardTest wc = new T05_WildCardTest();
		List<Member> memlist = new ArrayList<Member>();
		wc.registerMemberInfo2(memlist);
		wc.printMemberInfo(memlist);
	}
}

/*
 * 회원정보
 */
class Member {
	private String name;
	private int age;

	public Member(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", age=" + age + "]";
	}

}