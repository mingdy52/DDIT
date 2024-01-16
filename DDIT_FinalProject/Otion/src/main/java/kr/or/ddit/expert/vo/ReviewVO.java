package kr.or.ddit.expert.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewVO implements Serializable{

	private String reviewNum;
	private String reviewWriter;
	private String myEprod;
	@NotBlank(groups= {InsertGroup.class, UpdateGroup.class}, message="후기제목을 작성해주세요.")
	private String reviewTitle;
	@NotBlank(groups= {InsertGroup.class, UpdateGroup.class}, message="후기내용을 작성해주세요.")
	private String reviewContent;
	private String reviewDate;
	@NotBlank(groups= {InsertGroup.class, UpdateGroup.class}, message="별점을 작성해주세요.")
	private String reviewStar;
	
}
