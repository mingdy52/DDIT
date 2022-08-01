import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import service.BoardService;
import service.IBoardHwService;
import util.JDBCUtil3;
import vo.BoardHwVO;

public class BoardMain {
	Scanner sc = new Scanner(System.in);
	private IBoardHwService boSer;
	
	public BoardMain() {
		boSer = BoardService.getInstance();
	}

	public static void main(String[] args) {
		BoardMain boMain = new BoardMain();
		boMain.start();
	}

	private void start() {
		while (true) {
			System.out.println("------------------------------------------");
			System.out.println("1. 전체 목록 출력  2. 새글작성  3. 수정  4.삭제  5.검색 ");
			System.out.println("------------------------------------------");

			System.out.print("메뉴 선택 => ");
			int num = sc.nextInt();

			switch (num) {
			case 1:
				displayAll();
				break;

			case 2:
				writeNew();
				break;

			case 3:
				updatePost();
				break;

			case 4:
				del();
				break;

			case 5:
				search();
				break;

			default:
				System.out.println("다시 입력하세요.");
				break;
			}
		}
	}

	private void search() { 
		sc.nextLine();
		System.out.println("검색어를 입력하세요");
		
		System.out.print("글제목 => ");
		String boTitle = sc.nextLine();
		
		System.out.print("작가 => ");
		String boWriter = sc.nextLine();
		
		System.out.print("내용 => ");
		String boContent = sc.nextLine();
		
		BoardHwVO bo = new BoardHwVO();
		bo.setBoTitle(boTitle);
		bo.setBoWriter(boWriter);
		bo.setBoContent(boContent);
		
		List<BoardHwVO> boList = boSer.search(bo);
		
		
		if(boList.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
		} else {
			for(BoardHwVO boV : boList) {
				
				System.out.println(boV.getBoNo() + "\t" + boV.getBoTitle() + "\t" 
						+ boV.getBoWriter() + "\t\t" + boV.getBoDate() 
						+ "\t" + boV.getBoContent());
			}
		}
	}

	private void del() {
		System.out.println("수정할 글의 정보를 입력하세요.");
		System.out.print("제목 : ");
		String title = sc.next();
		
		int cnt = boSer.deletePost(title);
		
		if(cnt > 0) {
			System.out.println(title + "이 삭제되었습니다.");
		} else {
			System.out.println(title + "삭제 실패");
			
		}
	}

	private void updatePost() {
		
		sc.nextLine(); // 입력버퍼 비우기
		System.out.print("수정할 글 번호>>");
		String boNo = sc.next();
		sc.nextLine(); // 버퍼 비우기(엔터값 제거)
		System.out.print("글 내용>>"); 
		String boContent = sc.nextLine();
		
		
		BoardHwVO boVo = new BoardHwVO();
		boVo.setBoNo(boNo);
		boVo.setBoContent(boContent);
		
		int cnt = boSer.updatePost(boVo);
			if(cnt > 0) {
				System.out.println("게시글 수정 성공.");
			}

	}
	

	private void writeNew() {
		
			System.out.print("작가 : ");
			String boWriter = sc.next();
			System.out.print("제목 : ");
			String boTitle = sc.next();
			System.out.print("내용 : ");
			String boContent = sc.next();
			
			BoardHwVO bo = new BoardHwVO();
			bo.setBoTitle(boTitle);
			bo.setBoWriter(boWriter);
			bo.setBoContent(boContent);
			
			int cnt = boSer.writeNew(bo);
			if(cnt > 0) {
				System.out.println("등록 성공");
			}
	}
	

	
	private void displayAll() {
		
	
		List<BoardHwVO> boList = boSer.displayAll();
		
		if(boList.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다.");
		} else {
			for(BoardHwVO boVo : boList) {
				
				System.out.println("글번호: " + boVo.getBoNo());
				System.out.println("글제목: " + boVo.getBoTitle());
				System.out.println("작가: " + boVo.getBoWriter());
				System.out.println("날짜: " + boVo.getBoDate());
				System.out.println("내용: " + boVo.getBoContent() + "\n");
			}
		}

	}
}
