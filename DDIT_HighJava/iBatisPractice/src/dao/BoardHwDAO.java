package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.JDBCUtil3;
import vo.BoardHwVO;

public class BoardHwDAO implements ImBoardHwDAO{
	Scanner sc = new Scanner(System.in);
	private static ImBoardHwDAO boDAO;
	private BoardHwDAO() {
		
	}
	public static ImBoardHwDAO getInstance() {
		if(boDAO==null) {
			boDAO = new BoardHwDAO();
		}
		return boDAO;
	}
	
	@Override
	public List<BoardHwVO> displayAll(SqlMapClient smc) {
		
		List<BoardHwVO> boList = new ArrayList<BoardHwVO>();
		
		try {
				boList = smc.queryForList("board.displayAll");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("출력 실패");
			e.printStackTrace();
		} 
		return boList;
	}

	@Override
	public int writeNew(SqlMapClient smc, BoardHwVO bo) {
		int cnt = 0;
		
		try {
			
			cnt = smc.update("board.writeNew", bo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("등록 실패");
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int updatePost(SqlMapClient smc, BoardHwVO bo) {
		int cnt = 0;
		
		try {
		
			cnt = smc.update("board.updatePost", bo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return cnt;
	}

	@Override
	public int deletePost(SqlMapClient smc, String bo) {
		int cnt = 0;
		
		try {
			
			cnt = smc.update("board.deletePost", bo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<BoardHwVO> search(SqlMapClient smc, BoardHwVO bo) {
		List<BoardHwVO> boList = new ArrayList<BoardHwVO>();
		
		try {
			boList = smc.queryForList("board.search", bo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return boList;
	}
	
	



	
}
