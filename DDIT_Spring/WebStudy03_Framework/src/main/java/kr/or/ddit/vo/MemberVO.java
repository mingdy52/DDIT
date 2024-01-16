package kr.or.ddit.vo;

import java.io.Serializable;
import java.security.acl.Group;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;


import kr.or.ddit.validate.DeleteGroup;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.validate.contraints.TelNumber;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

// DTO, marker interface : Serializable - marker annotation
/**
 * 
 * 회원 관리를 위한 Domain Layer
 * 한 사람의 회원에 대한 모든 정보를 가진 객체
 * 기본정보 + 구매기록(상품들)
 * 
 * Mybatis 를 이용한 조인 방법
 * 1. 테이블간의 관계성을 메인 테이블을 중심으로 파악.
 * 		ex) 한명의 회원과 그 사람의 구매 기록 조회
 * 			MEMBER(1) PROD(N) -> 1 : N
 * 			PROD(1) BUYER(1) -> 1 : 1
 * 2. 각 테이블의 스키마를 반영한 VO 생성
 * 		MEMBER(MemberVO), PROD(ProdVO), BUYER(BuyerVO)
 * 3. 테이블간의 관계성을 VO에 반영.
 * 		1 : 1 -> ProdVO has a BuyerVO
 * 		1 : N -> MemberVO has many ProdVO
 * 4. 조인 쿼리 작성 - resultType 대신 resultMap 사용해 바인딩.
 * 		1 : 1 -> has a -> association 으로 바인딩.
 * 		1 : N -> has many -> collection 으로 바인딩. -> 반드시 id로 중복여부 판단 설정.
 * 
 */

@Data // 자동으로 게터 세터 만들어줌. -  롬북에서 리플렉션이 이루어 지고 있는 것!. 이걸 지우면 마이바티스가 찾지 못해. 게터 세터가 없으니까.
@EqualsAndHashCode(of= {"memId", "memRegno1", "memRegno2"})
@ToString(exclude= {"memPass", "memRegno1", "memRegno2", "buyList"})
@NoArgsConstructor //도메인 레이어에 기본 생성자가 없으면 프레임워크를 사용할 수 없음.
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
public class MemberVO implements Serializable {
	// Serializable 는 가지고 있는 메소드가 없음. 현재 데이터가 직렬화 할 수 있는지 확인하는 역할만 함.
	// 이런 인터페이스를 marker interface 라고 함. - 비슷한 애 : marker annotation
	
//	transient : transient가 설정된 데이터는 객체가 직렬화 되더라도 직렬화에서 빠져나옴.
	
		@NotBlank(message="아이디는 필수 입력데이터", groups= {Default.class, DeleteGroup.class})
//		javax.validation.constraints.NotBlank.message // properties 파일에서 가져옴
//		javax.validation.constraints.NotBlank // 어노테이션에서 가져옴
		private String memId;
		
		@NotBlank(groups= {Default.class, DeleteGroup.class})
		@Size(min=4, max=12, message="비밀번호는 4글자에서 12글자 사이.", groups= {Default.class, DeleteGroup.class})
		private String memPass;
		
		@NotBlank
		private String memName;
		
		@NotBlank(groups=InsertGroup.class)
		@Size(min=6, max=6, groups=InsertGroup.class) // memRegno1 는 insert 할 때만 검증하겠따.
		private String memRegno1;
		
		@NotBlank(groups=InsertGroup.class)
		@Size(min=7, max=7, groups=InsertGroup.class)
		private String memRegno2;
		
		@NotBlank
		private String memBir;
		
		@NotBlank
		private String memZip;
		
		@NotBlank
		private String memAdd1;
		
		@NotBlank
		private String memAdd2;
		
//		@Pattern(regexp="\\d{2,3}-\\d{3,4}-\\d{4}")
		@TelNumber
		// 속성명을 생략할 수 있는 건 그 속성명이 value 일 때만 가능. 속성을 두개 이상 사용할 경우 속성명 생략 불가
		private String memHometel;
		
//		@Pattern(regexp="\\d{2,3}-\\d{3,4}-\\d{4}")
		@TelNumber
		private String memComtel;
		
		@NotBlank
//		@Pattern(regexp="\\d{3}-\\d{3,4}-\\d{4}")
		@TelNumber("\\d{3}-\\d{3,4}-\\d{4}")
		private String memHp;
		
		@NotBlank
		@Email(message="이메일 계정 확인")
		private String memMail;
		private String memJob;
		private String memLike;
		private String memMemorial;
		private String memMemorialday;
		private Integer memMileage;
		private String memDelete;
	
		// 구매 기록
		// 만약 구매기록에는 절대로 중복이 발생해서는 안된다 는 조건이 추가됐다면? 중복 허용 안하는 Set 사용 => 수정해도 Lombok이 알아서
		// 해결해줌~
		private Set<ProdVO> buyList; // has many 1 : N
		
		private String memRole;
}
