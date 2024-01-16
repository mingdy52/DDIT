package basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {

		// 접근할 클래스로 만든 객체 생성
		Service service = new Service();

		// 클래스 정보를 담은 클래스 객체 생성
		Class<?> klass = service.getClass();

		// 리플렉션을 이용한 메서드 정보 접근하기
		Method[] declaredMethods = klass.getDeclaredMethods();

		for (Method m : declaredMethods) {

			System.out.println(m.getName()); // 메서드명 출력

			Annotation[] annos = m.getAnnotations();

			for (Annotation anno : annos) {
				if (anno.annotationType().getSimpleName().equals("PrintAnnotation")) {

					PrintAnnotation printAnn = (PrintAnnotation) anno;
					for (int i = 0; i < printAnn.count(); i++) {
						System.out.print(printAnn.value());
					}
				}

			}
			System.out.println(); // 줄바꿈 처리
			
			//m.invoke(service);
			
			Class<?> clazz = Service.class;
			
			service = (Service) clazz.newInstance();
			
			m.invoke(service); // 객체 만들어서 실행
		}
	}
}
