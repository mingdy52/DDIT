package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProdServiceImpl implements ProdService {
	
	@Inject
	ProdDAO prodDAO;
	@Inject
	WebApplicationContext rootContext;
	
	private File imageFolder;
	
	@PostConstruct // 생성한 뒤에 초기화. 인젝션이 끝난 후 딱 한번 실행(서비스가 싱글톤이니까)
	public void init() {
		// 상품 이미지 저장 처리(MultipartFile)
		String imageFolderUrl = "/resources/prodImages";
		String imageFolderPath = rootContext.getServletContext().getRealPath(imageFolderUrl);
		imageFolder = new File(imageFolderPath);
		if(!imageFolder.exists()) imageFolder.mkdirs();
		log.info("{} 주입 후 {} 생성 및 확인", rootContext, imageFolder);
	}
	
	private void processImage(ProdVO prod) {
		MultipartFile imageFile = prod.getProdImage();
		if(imageFile != null) {
			if(1==1) throw new RuntimeException("강제 예외 발생");
			File prodImageFile = new File(imageFolder, prod.getProdImg());
			try {
				imageFile.transferTo(prodImageFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e); 
			}
		}
	}
	
	@Transactional // 선언적 프로그래밍. 
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int rowcnt = prodDAO.insertProd(prod);
		processImage(prod);
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO) {
		pagingVO.setTotalRecord(prodDAO.selectTotalRecord(pagingVO));
		List<ProdVO> prodList = prodDAO.selectProdList(pagingVO);
		pagingVO.setDataList(prodList);
		return prodList;
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		ProdVO prod = prodDAO.selectProd(prodId);
		if(prod==null) {
			throw new PKNotFoundException(String.format("%s 상품이 없음.", prodId));
		}
		return prod;
	}
	
	@Transactional 
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		if(prodDAO.selectProd(prod.getProdId()) == null) {
			throw new PKNotFoundException(String.format("%s 상품 없음", prod.getProdName()));
		}
		int rowcnt = prodDAO.updateProd(prod);
		processImage(prod);
		
		return rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
	}

}




