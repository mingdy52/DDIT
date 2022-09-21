package kr.or.ddit.proj.prod.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.proj.prod.dao.ProjectProdDAO;
import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.prod.vo.ProjectProdVO;

@Service
@Transactional
public class ProjectProdServiceImpl implements ProjectProdService {

	@Inject
	ProjectProdDAO prodDao;
	
	@Override
	public List<ProjectProdVO> retrieveProdList() {
		// TODO Auto-generated method stub
		return prodDao.selectProdList();
	}

	@Override
	public void createBuyProd(ProjectPaymentVO payVO) {
		// TODO Auto-generated method stub
		prodDao.insertBuyProd(payVO);
	}

	@Override
	public ProjectPaymentVO retrievePayment(String pjId) {
		// TODO Auto-generated method stub
		return prodDao.selectPayment(pjId);
	}

}
