package kr.or.ddit.buyer.vo;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = { "prodId" })
@ToString(exclude = { "prodDetail" })
public class ProdVO implements Serializable {
	private int rnum;

	@NotBlank(groups=UpdateGroup.class)
	private String prodId;
	@NotBlank
	@Size(min=4, max=15)
	private String prodName;
	@NotBlank
	private String prodLgu;
	private String lprodNm;
	@NotBlank
	private String prodBuyer;
	private String buyerName;
	@NotNull
	@Min(0)
	private Integer prodCost;
	@NotNull
	@Min(0)
	private Integer prodPrice;
	@NotNull
	@Min(0)
	private Integer prodSale;
	@NotBlank
	private String prodOutline;
	private String prodDetail;
	
	private MultipartFile prodImage;
	public void setProdImage(MultipartFile prodImage) {
		this.prodImage = prodImage;
		if(prodImage!=null && !prodImage.isEmpty()) {
			this.prodImg = UUID.randomUUID().toString();
		}
	}
	
	@NotBlank(groups=InsertGroup.class)
	private String prodImg;
	
	@NotNull
	@Min(0)
	private Integer prodTotalstock;
	private String prodInsdate;
	@NotNull
	@Min(0)
	private Integer prodProperstock;
	private String prodSize;
	private String prodColor;
	private String prodDelivery;
	private String prodUnit;
	private Integer prodQtyin;
	private Integer prodQtysale;
	private Integer prodMileage;

	private BuyerVO buyer; // HAS A (1:1)
}
