package kr.or.ddit.community.coop.service;

import kr.or.ddit.community.coop.vo.CooFormVO;

public interface CooFormService {
	// 신청
	public void createCooForm(CooFormVO cooForm);
	// 거절
	public void updateCancelCooForm(String cooNum);
	// 승인
	public void updateCorrectCooForm(String cooNum);
}
