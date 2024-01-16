package kr.or.ddit.proj.issue.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.common.attach.service.AttatchService;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.proj.colleague.service.ColleagueService;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.issue.service.IssueService;
import kr.or.ddit.proj.issue.vo.IssueReplyVO;
import kr.or.ddit.proj.issue.vo.IssueVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/project/{pjId}/work/{workNum}/issue")
@Slf4j
public class ProjDetailIssue {

	@Inject
	IssueService service;

	@Inject
	private ColleagueService colleagueService;
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public IssueVO issueListJSON(@ModelAttribute IssueVO issue, Authentication auth) {
		log.info("IssueVO$$$$$$$$$$$$$$$$$$$$$$$$$" + issue);
		if (ObjectUtils.isNotEmpty(issue.getIssueAttatch())) {
			AttatchVO attatch = new AttatchVO(issue.getIssueAttatch());
			attatch.setUploaderId(auth.getName());
			issue.setAttatch(attatch);
			attatch.setAttatchOrder(1);

		}
		
		return service.insertIssue(issue);
	}

}
