package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comm.service.AtchFileServiceImpl;
import comm.service.IAtchFileService;
import comm.vo.AtchFileVO;

@WebServlet("/filedownload.do")
public class FileDownloadController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long fileId = req.getParameter("fileId") != null ?
				Long.parseLong(req.getParameter("fileId")) : -1;
		int fileSn = req.getParameter("fileSn") != null ?
				Integer.parseInt(req.getParameter("fileSn")) : 1;
				
		// 파일 정보 조회
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		AtchFileVO atchFileVO = new AtchFileVO();
		atchFileVO.setAtchFileId(fileId);
		atchFileVO.setFileSn(fileSn);
		
		atchFileVO = fileService.getAtchFileDetail(atchFileVO);
		
		// 파일 다운로드 처리를 위한 응답헤더 정보 설정하기
		resp.setContentType("application/octet-stream");
		
		// 오리지널 파일 이름으로 다운로드
		// URL에는 공백문자를 포함할 수 없다. 
		// URLEncoder를 이용하여 인코딩 작업을 하면 공백은(+)로 표시되기 때문에 (+) 문자를 공백문자를 의미하는 %20으로 바꿔준다.
		resp.setHeader("Content-Disposition", "attachment; filename=\"" 
							+ URLEncoder.encode(atchFileVO.getOrignlFileNm(), "UTF-8").replace("\\+", "%20") + "\"");
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(atchFileVO.getFileStreCours()));
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
		
		int data = 0;
		while((data = bis.read()) != -1) {
			bos.write(data);
		}
		
		bis.close();
		bos.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
