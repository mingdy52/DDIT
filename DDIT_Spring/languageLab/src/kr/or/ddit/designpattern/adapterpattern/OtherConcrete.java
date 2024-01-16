package kr.or.ddit.designpattern.adapterpattern;

public class OtherConcrete implements Target {

	@Override
	public void request() {
		System.out.println(this.getClass().getSimpleName() + "에서 요청이 처리됨.");
	}

}
