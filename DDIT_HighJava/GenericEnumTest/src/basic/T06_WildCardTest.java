package basic;

import java.util.ArrayList;
import java.util.List;

public class T06_WildCardTest {
	
	// 장바구니 항목조회를 위한 메서드(모든 항목)
	public static <T> void displayCartItemInfo(Cart<T> cart ) {
		System.out.println(" = 음식류 장바구니 항목 리스트= ");
		for (Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("-----------------------------------------");
	}
	
	// 장바구니 항목조회를 위한 메서드(음료나 그 하위)
	public static void displayCartItemInfo2(Cart<? extends Drink> cart ) {
		System.out.println(" = 음식류 장바구니 항목 리스트= ");
		for (Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("-----------------------------------------");
	}
	
	// 장바구니 항목조회를 위한 메서드(고기나 그 상위)
	public static void displayCartItemInfo3(Cart<? super Meat> cart ) {
		System.out.println(" = 고기류외 음식 장바구니 항목 리스트= ");
		for (Object obj : cart.getList()) {
			System.out.println(obj);
		}
		System.out.println("-----------------------------------------");
	}
	
	public static void main(String[] args) {
		Cart<Food> foodCart = new Cart<Food>();
		
		foodCart.addItem(new Meat("돼지고기", 5000));
		foodCart.addItem(new Meat("소고기", 10000));
		foodCart.addItem(new Juice("오렌지쥬스", 1500));
		foodCart.addItem(new Coffee("아메리카노", 2000));
		
		Cart<Meat> meatCart = new Cart<>();
		
		meatCart.addItem(new Meat("돼지고기", 5000));
		meatCart.addItem(new Meat("소고기", 10000));
//		meatCart.addItem(new Juice("오렌지쥬스", 1500));
		
		Cart<Drink> drinkCart = new Cart<>();
		
		drinkCart.addItem(new Juice("오렌지쥬스", 1500));
		drinkCart.addItem(new Coffee("아메리카노", 2000));
//		drinkCart.addItem(new Meat("소고기", 10000));
		
		Cart<Coffee> coffeeCart = new Cart<>();
		
		coffeeCart.addItem(new Coffee("아메리카노", 2000));
		coffeeCart.addItem(new Coffee("에스프레소", 1500));
		coffeeCart.addItem(new Coffee("카라멜마끼야또", 5000));


		
		displayCartItemInfo(foodCart);
		displayCartItemInfo(meatCart);
		displayCartItemInfo(drinkCart);
		
//		displayCartItemInfo2(foodCart);
//		displayCartItemInfo2(meatCart);
		displayCartItemInfo2(drinkCart);

		displayCartItemInfo3(foodCart);
		displayCartItemInfo3(meatCart);
//		displayCartItemInfo3(drinkCart);
	}
}

class Food {
	private String name; // 음식 이름
	private int price; // 가격
	
	public Food(String name, int price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return this.name + "(" + this.price + "원)";
	}
	
	
}

// 음료
class Drink extends Food {
	public Drink(String name, int price) {
		super(name, price);
	}
}

// 쥬스
class Juice extends Drink {
	public Juice(String name, int price) {
		super(name, price);
	}
}

// 커피
class Coffee extends Drink {
	public Coffee(String name, int price) {
		super(name, price);
	}
}

// 고기
class Meat extends Food {
	public Meat(String name, int price) {
		super(name, price);
	}
}

// 장바구니
class Cart<T> {
	private List<T> list = new ArrayList<T>();
	
	public List<T> getList(){
		return this.list;
	}
	
	public void addItem(T item) {
		list.add(item);
	}
}
