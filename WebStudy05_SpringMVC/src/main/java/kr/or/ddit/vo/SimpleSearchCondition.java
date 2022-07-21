package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 단순 키워드 검색에 활용할 VO
 *
 */
@Data
@AllArgsConstructor
public class SimpleSearchCondition {
	
	private String searchType;
	private String searchWord;
}
