package kr.or.ddit.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 단순 키워드 검색에 활용할  VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleSearchCondition {
	private String searchType;
	private String searchWord;
}
