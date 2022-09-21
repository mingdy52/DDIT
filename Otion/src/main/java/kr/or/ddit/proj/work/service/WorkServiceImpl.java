package kr.or.ddit.proj.work.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import kr.or.ddit.proj.issue.vo.IssueVO;
import kr.or.ddit.proj.work.dao.WorkDAO;
import kr.or.ddit.proj.work.vo.ReplyVO;
import kr.or.ddit.proj.work.vo.WorkVO;
import kr.or.ddit.proj.workmark.dao.WorkMarkDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService{

	private final WorkDAO workDAO;
	
	@Inject
	private WorkMarkDAO workMarkDAO;
	@Inject
	private AttatchDAO attatchDAO;
	
	@Override
	public List<WorkVO> selectAll(PagingVO<WorkVO> paging) {
		
		return workDAO.selectWorkList(paging);
	}

	public List<WorkVO> findAll(PagingVO<WorkVO> pagingVO) {
		int totalRecord = workDAO.selectWorkCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<WorkVO> dataList = workDAO.selectWorkList(pagingVO);
		pagingVO.setDataList(dataList);
		return dataList;
	}
	
	

	@Override
	public List<WorkVO> selectOne(WorkVO work) {
		List<WorkVO> workList = new ArrayList<>(); 
		WorkVO work1=(WorkVO) workDAO.selectOne(work.getWorkNum());
		workList.add( work1);
		 if(work.getParentWorkNum()!=null&&work.getParentWorkNum().isEmpty()) {
			 WorkVO work2=(WorkVO) workDAO.selectOne(work.getParentWorkNum());
		 workList.add(work2);		 
		 }
		return workList;
	}

	@Override
	public List<ColleagueVO> selectAllMemList(String pjId) {

		return workDAO.selectAllMemList(pjId);
	}

	@Transactional
	@Override
	public int modifyWork(WorkVO work) {
		List<String> memList = new ArrayList<>();
		if(!(work.getMemId()==null&&work.getMemId().isEmpty())) {
		String mem[] = work.getMemId().trim().split(":");
		for(int i=0; i<mem.length; i++) {
			if(StringUtils.isNotBlank(mem[i])) {
		memList.add(mem[i]);
			}
		}
		work.setMemberList(memList);
		}
		log.info("work*************************"+work);
		if(work.getMemberList().size()!=0) {
			workDAO.deleteAssignment(work);
			log.info("delte통과*****************************");
			
			for(int i = 0; i < work.getMemberList().size(); i++) {
				Map<String, String> param = new HashMap<>();
				param.put("workNum", work.getWorkNum());
				param.put("pjId", work.getPjId());
				log.info("memberList***********************"+work.getMemberList().get(i));
				param.put("member", work.getMemberList().get(i));
				log.info("param!@#!@#@!#!@#!@#!@#!@#"+param.get("member"));
				workDAO.insertdeleteAssignment(param);
			}
			
//			workDAO.insertdeleteAssignment(work);
			log.info("insert통과*******************y**********");
		}
		int cnt =workDAO.modifyWork(work);
		log.info("update통과*****************************");
		
		return cnt;
	}

	@Transactional
	@Override
	public int insertWork(WorkVO work) {
		List<String> memList = new ArrayList<>();
		String mem[] = work.getMemId().split(":");
		for(int i=0; i<mem.length; i++) {
			if(StringUtils.isNotBlank(mem[i])) {
		memList.add(mem[i]);
		work.setMemId(null);
			}
		}
		log.info("paging$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+work);
		int cnt =workDAO.insertWork(work);
		work.setMemberList(memList);
		log.info("select성공 $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		
		if(work.getMemberList().size()!=0) {
		for(int i = 0; i < work.getMemberList().size(); i++) {
			Map<String, String> param = new HashMap<>();
			param.put("workNum", work.getWorkNum());
			param.put("pjId", work.getPjId());
			log.info("memberList***********************"+work.getMemberList().get(i));
			param.put("member", work.getMemberList().get(i));
			workDAO.insertdeleteAssignment(param);
		}
		}
		return cnt; 
	}

	@Override
	public List<WorkVO> selectAllWorkList(PagingVO<WorkVO> paging) {

		int totalRecord = workDAO.selectWorkCount(paging);
		paging.setTotalRecord(totalRecord);
		List<WorkVO> dataList = workDAO.selectAllWorkList(paging);
		paging.setDataList(dataList);
		return dataList;
	}

	@Override
	public List<WorkVO> retrieveMyWorkList(PagingVO<WorkVO> pagingVO) {
		// TODO Auto-generated method stub
		pagingVO.setTotalRecord(workDAO.selectMyWorkTotalRecord(pagingVO));
		List<WorkVO> dataList = workDAO.selectMyWorkList(pagingVO);
		pagingVO.setDataList(dataList);
		return dataList;
	}

	@Override
	public List<WorkVO> retriverMyWork(ColleagueVO colleague) {
		// TODO Auto-generated method stub
		List<WorkVO> workList = workDAO.selectMyWork(colleague);
		for(WorkVO single : workList) {
			if(single.getWorkTitle().length() >=5) {
				single.setWorkTitle(single.getWorkTitle().substring(0,5));
			}
		}
		return workList;
	}

	@Transactional
	@Override
	public int deleteWork(WorkVO work) {
		workDAO.deleteWorkNumReply(work);
		workMarkDAO.deleteWorkNumMark(work);
		workDAO.deleteAssignment(work);
		for(IssueVO issue :workDAO.selectIssueList(work)) {
			workDAO.deleteIssueReply(issue);
			attatchDAO.deleteAttatchReplaceFile(issue.getIssueNum());
		}
		workDAO.deleteIssue(work);
		return workDAO.deleteWork(work);
	}

	@Transactional
	@Override
	public List<ReplyVO> insertReply(ReplyVO reply) {
		reply.setWriterNum(workDAO.selectColNum(reply));
		workDAO.insertReply(reply);
		return workDAO.selectAllReplyList(reply);
	}

	@Override
	public List<ReplyVO> selectReply(ReplyVO reply) {
		return workDAO.selectAllReplyList(reply);
	}

	@Override
	public List<ReplyVO> updateReply(ReplyVO reply) {
		
		 workDAO.updateReply(reply);
		 
		 return workDAO.selectAllReplyList(reply);
	}

	@Override
	public List<ReplyVO> deleteReply(ReplyVO reply) {
		
		if(StringUtils.isBlank(reply.getParentWoReplyNum())) {
			
			workDAO.deleteReply(reply);
		}else {
			
			workDAO.deleteParentWoReplyNum(reply);
			workDAO.deleteReply(reply);
		}
		
		 
		 return workDAO.selectAllReplyList(reply);
	}

	@Transactional
	@Override
	public void deleteParentWork(WorkVO work) {
		  
			 for(WorkVO vo : workDAO.selectParentWork(work)) {
					
						 log.info("여기 실행되냐??");
						 workDAO.deleteWorkNumReply(vo);
						 workMarkDAO.deleteWorkNumMark(vo);
						 workDAO.deleteAssignment(vo);
						 for(IssueVO issue :workDAO.selectIssueList(vo)) {
							 workDAO.deleteIssueReply(issue);
							attatchDAO.deleteAttatchReplaceFile(issue.getIssueNum());
					 }
						 workDAO.deleteIssue(vo);
						 workDAO.deleteWork(vo);
				
			 }
			 
		 workDAO.deleteWorkNumReply(work);
		 workMarkDAO.deleteWorkNumMark(work);
		 workDAO.deleteAssignment(work);
			for(IssueVO issue :workDAO.selectIssueList(work)) {
				workDAO.deleteIssueReply(issue);
				attatchDAO.deleteAttatchReplaceFile(issue.getIssueNum());
			}
			workDAO.deleteIssue(work);
			workDAO.deleteWork(work);
		
		
	}
	
}
