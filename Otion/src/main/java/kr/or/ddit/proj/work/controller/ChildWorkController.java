package kr.or.ddit.proj.work.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.work.service.WorkService;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/project/{pjId}/work/{workNum}/form")
public class ChildWorkController {

	@Inject
	private WorkService service;
	
	//workReq=gogo, workTitle=ㅁㄴㅇㅁㅇ, workPriority=요청, memId=black:, 
//	startDate=2022-08-23, endDate=2022-08-25, workStatusCode=낮음, workContent=dfdsfqweasdasd
	//@RequestBody WorkVO work 로 안받아지는 경우에 대한 해결이 필요함
	@GetMapping(produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
	public List<WorkVO> child(@RequestParam Map<String,String> map, @PathVariable("pjId") String pjId,@PathVariable("workNum") String workNum) {
		log.info("map : " + map.toString());
		
		WorkVO work = new WorkVO();
		
		work.setWorkReq(map.get("workReq"));
		work.setWorkTitle(map.get("workTitle"));
		work.setWorkPriority(map.get("workPriority"));
		work.setMemId(map.get("memId"));
		work.setWorkStart(map.get("workStart"));
		work.setWorkEnd(map.get("workEnd"));
		work.setWorkStatusCode(map.get("workStatusCode"));
		work.setWorkContent(map.get("workContent"));
		
		
		PagingVO<WorkVO> paging = new PagingVO<>(1,2);
		paging.setDetailCondition(work);
		paging.getDetailCondition().setPjId(pjId);
		if(workNum!="000") {
		paging.getDetailCondition().setParentWorkNum(workNum);}
		service.insertWork(paging.getDetailCondition());
	    return service.findAll(paging);
	
	}
	
	
}
