package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.mvc.DelegatingViewResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/file/upload.do")
public class FileUploadServlet extends HttpServlet {
	private ServletContext application;
	private static final String CHARSET = "UTF-8";
	private static final String ATTACHEDIR = "D:\\contents";
	private static final int LIMITSIZEBYTES = 1024 * 1024;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// fileSystemResource 저장위치
		File fileSystemFolder = new File("d:/contents");
		
		String webPath = "/resources/images";
		String fileSystemPath = getServletContext().getRealPath(webPath);
		// 애플리케이션 마다 서버의 위치가 달라지기 때문에.
		File webResourceFolder = new File(fileSystemPath);

		DiskFileItemFactory itemFactory = new DiskFileItemFactory();

		ServletFileUpload uploadHandler = new ServletFileUpload(itemFactory);
//		바디의 파트 하나하나를 객체화 해줌.
		try {
			List<FileItem> itemList = uploadHandler.parseRequest(req);
//		파트 하나하나를 객체로 파싱해서 FileItem로 캡슐화해줌. 
//		즉, 파트 하나와 FileItem 은 같은 의미를 가짐. 리스트 사이즈는 파트의 개수(여기서 3개)와 같음.
//		chunk : 이진 데이터가 클라이언트가 서버로 넘어갈 때 쪼개어 져서 넘어감. ex) 넘어가는 데이터가 엄청 크다면? 그냥 넘어갈 수 없지~!
//		ex) chunk가 10개라면 10개 모두 와야 파일이 모두 넘어간거임. 그러니까 넘어오는 chunk 들을 임시 저장소에 넣어서 가지고 있어야함.
//		DiskFileItemFactory ->  임시 저장소 운영, 청크가 다 모이면 파일아이템 만들어줌.
			if (itemList != null && !itemList.isEmpty()) {
				List<String> savePathList = new ArrayList<>();
				for (FileItem item : itemList) {
					String partName = item.getFieldName();
					if (item.isFormField()) {
						// 객체가 문자인지 파일인지 판단함. 문자면 트루

						String encoding = req.getCharacterEncoding();
						String partValue = item.getString(encoding);
						// 한국어는 특수 문자라 인코딩 해줘야함. 지금 필터에서 캐릭터 셋을 utf-8로 맞췄으니까 그 캐릭터 셋이 뭔지 찾아야함.

						log.info("파트명 : {}, 파트값 : {}", partName, partValue);
					} else {
						String savePath = null;
						if(partName.startsWith("fileSystem")) {
							String saveName = uploadFile(item, fileSystemFolder);
							savePath = new File(fileSystemFolder, saveName).getAbsolutePath();
							
						} else if(partName.startsWith("web")) {
							
							if(item.getContentType().startsWith("image/")) {
								String saveName = uploadFile(item, webResourceFolder);
								savePath = webPath + "/" + saveName;
								
							} 
						}
						savePathList.add(savePath);
					} // if(item.isFormField()) end
				} // for end
				req.getSession().setAttribute("savePathList", savePathList);
			} // if(itemList != null && !itemList.isEmpty()) end
			
			resp.sendRedirect(req.getContextPath() + "/15/fileUploadForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private String uploadFile(FileItem item, File saveFolder) throws IOException {
		String originalFilename = item.getName();
		String saveName = UUID.randomUUID().toString();
//		겹치지 않는 식별자를 랜덤으로 만들어줌.
			
		try (
				InputStream is = item.getInputStream();
		) {
			File dest = new File(saveFolder, saveName);
			FileUtils.copyInputStreamToFile(is, dest);
			// 가지고 있는 입력 스트림을 파일에 기록해줌.
			
			return saveName;
		}
		
	}

}
