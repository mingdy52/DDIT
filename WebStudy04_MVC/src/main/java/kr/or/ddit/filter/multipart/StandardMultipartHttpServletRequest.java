package kr.or.ddit.filter.multipart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

public class StandardMultipartHttpServletRequest extends HttpServletRequestWrapper {
	
	private Map<String, List<MultipartFile>> multiPartFiles;
//	키 : 파트의 이름 
//	밸류 : 파트의 값. 리스트로 잡는 이유는 하나의 이름으로 두개 이상의 멀티파트 가 들어갈 수 있기 때문.
	
	public StandardMultipartHttpServletRequest/*어댑터*/(HttpServletRequest request /*어댑티*/) throws IOException, ServletException {
		super(request);
		multiPartFiles = new LinkedHashMap<>();
		parseRequest(request);
	}
	
	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		for (Part single : parts) {
			String contentType = single.getContentType();
			if(StringUtils.isBlank(contentType)) continue;
			
			String partName = single.getName();
			StandardServletMultiPartFile multipartFile 
						= new StandardServletMultiPartFile(single);
			
			List<MultipartFile> alreadyFiles = multiPartFiles.get(partName); // 이름은 동일한데 여러개의 파트가 있을 때
			if(alreadyFiles==null) {
				alreadyFiles = new ArrayList<>();
				multiPartFiles.put(partName, alreadyFiles);
			}
			
			alreadyFiles.add(multipartFile);
		}
		
	}
	
	public Map<String, List<MultipartFile>> getMultiPartFiles(){
		return multiPartFiles;
	}
	
	public MultipartFile getFile(String partName) {
		List<MultipartFile> files = multiPartFiles.get(partName);
		if(files != null) { // 일단 리스트 있다.
			return files.get(0);
			
		} else { // 그런 파일 없다.
			return null;
		}
	}
	
	public List<MultipartFile> getFiles(String partName) {
		return multiPartFiles.get(partName); // 해당하는 이름 파일 있으면 반환하고 없으며 null
	}
	
}
