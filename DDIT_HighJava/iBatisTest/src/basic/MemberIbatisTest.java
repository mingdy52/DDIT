package basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import vo.MemberVO;

public class MemberIbatisTest {
	public static void main(String[] args) {
//		iBatis를 이용하여 DB 자료를 처리하는 작업 순서
//		1. iBatis의 환경설정 파일을 읽어와 실행시킨다.
		try {
			// 1-1. xml문서 읽어오기
			
			/*
			 * Charset
			 * 문자열 변경 및 분석 소스
			 * 만약 한글 출력이 안되는 문자가 잇으시면 여기 때려넣고
			 * 제대로 출력되는걸 찾아서 디코딩->인코딩 순으로 변환해주면 된다.
			 */
			Charset charset = Charset.forName("UTF-8"); // 설정파일 인코딩 정보 설정
			
			// 인코딩 변환방식 불러옴
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			
			/*
			 * - iBatis를 사용할때 template를 쓰지 않을때는 
			 * 	  항상 SqlMapConfig파일을 통해 SqlMapClient를 생성해야 한다.
			 * - sqlMapClient 인터페이스는 트랜잭션 경계를 지정하기 위한 메소드를 가진다.
			 * 	  * 다음과 같은 메소드를 사용해 커밋/롤백 된다.
					public void startTransaction() throws SQLException
					public void commitTransaction() throws SQLException
					public void endTransaction() throws SQLException
					 - commitTransaction() 을 호출하지 않고  
					 	endTransaction() 메서드를 호출하는 경우 롤백 한다.
			 */
			// SqlMapClient 클래스는 Mapper를 생성하는 클래스임. Mapper 객체 생성
			// SqlMapClient 인터페이스는 SqlMapExecutor 인터페이스를 상속 받았다.
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close(); // reader 객체 닫기
			
			///////////////////////////////////////////////////////////////
			
// 		2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
/*		
			// 2-1. insert작업 연습
			System.out.println("insert 작업 시작");
			
			// 1) 저장할 데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();
			mv.setMemId("d006");
			mv.setMemName("흑임자");
			mv.setMemTel("010-8985-6465");
			mv.setMemAddr("대전 동구");
			
			// 2) SqlMapClient 객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식) smc.insert("namespace값.id값", 파라미터객체);
			//		반환값 : 성공하면 null이 반환된다.
			Object obj = smc.insert("memberTest.insertMember", mv);
							// Object insert(String id, Object ParameterObject)
			if(obj == null) {
				System.out.println("insert작업 성공");
			} else {
				System.out.println("insert작업 실패");
			}
			
//			int cnt3 = smc.update("memberTest.insertMember",mv);
//							// int update(String, Object).
//			if(cnt3 > 0) {
//				System.out.println("insert작업 성공");
//			} else {
//				System.out.println("insert작업 실패");
//			}
*/	
			System.out.println("------------------------------------------");

			//	2-2. update 작업 연습
			System.out.println("update작업 시작");
			
			MemberVO mv2 = new MemberVO();
			mv2.setMemId("d003");
			mv2.setMemName("전지수");
			mv2.setMemTel("1111-2222");
			mv2.setMemAddr("대전시 중구");
			
			// update()의 반환값은 성공한 레코드 수 이다.
			
			int cnt = smc.update("memberTest.updateMember", mv2);
						// int update(String, Object)
			if(cnt > 0) {
				System.out.println("update 작업 성공");
			} else {
				System.out.println("update 작업 실패");
			}
			System.out.println("------------------------------------------");
			
			System.out.println("delete작업 시작....");

	        // delete() 메소드의 반환값 : 성공한 레코드수
	        int cnt2 = smc.delete("memberTest.deleteMember","d003");
	        			// int delete(String, Object)
	        if (cnt2 > 0) {
	           System.out.println("delete작업 성공");
	        } else {
	           System.out.println("delete작업 실패!!!");
	        }
	        System.out.println("------------------------------------------");
	         
	        // 2-4. select 연습
	        // 1) 응답 결과가 여러개일 경우
	        System.out.println("결과가 여러개일 경우");
	        
	        // 응답 결과가 어려개일 경우에는 queryForList()를 사용한다.
	        // 이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에 추가해 주는 작업을 자동으로 수행한다.
	        List<MemberVO> memList = smc.queryForList("memberTest.getMemberAll");
	        
	        for (MemberVO mv3 : memList) {
				System.out.println("ID : " + mv3.getMemId());
				System.out.println("NAME : " + mv3.getMemName());
				System.out.println("TEL : " + mv3.getMemTel());
				System.out.println("ADDR : " + mv3.getMemAddr());
				System.out.println("=================================");
			}
	        System.out.println("출력 끝");
	        
	        // 2) 응답 결과가 1개일 경우
			System.out.println("select 연습 시작(결과가 1개일 경우)");
			
			// 응답 결과가 1개가 확실할 경우에는 queryForObject메서드를 사용한다.
			// 데이터베이스로부터 한 개의 레코드를 가져다가 자바 객체에 저장함.
			MemberVO mv4 = (MemberVO) smc.queryForObject("memberTest.getMember", "a002");
			
			System.out.println("ID : " + mv4.getMemId());
			System.out.println("NAME : " + mv4.getMemName());
			System.out.println("TEL : " + mv4.getMemTel());
			System.out.println("ADDR : " + mv4.getMemAddr());
			System.out.println("출력 끝");
			
			/*
			 * queryForMap()
			 * 	: 데이터베이스로부터 한 개 혹은 그 이상의 레코드를 가져올 때 자바 객체의 Map을 반환.
			 */
		}catch (IOException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
}
