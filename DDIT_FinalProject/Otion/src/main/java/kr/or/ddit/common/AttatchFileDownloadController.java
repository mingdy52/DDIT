package kr.or.ddit.common;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.common.attach.service.AttatchService;
import kr.or.ddit.common.vo.AttatchVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor // final을 넣기 위함
@RequestMapping("/download")
@Slf4j
public class AttatchFileDownloadController {
	
	private final AttatchService attatchService;
	
	@GetMapping
	public String download(
			@RequestParam String[] attatchNum,
			@RequestParam int[] attatchOrder,
			Model model
			) {
		List<AttatchVO> downList = new ArrayList<>();
		for(int i=0; i<attatchNum.length; i++) {
			AttatchVO attatch = new AttatchVO();
			attatch.setAttatchNum(attatchNum[i]);
			attatch.setAttatchOrder(attatchOrder[i]);
			downList.add(attatchService.multiDownload(attatch));
		}
		log.info("########## donwList 값 : {}",downList);
		model.addAttribute("attatchVO", downList);
		
		return "downloadView";
	}
}
