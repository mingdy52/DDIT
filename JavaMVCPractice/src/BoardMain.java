import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import service.BoardService;
import util.JDBCUtil3;
import vo.BoardHwVO;

public class BoardMain {
	Scanner sc = new Scanner(System.in);
	private BoardService boSer;
	
	public BoardMain() {
		boSer = new BoardService();
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
				modify();
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
		String title = sc.nextLine();
		
		System.out.print("작가 => ");
		String writer = sc.nextLine();
		
		System.out.print("내용 => ");
		String content = sc.nextLine();
		
		BoardHwVO boVo = new BoardHwVO();
		boVo.setBoTitle(title);
		boVo.setBoWriter(writer);
		boVo.setBoContent(content);
		
		List<BoardHwVO> boList = boSer.search(boVo);
		
		
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

	private void modify() {
		
		boolean chk = false; // 등록여부 체크용
		String title = "";
				
		do {
			System.out.println();
			System.out.println("수정할 글의 정보를 입력하세요.");
			System.out.print("제목 : ");
			title = sc.next();
			
			chk = check(title);
			
			if(chk == false) {
				System.out.println(title + "의 게시글이 존재하지 않습니다.");
			}
			
		} while(chk == false);
		
		System.out.print("내용 : ");
		String content = sc.next();
		
		BoardHwVO boVo = new BoardHwVO();
		
		boVo.setBoContent(content);
		
		int cnt = boSer.updateNew(boVo);
			if(cnt > 0) {
				System.out.println("게시글 수정 성공.");
			}

	}
	

	private boolean check(String title) {
		return boSer.check(title);
	}

	private void writeNew() {
		
			System.out.print("작가 : ");
			String writer = sc.next();
			System.out.print("제목 : ");
			String title = sc.next();
			System.out.print("내용 : ");
			String content = sc.next();
			
			BoardHwVO boVo = new BoardHwVO();
			boVo.setBoTitle(title);
			boVo.setBoWriter(writer);
			boVo.setBoContent(content);
			
			int cnt = boSer.insertNew(boVo);
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
