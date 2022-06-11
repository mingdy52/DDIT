package day02;

public class OopTest2 {
	
	public static void main(String[] args) {
		Putin putin = new Putin();
		System.out.println(putin.cnt_nuclear);
		System.out.println(putin.money);
		System.out.println(putin.army_power);
		
		putin.command();
		putin.galguda(5);
		putin.goToUkraine();
		
		System.out.println(putin.cnt_nuclear);
		System.out.println(putin.money);
		System.out.println(putin.army_power);
	}
	
}
