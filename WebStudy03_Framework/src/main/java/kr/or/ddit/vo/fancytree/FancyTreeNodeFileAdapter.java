package kr.or.ddit.vo.fancytree;

import java.io.File;

public class FancyTreeNodeFileAdapter implements FancyTreeNode<File> {
	
	private File adaptee;
	private String serverFilePath;
	
	public FancyTreeNodeFileAdapter(File adaptee, String serverFilePath) { 
		//adaptee 가 없으면 말짱 도루묵! adaptee 가 있으면 안돼니까 기본생성자를 만들면 안돼
		super();
		this.adaptee = adaptee;
		this.serverFilePath = serverFilePath;
	}

	@Override
	public String getKey() {
		return this.serverFilePath;
	}

	@Override
	public String getTitle() {
		return adaptee.getName();
	}

	@Override
	public boolean isFolder() {
		return adaptee.isDirectory();
	}
	
	@Override
	public File getAdaptee() {
		return this.adaptee;
	}
	
	@Override
	public boolean isLazy() {
		return isFolder();
	}
	
	@Override
	public boolean isExpanded() {
		return false;
	}
	
	@Override
	public int compareTo(FancyTreeNode<File> o) {
		File otherFile = o.getAdaptee();
		if(!(adaptee.isDirectory()^otherFile.isDirectory())) {
//			^: 배타적 or 연산자. 둘이 서로 다른 값을 가진 경우 true
				return FancyTreeNode.super.compareTo(o);
				
			} else {
				if(adaptee.isDirectory()) {
					return -1;
				} else {
					return 1;
				}
				
			}
	}
}
