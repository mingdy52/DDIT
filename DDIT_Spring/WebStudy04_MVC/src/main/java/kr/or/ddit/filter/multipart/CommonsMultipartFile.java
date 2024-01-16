package kr.or.ddit.filter.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 서블릿 스펙 2.x 인 경우 사용되는 Commons-FileUpload 의 FileItem 을 adaptee 로 가진 adapter 객체.
 *
 */
public class CommonsMultipartFile implements MultipartFile {
	
	private FileItem fileItem;
	
	public CommonsMultipartFile(FileItem adaptee) {
		super();
		this.fileItem = adaptee;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return IOUtils.toByteArray(getInputStream());
	}

	@Override
	public String getContentType() {
		return fileItem.getContentType();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return fileItem.getInputStream();
	}

	@Override
	public String getName() {
		return fileItem.getFieldName();
	}

	@Override
	public String getOriginalFilename() {
		return fileItem.getName();
	}

	@Override
	public long getSize() {
		return fileItem.getSize();
	}

	@Override
	public boolean isEmpty() {
//		return fileItem.getSize() == 0; // 보낸 파일의 사이즈가 0일 수 있음
		return StringUtils.isBlank(getOriginalFilename());
	}


}
