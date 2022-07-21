package kr.or.ddit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.vo.AttatchVO;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AttatchFileDownloadController {
	
	private final BoardService service;
	
	@RequestMapping("/board/{boNo}/attatch/{attNo}")
	public String download(
			@PathVariable int boNo
			, @PathVariable int attNo
			, Model model
			) {
		AttatchVO attatch = service.download(attNo);
		model.addAttribute("attatch", attatch);
		return "downloadView";
		
	}
	
}
