package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 상품에 관한 정보를 가진 Domain Layer
 *
 */
@Data
//@Setter
//@Getter
//of는 equal hashcode의 파라미터처럼 사용됨, 배열
//Mybatis에서 set형태의 javaType을 사용하려면 equals를 무조건 사용해야함
@EqualsAndHashCode(of= {"prodId"}) // 프라이머리 키가 동일하면 같은 객체로 처리하겠다.
@ToString(exclude= {"prodDetail"})
public class ProdVO implements Serializable {
	
	private int rnum;
	private String prodId;
	private String prodName;
	private String prodLgu;
	private String lprodNm;
	private String prodBuyer;
	
	private BuyerVO buyer; // has a 관계, 1 : 1
	// 기본형이 아님. 컬럼 하나의 값을 직접 줄 수 없다면 
	
	private Integer prodCost;
	private Integer prodPrice;
	private Integer prodSale;
	private String prodOutline;
	private String prodDetail;
	private String prodImg;
	private Integer prodTotalstock;
	private String prodInsdate;
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;
	
	private Set<MemberVO> memberSet; // ProdVO has many MemberVO 관계, 1 : N
	
	
}
