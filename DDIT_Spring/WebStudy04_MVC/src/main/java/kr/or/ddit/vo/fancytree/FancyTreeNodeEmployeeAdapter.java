package kr.or.ddit.vo.fancytree;

import kr.or.ddit.vo.EmployeeVO;

public class FancyTreeNodeEmployeeAdapter implements FancyTreeNode<EmployeeVO>{
	
	private EmployeeVO adaptee;
//	Integer num = new Integer(34);
//	Adapter pattern(WRAPPER pattern)
	public FancyTreeNodeEmployeeAdapter(EmployeeVO adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public String getKey() {
		return adaptee.getEmployeeId().toString();
	}

	@Override
	public String getTitle() {
		return String.format("%s %s", adaptee.getLastName(), adaptee.getFirstName());
	}

	@Override // 부하직원의 존재 여부
	public boolean isFolder() {
		return adaptee.getChildCount()>0;
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
	public EmployeeVO getAdaptee() {
		return adaptee;
	}

	@Override
	public int compareTo(FancyTreeNode<EmployeeVO> o) {
		return -(adaptee.getSalary() - o.getAdaptee().getSalary());
	}
}














