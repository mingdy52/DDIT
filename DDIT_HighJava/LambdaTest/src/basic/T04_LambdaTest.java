package basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class T04_LambdaTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		list.add("홍길동");
		list.add("이순신");
		list.add("변학도");
		
		for (String str : list) {
			System.out.println(str);
		}
		
		list.forEach(new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
			}
			
		});
		
		list.forEach((t) -> System.out.println(t));
		
	}
}
