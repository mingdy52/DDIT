package kr.or.ddit.common.vo;

import kr.or.ddit.enumpkg.ComCode;
import lombok.Data;

@Data
public class NewsVO {
	private String newsNum;
	private String receiverId;
	private String newsMsgCode;
	private String newsOpenYn;
	private String newsDate;
	private String newsId;
	
	private String result;
	
	private String comCodeNm;
	
	private String postId;
}
