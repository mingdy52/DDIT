package kr.or.ddit.vo;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;

public class MemberVOTest {
	public static Validator validator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
		validator = Validation.byDefaultProvider()
		        .configure()
		        .messageInterpolator(
		                new ResourceBundleMessageInterpolator(
		                        new PlatformResourceBundleLocator( "kr.or.ddit.msgs.errorMessage" )
		                )
		        )
		        .buildValidatorFactory()
		        .getValidator();
	}
	
	@Test
	public void testBundle() {
		ResourceBundle bundle = ResourceBundle.getBundle("kr.or.ddit.msgs.message");
		ResourceBundle bundle2 = ResourceBundle.getBundle("kr.or.ddit.msgs.message", Locale.ENGLISH);
//		베이스 네임은 kr 부터. 확장자 빼고. 로케일 배고 넣기
		System.out.println(bundle.getString("hi"));
		System.out.println(bundle2.getString("hi"));
		
	}

	@Test
	public void test() {
		MemberVO target = new MemberVO();
//		target.setMemId("a001");
//		target.setMemPass("ab");
//		target.setMemMail("abcd");
//		target.setMemRegno1("134");
		
		
		Set<ConstraintViolation<MemberVO>> violations = validator.validate(target);
		for(ConstraintViolation<MemberVO> singleViloation : violations) {
			String property = singleViloation.getPropertyPath().toString();
			String message = singleViloation.getMessage();
			System.out.printf("%s -%s \n", property, message);
		}
	}

}

