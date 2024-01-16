package vo;

public class BoardHwVO {
	private String boNo;
	private String boTitle;
	private String boWriter;
	private String boDate;
	private String boContent;
	
	public String getBoNo() {
		return boNo;
	}
	public void setBoNo(String boNo) {
		this.boNo = boNo;
	}
	
	public String getBoTitle() {
		return boTitle;
	}
	public void setBoTitle(String boTitle) {
		this.boTitle = boTitle;
	}
	
	public String getBoWriter() {
		return boWriter;
	}
	public void setBoWriter(String boWriter) {
		this.boWriter = boWriter;
	}
	
	public String getBoDate() {
		return boDate;
	}
	public void setBoDate(String boDate) {
		this.boDate = boDate;
	}
	
	public String getBoContent() {
		return boContent;
	}
	public void setBoContent(String boContent) {
		this.boContent = boContent;
	}
	
	
	@Override
	public String toString() {
		return "BoardHwVO [boNo=" + boNo + ", boTitle=" + boTitle + ", boWriter=" + boWriter + ", boDate=" + boDate
				+ ", boContent=" + boContent + "]";
	}
	
	
}
