package kr.or.ddit.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Objects;

import kr.or.ddit.reflect.ReflectionTest;

/**
 * Refelction??
 * 		: 객체로부터 객체의 구성 요소, 특징, 속성, 형태들을 찾아가는 과정. 
 * 		  변수와 메소드, 즉 변수로부터 그 변수의 모태가 되는 클래스를 찾아가는 것. java.reflect 패키지로 지원.
 * 
 *
 */
public class ReflectionDesc {
	
	public static void main(String[] args) {
		Object obj = ReflectionTest.getObject(); // 우리가 가진 붕어빵. 붕어빵 안에 어떤 객체가 들었는지 모르니까 obj인 것임.
		System.out.println(obj);
		
		Class type = obj.getClass();
		System.out.println(type); // 몰랐는데 MemberVO 타입이란거 알아냄.
//		Field[] fields = type.getFields(); // 객체가 가진 전역변수(프로퍼티)들.
		// 퍼블릭 필드만 가져올 수 있음.
		Field[] fields = type.getDeclaredFields();
		
		for (Field fld : fields) {
			Class fldType = fld.getType(); // 전역변수의 타입 알아보기
			String fldName = fld.getName(); // 전역변수의 이름 알아보기
			
//			memId -> getMemId
			String getterName = "get" + fldName.substring(0,1).toUpperCase() + fldName.substring(1);
			
			try {
				Method getter = type.getMethod(getterName);
				// 리플렉션은 불확실성을 기반으로 함. 알고 있는게 없으니까! -> 다양한 예외 처리가 필요함.
				
				Object returnValue = getter.invoke(obj); //(오브젝트, 인자)
				// member.getMemId();
				// 어떤 객체로 돌아올지 모르니까 반환 타입도 오브젝트
				System.out.printf("%s %s = %s\n", fldType.getSimpleName(), fldName, Objects.toString(returnValue));
				
			} catch (NoSuchMethodException | SecurityException 
					| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// SecurityException 호출할 메소드가 private 일 때 발동
				e.printStackTrace();
			}
			
		}
		
		Method[] methods = type.getDeclaredMethods();
		
		for (Method mtd : methods) {
			String mtdName = mtd.getName();
			Parameter[] parameter = mtd.getParameters();
			Class mtdReturnType = mtd.getReturnType();
			
			System.out.printf("%s %s(%s)\n", mtdReturnType, mtdName, Arrays.toString(parameter));
		}
		
	}
	
}
