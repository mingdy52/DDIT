package day02;

public class OopTest {
	public static void main(String[] args) {
		Animal ani = new Animal();
		System.out.println(ani.age);
		ani.getOld();
		
		Cat cat = new Cat();
		System.out.println(cat.age);
		System.out.println(cat.ssagaji);
		cat.getOld();
		cat.chur();
		System.out.println(cat.age);
		System.out.println(cat.ssagaji);
		
	}
}
