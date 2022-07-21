package kr.or.ddit.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 거래처에 대한 정보를 가진 Domain Layer
 * @author 306-16
 *
 */

@Data
@EqualsAndHashCode(of= "buyerId")
public class BuyerVO {

	private String buyerId;
	private String buyerName;
	private String buyerLgu;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	private String buyerComtel;
	private String buyerFax;
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
}
