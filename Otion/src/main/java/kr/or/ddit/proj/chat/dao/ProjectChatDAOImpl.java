package kr.or.ddit.proj.chat.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.common.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.proj.chat.vo.ProjectChatRoomVO;
import kr.or.ddit.proj.chat.vo.ProjectChatVO;
import kr.or.ddit.proj.colleague.vo.ColleagueVO;

public class ProjectChatDAOImpl implements ProjectChatDAO{
	
	private SqlSessionFactory sqlSessionFactory
	= CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<ProjectChatVO> selectChatList(ColleagueVO colleague) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertChat(ProjectChatVO chatVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProjectChatVO selectChatRoom(ProjectChatVO chatVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertChatting(ProjectChatRoomVO roomVO) {
		// TODO Auto-generated method stub
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			ProjectChatDAO mapper = sqlSession.getMapper(ProjectChatDAO.class);
			mapper.insertChatting(roomVO);
			sqlSession.commit();
		}
	}

	@Override
	public int countChatting(ProjectChatRoomVO roomVO) {
		// TODO Auto-generated method stub
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			ProjectChatDAO mapper = sqlSession.getMapper(ProjectChatDAO.class);
			int cnt = mapper.countChatting(roomVO);
			return cnt;
		}
	}

	@Override
	public ProjectChatVO selectChat(String crNum) {
		// TODO Auto-generated method stub
		return null;
	}

}
