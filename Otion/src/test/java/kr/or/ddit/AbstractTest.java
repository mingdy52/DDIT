package kr.or.ddit;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)//junit과 spring연결
@ContextHierarchy({//만들어진 가상컨테이너를 계층구조 생성
	@ContextConfiguration("file:src/main/resources/kr/or/ddit/spring/*-context.xml")
	, @ContextConfiguration("file:webapp/WEB-INF/spring/appServlet/servlet-context.xml")
})
@WebAppConfiguration("file:webapp") //webINF위치 잡음
@Transactional    //어떠한 insert, update, delete 다 롤백해줌
public abstract class AbstractTest {

}
