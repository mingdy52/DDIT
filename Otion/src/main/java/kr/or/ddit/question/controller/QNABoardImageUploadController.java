package kr.or.ddit.question.controller;

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

/**
 * 
 * @author 서효림
 * @since 2022. 8. 27.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 27.       서효림          최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Controller
@RequestMapping("/qna")
public class QNABoardImageUploadController {
	@Inject
	private WebApplicationContext context;
	
	private ServletContext application;
	
	@Value("#{appInfo.imageURL}")
	private String imageFolderURL;
	
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		application = context.getServletContext();
		String realPath = application.getRealPath(imageFolderURL);
		saveFolder = new File(realPath);
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}
	
	@PostMapping(value="image", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> imageUpload(@RequestPart MultipartFile upload) throws IOException{
		Map<String, Object>resultMap = new HashMap<>();
		if(!upload.isEmpty()) {
			String saveName = UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, saveName);
			upload.transferTo(saveFile);
			String imageFileURL = application.getContextPath() + imageFolderURL + "/" + saveName;
			String fileName = upload.getOriginalFilename();
			int uploaded = 1;
			resultMap.put("fileName",fileName);
			resultMap.put("uploaded",uploaded);
			resultMap.put("url", imageFileURL);
		}else {
			Map<String, Object> error = new HashMap<>();
			error.put("number",400);
			error.put("message","업로드 된 파일이 없음.");
			resultMap.put("error",error);
		}
		return resultMap;
	}
	
}
