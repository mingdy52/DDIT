package kr.or.ddit.common.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이징 처리와 관련된 모든 데이터를 가진 객체/
 * setTotlaRecord / setCurrentPage 가 호출되어야 연산 완료.
 * 
 */

@Getter
@NoArgsConstructor
public class PagingVO<T> {
//	generic - 정적이지 않고 동적이다. 
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord; // DB 조회(**)
	private int totalPage; // 토탈 레코드가 결정되면 토탈 페이지 결정
	private int screenSize = 10; // 임의 결정
	private int blockSize = 5; // 임의 결정
	private int currentPage; // 사용자 파라미터
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
//	페이징 구조가 생성될 때 리스트의 타입이 제한된다.
	
	private SimpleSearchCondition simpleCondition;
	
	private T detailCondition; // 상세 검색. prodVO 로 타입이 정해지면 prodVO에 있는 모든 프로퍼티로 검색 가능.
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (totalRecord + (screenSize - 1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) { // currentPage 가 아래를 정해줄 수 있음.
		this.currentPage = currentPage;
		
		this.endRow = currentPage * screenSize;
		this.startRow = endRow - (screenSize - 1);
		
		this.endPage = ((currentPage + (blockSize - 1)) / blockSize) * blockSize;
		this.startPage = endPage - (blockSize - 1);
	}
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public void setSimpleCondition(SimpleSearchCondition simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}
	
	private static final String PAGINGPTRN = "<a href='#' data-page='%d'>%s</a>";
	
	public String getPagingHTML() {
		endPage = endPage > totalPage ? totalPage : endPage; 
		StringBuffer html = new StringBuffer();
		if(startPage > blockSize) {
			html.append(String.format(PAGINGPTRN, (startPage-blockSize) , "이전"));
		}
		for(int page=startPage; page<=endPage; page++) {
			html.append(String.format(PAGINGPTRN, page, Integer.toString(page)));
		}
		if(totalPage > endPage) {
			html.append(String.format(PAGINGPTRN, (endPage + 1) , "다음"));
		}
		return html.toString();
	}
	
	private static final String BSPTRN = "<li class='page-item %s' %s><a class='page-link' href='#' data-page='%d'>%s</a></li>";
	
	
	private String makePreviousLink() {
		boolean disabled = startPage <= blockSize;
		return String.format(BSPTRN, 
							disabled?"disabled":"", 
							"", 
							startPage-blockSize, 
							"이전");
	}
	
	private String makePageLink() {
		StringBuffer pageLink = new StringBuffer();
		endPage = endPage > totalPage ? totalPage : endPage;
		for(int page=startPage; page<=endPage; page++) {
			boolean active = page == currentPage;
			pageLink.append(
				String.format(BSPTRN, 
							active?"active":"", 
							"aria-current='page'", 
							page, 
							Integer.toString(page))
			);
		}
		return pageLink.toString();
	}
	
	private String makeNextLink() {
		boolean disabled = totalPage <= endPage;
		return String.format(BSPTRN, 
							disabled?"disabled":"", 
							"", 
					endPage + 1, "다음");
		}
	
		public String getPagingHTMLBS() {
			StringBuffer html = new StringBuffer();
			html.append(" <nav aria-label='...'>    ");
			html.append("   <ul class='pagination'> ");
			html.append(makePreviousLink());
			html.append(makePageLink());
			html.append(makeNextLink());
			html.append("   </ul>                   ");
			html.append(" </nav>                    ");
			return html.toString();
		}
	}