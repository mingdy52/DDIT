package kr.or.ddit.admin.vo;

import java.util.List;

import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.MyExpertVO;
import lombok.Data;

@Data
public class RevenueVO {
	private int eprodMonth01;
	private int eprodMonth02;
	private int eprodMonth03;
	private int eprodMonth04;
	private int eprodMonth05;
	private int eprodMonth06;
	private int eprodMonth07;
	private int eprodMonth08;
	private int eprodMonth09;
	private int eprodMonth10;
	private int eprodMonth11;
	private int eprodMonth12;
	        
	private int workMonth01;
	private int workMonth02;
	private int workMonth03;
	private int workMonth04;
	private int workMonth05;
	private int workMonth06;
	private int workMonth07;
	private int workMonth08;
	private int workMonth09;
	private int workMonth10;
	private int workMonth11;
	private int workMonth12;
	
	private String epayDate;
	private String wpayDate;
	private int eprodRevenue;
	private int workRevenue;
	private int accumRevenue;
	private String strEprodMonth;
	private String strWorkMonth;
	
	private String expertId;
	private String year;
	

}