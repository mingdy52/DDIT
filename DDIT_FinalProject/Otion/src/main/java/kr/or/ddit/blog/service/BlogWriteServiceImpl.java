package kr.or.ddit.blog.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.blog.dao.BlogWriteDAO;
import kr.or.ddit.blog.vo.BlogHeartVO;
import kr.or.ddit.blog.vo.MyBlogPostVO;
import kr.or.ddit.blog.vo.PostMarkVO;
import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.PagingVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 박채록
 * @since 2022. 8. 23.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 23.      박채록       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Service
public class BlogWriteServiceImpl implements BlogWriteService{

	private static final String MyBlogPostVO = null;

	@Inject
	BlogWriteDAO writeDAO;
	
	@Inject
	AttatchDAO attatchDAO;
	
	@Value("#{appInfo['attatchPath']}")
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		log.info("주입된 저장 경로 : {}", saveFolder.getCanonicalPath());
	}
	
	@Transactional
	@Override
	public int createMypost(MyBlogPostVO mypost) {
		try {
			processAttatches(mypost); // 첨부파일 처리.
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		int rowcnt = writeDAO.insertMyPost(mypost);
		
		return rowcnt;
	}
	
	
	@Transactional
	@Override
	public int createBookMark(PostMarkVO postmark) {
		int rowcnt = writeDAO.insertBookMK(postmark);
		return rowcnt;
	}
	
	@Transactional
	@Override
	public int deleteBookMark(PostMarkVO postmark) {
		int rowcnt = writeDAO.deleteBookMK(postmark);
		return rowcnt;
	}

	@Transactional
	@Override
	public boolean MarkYn(PostMarkVO postmark) {
		boolean markChk = false;
		PostMarkVO mypostmark =  writeDAO.selectMarkYn(postmark);
		if(mypostmark!=null) {
			markChk =true;
		}else {
			markChk = false;
		}
		return markChk;
	}
	
	@Override
	public List<PostMarkVO> retrieveMyMarkList(PagingVO<PostMarkVO> pagingVO) {
		pagingVO.setTotalRecord(writeDAO.selectmyMarkTotal(pagingVO));
		List<PostMarkVO> mymarkList = writeDAO.selectmyMarkList(pagingVO);
		pagingVO.setDataList(mymarkList);
		return mymarkList;
	}
	
	@Transactional
	@Override
	public int modifyPost(Map<String, Object> map) {
		MyBlogPostVO mypost = (MyBlogPostVO) map.get("mypost");
		log.info("mypost:{}", mypost);
		int rowcnt = writeDAO.updatePost(mypost);
		
		try {
			processAttatches(mypost); // 첨부파일 처리.
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		String[] attatchNum = (String[]) map.get("attatchNum");
		log.info("attatchNum:{}", attatchNum);
		int[] attatchOrder = (int[]) map.get("attatchOrder");
		if(attatchNum != null) {
			AttatchVO attatchVO = new AttatchVO();
			attatchVO.setAttatchPlace(mypost.getPostNum());
			for (String num : attatchNum) {
				attatchVO.setAttatchNum(num);
				for (int order : attatchOrder) {
					attatchVO.setAttatchOrder(order);
					attatchDAO.deleteAttatchFile(attatchVO);
					
				}
				
			}
		}
		
		return rowcnt;
	}
	
	private void processAttatches(MyBlogPostVO mypost) throws IOException {
		List<AttatchVO> attatchList = mypost.getAttatchList();
		if(attatchList == null || attatchList.isEmpty()) {
			return;
		}
		log.info("mypost:{}", mypost);
		log.info("attatchList:{}", attatchList);
		attatchDAO.insertBlogAttatches(mypost);
		// 2진 데이터(파일 자체) 저장 -> d:/saveFiles
		
		for (AttatchVO attatch : attatchList) {
			MultipartFile file = attatch.getAttatchFile();
			attatch.saveTo(saveFolder);
			
		}
	}

	@Override
	public int createHeart(BlogHeartVO heart) {
		int rowcnt = writeDAO.insertHeart(heart);
		return rowcnt;
	}

	@Override
	public int deleteHeart(BlogHeartVO heart) {
		int rowcnt = writeDAO.deleteHeart(heart);
		return rowcnt;
	}

	@Override
	public boolean heartYn(BlogHeartVO heart) {
		boolean heartChk = false;
		BlogHeartVO mypostmark =  writeDAO.selectHeartYn(heart);
		if(mypostmark!=null) {
			heartChk =true;
		}else {
			heartChk = false;
		}
		return heartChk;
	}

	@Override
	public int countHeart(String postNum) {
		int heartNo = writeDAO.selectTotalHeart(postNum);
		return heartNo;
	}

	@Override
	public int modifyHeartNo(String postNum) {
		int heart = writeDAO.updateHeartNo(postNum);
		return heart;
	}
	
}
