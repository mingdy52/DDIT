package kr.or.ddit.proj.prod.service;

import java.util.List;

import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.prod.vo.ProjectProdVO;

public interface ProjectProdService {
	
	public List<ProjectProdVO> retrieveProdList();

	public void createBuyProd(ProjectPaymentVO payVO);

	public ProjectPaymentVO retrievePayment(String pjId);
}
