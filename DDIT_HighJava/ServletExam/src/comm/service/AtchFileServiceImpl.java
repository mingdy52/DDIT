package comm.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.ibatis.sqlmap.client.SqlMapClient;

import comm.dao.AtchFileDAOImpl;
import comm.dao.IAtchFileDAO;
import comm.vo.AtchFileVO;
import util.SqlMapClientFactory;

public class AtchFileServiceImpl implements IAtchFileService {
	
	private static IAtchFileService service;
	private SqlMapClient smc;
	private IAtchFileDAO fileDao;
	
	private static final String UPLOAD_DIR = "d:/D_Other/upload_files";
	
	private AtchFileServiceImpl() {
		smc = SqlMapClientFactory.getInstance();
		fileDao = AtchFileDAOImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(service == null) {
			service = new AtchFileServiceImpl();
		}
		
		return service;
	}
	
	@Override
	public AtchFileVO saveAtchFileList(HttpServletRequest req) {
		
		// 서버 물리경로 구하기
		// ServletContext: 하나의 서블릿이 서블릿 컨테이너와 통신하기 위해서 사용되어지는 메서드들을 가지고 있는 클래스
		String uploadPath = UPLOAD_DIR;
								// ServletContext 객체를 가져오기 위한 메소드
								// File.separator: 프로그램이 실행 중인 OS에 해당하는 구분자를 리턴(윈도우:'\')
		
		File uploadDir = new File(uploadPath);
		
		if(!uploadDir.exists()) { // 업로드 경로가 존재하지 않으면
			uploadDir.mkdir();
		}
		
		AtchFileVO atchFileVO = null;
		
		try {
			
			String fileName = "";
			
			boolean isFirstFile = true; // 첫번째 파일 여부 체크용
			
			for(Part part : req.getParts()) {
					fileName = getFileName(part); // 파일명 추출
				
				if(fileName != null && !fileName.equals("")) {
					
					if(isFirstFile) { // 첫번째 파일정보에 접근하기
						isFirstFile = false;
						
						// 파일 기본저옵 저장하기
						atchFileVO = new AtchFileVO();
						
						// 기본 첨부파일정보 저장(VO에 atchFileId가 저장됨
						fileDao.insertAtchFile(smc, atchFileVO);
						
								
					}
					
					String orignFileName = fileName; // 원본 파일명
					long fileSize = part.getSize(); // 파일 사이즈
					String saveFileName = ""; // 저장 파일명
					String saveFilepath = ""; // 저장 파일 경로
					File storeFile = null; // 저장 파일 객체
					
					do {
						saveFileName = UUID.randomUUID().toString().replace("-", "");
						// UUID: 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약
						
						saveFilepath = uploadPath + File.separator + saveFileName;
						System.out.println("저장파일경로 : " + saveFilepath);
						storeFile = new File(saveFilepath);
						
					} while(storeFile.exists());

					// 확장명 추출
					String fileExtension = orignFileName.lastIndexOf(".") < 0 ? ""
								: orignFileName.substring(orignFileName.lastIndexOf(".") + 1);
					
					part.write(saveFilepath); // 업로드 파일 저장
					
					atchFileVO.setStreFileNm(saveFileName);
					atchFileVO.setFileSize(fileSize);
					atchFileVO.setOrignlFileNm(orignFileName);
					atchFileVO.setFileStreCours(saveFilepath);;
					atchFileVO.setFileExtsn(fileExtension);
					
					// 파일 세부정보 저장
					fileDao.insertAtchFileDetail(smc, atchFileVO);
					
					part.delete(); // 임시 저장 업로드 파일 삭제
					
					System.out.println("파일명: " + fileName + "업로드 완료");
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return atchFileVO;
	}
	
private String getFileName(Part part) { // fileUploadServlet2 참고

		for(String content : part.getHeader("Content-Disposition").split(";")) {
			if(content.trim().startsWith("filename")) {
					return content.substring(content.indexOf("=") + 1).trim().replace("\"", "");
			}
		}
		return null; // filename이 없는 경우 (폼필드)
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {
	
		return fileDao.getAtchFileList(smc, atchFileVO);
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		
		return fileDao.getAtchFileDetail(smc, atchFileVO);
	}
	

}
