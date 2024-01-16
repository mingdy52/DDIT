package kr.or.ddit.memo.async;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.memo.MemoVO;
import kr.or.ddit.memo.service.MemoService;

@RestController
@RequestMapping(value="/memo", produces=MediaType.APPLICATION_JSON_UTF8_VALUE) // 여기에서 제이슨을 만드는 이외의 작업은 아무것도 할 수 없음.
public class MemoRestController {
	public static Map<Integer, MemoVO> memoTable = new LinkedHashMap<>();
	
	private MemoService service;
	
	@Inject
	public void setService(MemoService service) {
		this.service = service;
	}
	
	@GetMapping // 주소, responseBody(응답 데이터를 마샬링) 가 생략되어 있음.
	public Collection<MemoVO> list() {
		Map<Integer, MemoVO> memoList = service.retrieveMemoList();
		return memoList.values();
	}
	
	@PostMapping
	public MemoVO insert(@RequestBody/*요청데이터를 언마샬링*/ MemoVO memo) { // 응답이 넘어가지 않으니까 modelattribute어노테이션을 사용할 필요 없음.
		service.createMemo(memo);
		int lastCode = memoTable.keySet().stream()
										.mapToInt(key->key.intValue()) // key integer 를 int로 바꿈.
										.max()
										.orElse(0); // max가 없을 때 쓸 수 있는 기본값
		memo.setCode(lastCode+1);
		memoTable.put(memo.getCode(), memo);
		return memo; 
	}
	
	@PutMapping("{code}") // 경로 변수로 code를 받음.
	public MemoVO update(@RequestBody MemoVO memo
			, @PathVariable int code) {
//				요청 주소에서 필요한 데이터를 찾음. 경로 변수 이름과 파라미터이름이 동일하면 name 생략 가능*
		memo.setCode(code);
		memoTable.put(code, memo);
		return memo;
	}
	
	@DeleteMapping("{code}")
	public MemoVO delete(@PathVariable int code) {
		return memoTable.remove(code);
	}
	
}
