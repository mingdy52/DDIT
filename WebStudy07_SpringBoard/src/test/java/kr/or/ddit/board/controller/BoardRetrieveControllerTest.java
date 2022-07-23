package kr.or.ddit.board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.AbstractTest;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.common.vo.PagingVO;

public class BoardRetrieveControllerTest extends AbstractTest {
	
	// 클라이언트가 없으니까 모조 객체를 만들어 준다. -> mock
	@Inject
	WebApplicationContext context;
	private MockMvc mokMvc;
	
	@Before
	public void setUp() {
		mokMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testGetBoardList() throws Exception {
		// given
		// when
//		mokMvc.perform(get("/board").accept(MediaType.ALL))
//			.andExpect(status().isOk())
//			.andExpect(view().name("board/boardView"))
////			.andExpect(model().attribute(name, matcher)) // 여기선 모델이 필요 없음
//			.andDo(log());
		get("");
		//then
	}

	@Test
	public void testPostBoard() throws Exception {
		// given
		// when
//		mokMvc.perform(get("/board").accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk())
//			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//			.andDo(log());
		//then
	}

}
