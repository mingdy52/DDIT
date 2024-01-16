package kr.or.ddit.expert.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExpWishVO {
	private int rnum; //번호조회
	private String wishId;
	private String memId;
	private String eprodNum;
	
	private EProdVO eprod;
}
