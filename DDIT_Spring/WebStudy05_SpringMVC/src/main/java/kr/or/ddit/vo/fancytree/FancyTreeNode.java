package kr.or.ddit.vo.fancytree;

public interface FancyTreeNode<T> extends Comparable<FancyTreeNode<T>> {
	// 파일이 아니더라도 FancyTreeNode 를 사용하면 정렬할 수 있음.
//	<T>: 객체를 생성하는 시점에 타입 이 정해짐
	
//	private String key;
//	private String title;
	
	public String getKey();
	public String getTitle();
	// key title 이 있어야 한다는 말과 같음.
	
	public boolean isFolder();
	public boolean isLazy();
	public boolean isExpanded();
	
	
	public T getAdaptee();
	
	@Override
	default int compareTo(FancyTreeNode<T> o) {
		return getTitle().toLowerCase().compareTo(o.getTitle().toLowerCase());
	}
	
}
