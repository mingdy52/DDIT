package kr.or.ddit.proj.prod.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.proj.prod.vo.ProjectPaymentVO;
import kr.or.ddit.proj.prod.vo.ProjectProdVO;

@Mapper
public interface ProjectProdDAO {

	public List<ProjectProdVO> selectProdList();

	public void insertBuyProd(ProjectPaymentVO payVO);

	public ProjectPaymentVO selectPayment(String pjId);
}
