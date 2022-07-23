package kr.or.ddit.buyer.vo;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="buyerId")
@ToString(exclude= {"prodList"})
public class BuyerVO  implements Serializable {
	
	private int rnum;
	
	@NotBlank(groups= {UpdateGroup.class, DeleteGroup.class})
	private String buyerId;
	@NotBlank
	private String buyerName;
	@NotBlank
	private String buyerLgu;
	private String buyerBank;
	private String buyerBankno;
	private String buyerBankname;
	private String buyerZip;
	private String buyerAdd1;
	private String buyerAdd2;
	@NotBlank
	private String buyerComtel;
	@NotBlank
	private String buyerFax;
	@NotBlank
	private String buyerMail;
	private String buyerCharger;
	private String buyerTelext;
	
	private String lprodNm;
	
	private Set<ProdVO> prodList;
	
}
