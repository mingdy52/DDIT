package kr.or.ddit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/*-context.xml") 
	, @ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
// 어플리케이션에서 사용할 컨테이너. 웹용 컨테이너가 필요함.
@WebAppConfiguration("file:webapp")
@Transactional
public abstract class AbstractTest {
	
}
