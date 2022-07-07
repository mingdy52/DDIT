package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.mvc.DelegatingViewResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/file/upload_3.do")
@MultipartConfig(maxFileSize=-1, maxRequestSize=-1) // 이게 있으면 서블릿 3점대 버전을 사용해서 part 인터페이스를 사용할 수 있음.
public class FileUploadServletSpec3 extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uploader = req.getParameter("uploader");
		Part fileSystemPart = req.getPart("fileSystemResource");
		Part webResourcePart = req.getPart("webResource");
		log.info("uploader : {}", uploader);
		log.info("fileSystemResource : {}", fileSystemPart);
		log.info("webResource : {}", webResourcePart);
		
		File fileSystemFolder = new File("d:/contents");
		String webPath = "/resources/images";
		String fileSystemPath = getServletContext().getRealPath(webPath);
		File webResourceFolder = new File(fileSystemPath);

		List<String> savePathList = new ArrayList<>();
		req.getSession().setAttribute("savePathList", savePathList);
		String savePath = null;
		
		String saveName = uploadFile(fileSystemPart, fileSystemFolder);
		savePath = new File(fileSystemFolder, saveName).getAbsolutePath();
		savePathList.add(savePath);
		if (webResourcePart.getContentType().startsWith("image/")) {
			saveName = uploadFile(webResourcePart, webResourceFolder);
			savePath = webPath + "/" + saveName;
		}
		
		savePathList.add(savePath);
		new DelegatingViewResolver().viewResolve("redirect:/15/fileUploadForm.jsp", req, resp);
	}
	
	private String uploadFile(Part part, File saveFolder) throws IOException {
//		FileItem과 Part 는 가지고 있는 스펙이 비슷함.
		
		String originalFilename = part.getSubmittedFileName();
		String saveName = UUID.randomUUID().toString();
//		겹치지 않는 식별자를 랜덤으로 만들어줌.
			
		try (
				InputStream is = part.getInputStream();
		) {
			File dest = new File(saveFolder, saveName);
			FileUtils.copyInputStreamToFile(is, dest);
			// 가지고 있는 입력 스트림을 파일에 기록해줌.
			
			return originalFilename;
		}
		
	}
	
}
