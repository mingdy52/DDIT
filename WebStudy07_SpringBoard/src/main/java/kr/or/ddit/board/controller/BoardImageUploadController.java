package kr.or.ddit.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/board")
public class BoardImageUploadController {
	
	@Inject
	private WebApplicationContext context;
	private ServletContext application;
	
	
	@Value("#{appInfo.imageURL}") // 밸류를 사용해서 스프링 밸류를 사용할 수 있음.
	private String imageFolderURL;
	
	private File saveFolder;
	
	@PostConstruct
	public void init() { // 라이프 사이클 콜백
		application = context.getServletContext();
		String realPath = application.getRealPath(imageFolderURL);
		saveFolder = new File(realPath);
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}
	
	@PostMapping(value="image", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> imageUpload(
			@RequestPart MultipartFile upload
			) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(!upload.isEmpty()/*파일을 선택하지 않고 업로드*/) {
//			저장 경로
//			1. 파일 시스템
//			2. 클래스패스
//			3. 웹 리소스 -- 당첨
			
			String saveName = UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, saveName); // 세이프 폴더에 세이브 네임으로 저장
			upload.transferTo(saveFile);
			String imageFileURL =  application.getContextPath() + imageFolderURL + "/" + saveName;
			String fileName = upload.getOriginalFilename();
			int uploaded = 1;
			
			resultMap.put("fileName", fileName);
			resultMap.put("uploaded", uploaded);
			resultMap.put("url", imageFileURL);
			
		} else {
			Map<String, Object> error = new HashMap<>();
			error.put("number", 400);
			error.put("message", "업로드 된 파일이 없음.");
			
			resultMap.put("error", error);
			
		}
		
		return resultMap;
	}
}
