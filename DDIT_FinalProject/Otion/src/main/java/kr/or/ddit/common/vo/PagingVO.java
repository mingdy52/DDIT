package kr.or.ddit.common.vo;

import java.util.List;

import kr.or.ddit.proj.colleague.vo.ColleagueVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 페이징 처리와 관련된 모든 데이터를 가진 객체.
 * setTotalRecord/setCurrentPage 가 호출되어야 연산 완료.
 */
@Getter
@NoArgsConstructor
@ToString
public class PagingVO<T> {
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord; // DB 조회 (**)
	private int totalPage; // 
	private int screenSize=10; // 임의 결정
	private int blockSize=5; // 임의 결정
	private int currentPage; // 사용자의 파라미터
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	
	private List<T> dataList;
	
	/**
	 * 단순 키워드 검색용
	 */
	private SimpleSearchCondition simpleCondition;
	
	private T detailCondition;
	
	private ColleagueVO colleague;
	
	public void setColleague(ColleagueVO colleague) {
		this.colleague = colleague;
	}
	
	/**
	 * 날짜 검색용
	 */
	private String startDate;
	private String endDate;
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (totalRecord + (screenSize-1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
		this.endRow = screenSize * currentPage;
		this.startRow = endRow - (screenSize-1);
		this.endPage = ((currentPage + (blockSize-1))/blockSize) * blockSize;
		this.startPage = endPage - (blockSize-1);
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
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	private static final String PAGINGPTRN = "<a href='#' data-page='%d'>%s</a>";
	
	public String getPagingHTML() {
		endPage = endPage > totalPage ? totalPage : endPage; 
		StringBuffer html = new StringBuffer();
		if(startPage > blockSize) {
			html.append(String.format(PAGINGPTRN, (startPage-blockSize) , "<<"));
		}
		for(int page=startPage; page<=endPage; page++) {
			html.append(String.format(PAGINGPTRN, page, Integer.toString(page)));
		}
		if(totalPage > endPage) {
			html.append(String.format(PAGINGPTRN, (endPage + 1) , ">>"));
		}
		return html.toString();
	}
	
	
	
	private static final String BSPTRN = "<li class='page-item %s' %s><a class='page-link' href='#' data-page='%d'>%s</a></li>";
	
//	    <li class='page-item disabled'>
//	      <a class='page-link'>Previous</a>
//	    </li>
//	    <li class='page-item'><a class='page-link' href='#'>1</a></li>
//	    <li class='page-item active' aria-current='page'>
//	      <a class='page-link' href='#'>2</a>
//	    </li>
//	    <li class='page-item'><a class='page-link' href='#'>3</a></li>
//	    <li class='page-item'>
//	      <a class='page-link' href='#'>Next</a>
//	    </li>
	
	private String makePreviousLink() {
		boolean disabled = startPage <= blockSize;
		return String.format(BSPTRN, 
							disabled?"disabled":"", 
							"", 
							startPage-blockSize, 
							"&laquo;");
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
							endPage + 1,
							"&raquo;");
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
	
	
	private static final String REPLYSTYLEPTRN = "<div class='w-100'>"+
			"<a class='btn btn-primary btn-lg col-10' href='#' data-page='%d'>다음 페이지</a>";
	public String getPagingRepStyle() {
		StringBuffer html = new StringBuffer();
		if(currentPage<totalPage) {
			html.append(String.format(REPLYSTYLEPTRN, currentPage+1));
		}
		html.append("<button type='button' class='m-3 btn btn-secondary scrollTop col-1'>↑</button></div>");
		return html.toString();
	}

	

	
}


