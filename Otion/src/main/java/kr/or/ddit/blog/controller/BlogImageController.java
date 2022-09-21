package kr.or.ddit.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/blog")
@MultipartConfig
public class BlogImageController {

	@Inject
	WebApplicationContext rootcontext;
	
	private File imageFolder;
	

	@PostMapping(value="uploadFile",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Map<String, Object> fileURL(MultipartFile image, HttpServletRequest request) {
		log.info("cr{}",image); //누느로 화긴
		log.info("nameCk: " + image.getOriginalFilename());
		
//		String imageName;
//		
//		if(image!=null && !image.isEmpty()&&image.getContentType().startsWith("image/")) {
//			imageName = UUID.randomUUID().toString();//저장명 
//		}
		
	
		// 상품 이미지 저장 처리(MultipartFile)
		String imageFolderUrl = "/resources/postImages";
		String iamgeFolderPath = rootcontext.getServletContext().getRealPath(imageFolderUrl);
		imageFolder = new File(iamgeFolderPath);
		if (!imageFolder.exists())
			imageFolder.mkdirs(); // 안만들어져있으면 폴더만들어라.
		log.info("주입전 {} 주입 후 {} 생성 및 확인", rootcontext, imageFolder);
		
		if (!image.isEmpty()) {// 이미지 폴더파일이 저장되었다면
//			throw new RuntimeException("강제");`
			File postImageFile = new File(imageFolder, image.getOriginalFilename());
			try {
				image.transferTo(postImageFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e); //언체크드 예외 
				//핸들러어댑터가 가져감 ->500 
			} 
		}
		
		
		String url = request.getContextPath()+"/resources/postImages/"+image.getOriginalFilename();

//		log.info("urlcheck:{} " + iamgeFolderPath+"/"+image.getOriginalFilename());
		
		log.info("urlcheck:{} " + url);
		
		Map<String, Object> respMap = new HashMap<String, Object>();
		Map<String, String> fileMap = new HashMap<String, String>();
		
		respMap.put("success", 1);
		fileMap.put("url", url);
		respMap.put("file", fileMap);
		log.info("respMap{}",respMap); //누느로 화긴
		return respMap;
	}
}
