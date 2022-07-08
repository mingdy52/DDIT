package kr.or.ddit.filter.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

public interface MultipartFile {
	
	public byte[] getBytes() throws IOException;
	public String getContentType();
	public InputStream getInputStream() throws IOException;
	public String getName();
	public String getOriginalFilename();
	public long getSize();
	public boolean isEmpty();
	
	public default void transferTo(File dest) throws IOException {
		// 디폴트를 사용하면 인터페이스에서도 메소드를 사용할 수 있음.
		FileUtils.copyInputStreamToFile(getInputStream(), dest);
	} // 구현체는 안보이지만 이미 상속받고 있음.
	
}
