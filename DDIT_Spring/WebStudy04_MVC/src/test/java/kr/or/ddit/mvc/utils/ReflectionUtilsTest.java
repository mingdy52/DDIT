package kr.or.ddit.mvc.utils;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReflectionUtilsTest {
	
	String basePackages = "kr.or.ddit";
	
	@Test
	public void testGetFieldsWithAnnotationWithClass() {
		fail("Not yet implemented");
	}

	@RequestMapping("/test.do")
	public String sample1(HttpServletRequest req, HttpServletResponse resp) {
		return "";
	}

	@RequestMapping(value="/test.do", method=RequestMethod.POST)
	public String sample2(HttpServletRequest req, HttpServletResponse resp) {
		return "";
	}
	
	public String sample3(HttpServletRequest req, HttpServletResponse resp) {
		return "";
	}
	
	@Test
	public void testGetMethodsWithAnnotationAtClass() {
		Map<Method, RequestMapping> methods = ReflectionUtils.getMethodsWithAnnotationAtClass(
				ReflectionUtilsTest.class, RequestMapping.class, 
				String.class, HttpServletRequest.class, HttpServletResponse.class);
		methods.entrySet().stream().forEach((entry)-> {
			Method method = entry.getKey();
			RequestMapping annotation = entry.getValue();
			log.info("url : {}, method : {} \n 요청 처리 핸들러 메소드 : {}", annotation.value(), annotation.method(), method);
		});
	}

	@Test
	public void testGetMethodsAtClass() {
		List<Method> methods = ReflectionUtils.getMethodsAtClass(
				ReflectionUtilsTest.class, String.class, HttpServletRequest.class, HttpServletResponse.class);
		methods.stream().forEach((singleMethod)-> {
			log.info("수집된 메소드 정보 : {}", singleMethod);
		});
	}

	@Test
	public void testGetClassesWithAnnotationAtBasePackages() {
		Map<Class<?>, Controller> classes 
		= ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
		classes.entrySet().stream().forEach((entry)->{
			Class<?> singleClass = entry.getKey();
			Controller annotation = entry.getValue();
			log.info("수집된 클래스 정보 : {}, 어노테이션 정보 : {}", singleClass.getName(), annotation);
		});
	}
	@Test
	public void testGetAllClassesAtBasePackages() {
		List<Class<?>> classes = ReflectionUtils.getAllClassesAtBasePackages(basePackages);
		classes.stream().forEach((singleClass) -> {
			log.info("수집된 클래스 정보 : {}", singleClass.getName());
		});
	}

}
