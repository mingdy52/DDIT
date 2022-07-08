package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import kr.or.ddit.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements MemberDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public MemberVO selectMemberForAuth(MemberVO inputData) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMemberForAuth", inputData);
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class); 
			// 진짜 객체가 아님. 마이바티스가 구현체의 역할을 대리하고 있는 가짜 객체. MemberDAO를 전달
			return mapperProxy.selectMemberForAuth(inputData);
			// 프록시가 만들어지려면 반드시 인터페이스 구조가 필요함.
			// 이 방법의 단점은 너무 많은 프록시를 쓰고 버리는 것임.
		}
		
	}

	@Override
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
//			return sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectMemberList(pagingVO);
		}
		
	}
	
	@Override
	public int selectTotalRecord(PagingVO<MemberVO> pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
//			return sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList");
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class);
			return mapperProxy.selectTotalRecord(pagingVO);
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember", memId);
			MemberDAO mapperProxy = sqlSession.getMapper(MemberDAO.class); // 진짜 객체가 아님. 구현체의 역할을 대리하고 있는 가짜 객체
			return mapperProxy.selectMember(memId);
		}
	}
	
	@Override
	public int insertMember(MemberVO member) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession(); // 세션 오픈 지점. 트랜젝션 시작
				// SqlSession 얘가 내부에서 발생하는 예외를 runtime으로 바꿔줌.
				){
			
//			return sqlSession.insert("kr.or.ddit.member.dao.MemberDAO.insertMember", member);
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapper.insertMember(member);
			sqlSession.commit(); // 커밋이 없다면 세션이 클로즈 되는 시점에 모든 데이터를 롤백해버림.
			// 세션이 클로즈될 때 커밋되지 않은 것 모두 커밋함. 데이터 상태가 변할 때 커밋 필수임.
			return rowcnt;
		}
		
		
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapper.updateMember(member);
			sqlSession.commit();
			return rowcnt;
		}
	}
	

	@Override
	public int deleteMember(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapper.deleteMember(memId);
			sqlSession.commit();
			return rowcnt;
		}
		
	}

	
}
