package basic;

import java.util.ArrayList;
import java.util.List;

public class T01_ArrayListTest {
	public static void main(String[] args) {
		
		// 기본용량: 10
		List list1 = new ArrayList();
		
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(true);
		list1.add(12.34);
		
		// size() => 데이터 개수
		System.out.println("size => " + list1.size());
		System.out.println("list1 => " + list1);
		
		// get()으로 데이터 꺼내요기
		System.out.println("1번째 자료: " + list1.get(0));
		
		// 데이터 끼워넣기도 같다.
		list1.add(0, "zzz");
		System.out.println("list1 => " + list1);
	
		// 데이터 변경하기
		String temp = (String) list1.set(0, "yyy");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		// 삭제하기도 같다.
		list1.remove(0);
		System.out.println("0번 삭제 후: " + list1);
		
		list1.remove("bbb");
		System.out.println("bbb 삭제 후: " + list1);
		
		list1.remove(new Integer(111)); // 111만 적으면 인덱스로 인식함. 객체 생성해줘야함(숫자는 인덱스로 인식)
		System.out.println("======================================");
		
		// 제너릭을 지정하여 선언할 수 있다. 선언안하면 오브젝트
		List<String> list2 = new ArrayList<String>();
		list2.add("aaa");
		list2.add("bbb");
		list2.add("ccc");
		list2.add("ddd");
		list2.add("eee");
		
		for (int i = 0; i < list2.size(); i++) {
			System.out.println(i + " : " + list2.get(i));
			
		}
		System.out.println("======================================");
		
		// 향상된 for문(for-each)
		for (String s : list2) {
			System.out.println(s);
		}
		System.out.println("======================================");
		
		// contains(비교객체); => 리스트에 '비교객체'가 있으면 true, 없으면 false 리턴
		
		System.out.println(list2.contains("ddd"));
		System.out.println(list2.contains("ZZZ"));
		
		// indexOf(비교객체); => 리스트에서 '비교객체'를 찾아 '비교객체'가 있는 index 값을 반환한다. 없으면 -1 반환
		
		System.out.println("DDD의 index값 : " + list2.indexOf("ddd"));
		System.out.println("ZZZ의 index값 : " + list2.indexOf("ZZZ"));
		System.out.println("======================================");
		
		Object[] strArr = list2.toArray();
//		String[] strArr2 = (String[]) list2.toArray();
		System.out.println("배열의 개수 : " + strArr.length);
		
		// toArray()에서 캐스팅은 되지 않는다. 
		
//		** 리스트의 제너릭 타입에 맞는 자료형의 배열로 변환하는 방법
//		제너릭 타입의 0개짜리 배열을 생성해서 매개변수로 넣어준다.
//		형식) toArray(new 제너릭타입[0]) 또는 toArray(new 제너릭타입[] {})
		
		String[] strArr2 = list2.toArray(new String[0]);
									 // ArrayList의 요소를 지정된 요소 형식의 새 배열에 복사
		System.out.println("strArr2의 개수 : " + strArr2.length);
		
		for (int i = 0; i < list2.size(); i++) {
			list2.remove(i);
			
		}
		System.out.println(list2.size()); // 0으로 만드려면 끝에서부터 지우던가 증가를 없애던가
	}
}
