package kr.or.ddit.proj.issue.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.attach.service.AttatchService;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.issue.dao.IssueDAO;
import kr.or.ddit.proj.issue.vo.IssueReplyVO;
import kr.or.ddit.proj.issue.vo.IssueVO;
import kr.or.ddit.proj.work.dao.WorkDAO;
import kr.or.ddit.proj.work.vo.ReplyVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IssueServiceImpl implements IssueService{

	@Inject
	IssueDAO issueDAO;
	@Inject
	WorkDAO workDAO;
	@Inject
	AttatchService attatchService;

	@Inject
	AttatchDAO attatchDAO;

	@Inject
	WebApplicationContext root;
	
	@Transactional
	@Override
	public IssueVO insertIssue(IssueVO issue) {

		issueDAO.insertIssue(issue);
		
		if(ObjectUtils.isNotEmpty(issue.getAttatch())) {
		String path = root.getServletContext().getRealPath("/resources/issueImage/");
		log.info("path@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+path);
		File saveFolder = new File(path);
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}	
		
		issue.saveTo(saveFolder);
		
		
		issue.getAttatch().setAttatchPlace(issue.getIssueNum());
		issue.getAttatch().setFilePath(issue.getFilePath());
		issue.getAttatch().setSaveNm(issue.getIssueImg());
		attatchService.createAttachNumReplyAttatch(issue.getAttatch());
		
		}
		return issueDAO.findIssue(issue); 
		
	}

	@Override
	public List<IssueVO> findAllIssue(IssueVO issue) {
		
		return issueDAO.findAllIssue(issue);
	}
	
	public List<WorkVO> findAll(PagingVO<WorkVO> pagingVO) {
		int totalRecord = issueDAO.selectIssueCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<WorkVO> dataList = issueDAO.selectIssueList(pagingVO);
		pagingVO.setDataList(dataList);
		return dataList;
	}

	@Override
	public IssueVO findIssue(IssueVO issue) {
		return issueDAO.findIssue(issue); 
	}

	@Override
	public List<IssueReplyVO> allIssueReplyList(IssueReplyVO reply) {
	
		return issueDAO.allIssueReplyList(reply);
	}


	@Transactional
	@Override
	public List<IssueReplyVO> insertReply(IssueReplyVO issueReply) {
		ReplyVO reply = new ReplyVO();
		reply.setWorkReq(issueReply.getWorkReq()); 
		reply.setPjId(issueReply.getPjId());
		issueReply.setSolverNum(workDAO.selectColNum(reply));
		issueDAO.insertReply(issueReply);
		return issueDAO.allIssueReplyList(issueReply);
	}

	@Transactional
	@Override
	public List<IssueReplyVO> modifyReply(IssueReplyVO issueReply) {
		issueDAO.modifyReply(issueReply);
		return issueDAO.allIssueReplyList(issueReply);
	}
	
	@Transactional
	@Override
	public List<IssueReplyVO> removeReply(IssueReplyVO issueReply) {
		issueDAO.removeReply(issueReply);
		return issueDAO.allIssueReplyList(issueReply);
	}

	@Transactional
	@Override
	public void deletIssue(IssueVO issue) {
		workDAO.deleteIssueReply(issue);
		attatchDAO.deleteAttatchReplaceFile(issue.getIssueNum());
		issueDAO.deleteIssue(issue);
		
	}
}
