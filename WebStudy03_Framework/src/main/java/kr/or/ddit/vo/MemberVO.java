package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
	
	
	private String memId;
	private transient String memPass;
	private String memName;
	private transient String memRegno1;
	private transient String memRegno2;
	private String memBir;
	private String memZip;
	private String memAdd1;
	private String memAdd2;
	private String memHometel;
	private String memComtel;
	private String memHp;
	private String memMail;
	private String memJob;
	private String memLike;
	private String memMemorial;
	private String memMemorialday;
	private Integer memMileage;
	private String memDelete;
	
	// 구매기록
	private Set<ProdVO> buyList; // has many 1 : N
	
}
