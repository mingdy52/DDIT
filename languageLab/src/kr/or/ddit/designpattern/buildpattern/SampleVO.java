package kr.or.ddit.designpattern.buildpattern;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE) // 전체를 한꺼번에 만들 수 있는 생성자 -> aaaa 와 같음
@Builder
public class SampleVO {
	
//	public SampleVO(String prop1){
//		this.prop1 = prop1;
//	}
//	
//	public SampleVO(String prop1, String prop2){
//		this.prop1 = prop1;
//		this.prop2 = prop2;
//	}
//	
//	public SampleVO(String prop1, String prop2, String prop3){
//		this.prop1 = prop1;
//		this.prop3 = prop2;
//		this.prop2 = prop3;
//	}
	
	
	
/*	id: aaaa
	private SampleVO(String prop1, String prop2, String prop3) {
		super();
		this.prop1 = prop1;
		this.prop2 = prop2;
		this.prop3 = prop3;
	}
	
*/	
	private String prop1;
	private String prop2;
	private String prop3;



	public static SampleVOBuilder builder() {
		return new SampleVOBuilder();
	}
	
	public static class SampleVOBuilder {
		// 빌더는 빌드할 대상이 가진 프로퍼티와 동일한 프로퍼티를 가지고 있어야 함.
		private String prop1;
		private String prop2;
		private String prop3;
		
		// 세터의 역할을 하지만 빌더는 리턴 타입을 가짐 => 자기 자신!
		public SampleVOBuilder prod1(String prop1) {
			this.prop1 = prop1;
			return this; // 자기 자신을 반환
		}
		
		public SampleVOBuilder prop2(String prop2) {
			this.prop2 = prop2;
			return this; 
		}
		
		public SampleVOBuilder prop3(String prop3) {
			this.prop3 = prop3;
			return this; 
		}
		
		// 빌드는 빌더의 대상을 타입으로 가짐.
		// 동일한 클래스의 이넘클래스니까 private 이어도 접근 가능
		public SampleVO build() {
			return new SampleVO(prop1, prop2, prop3); 
		}
		
	}

}
