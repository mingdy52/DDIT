package kr.or.ddit.filter.multipart;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 서블릿 스펙 3.x 기반으로 Part 를 adaptee 로 갖는 adapter 객체.
 *
 */
public class StandardServletMultiPartFile implements MultipartFile {
	
	private Part part;
	
	public StandardServletMultiPartFile(Part part) {
		super();
		this.part = part;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return IOUtils.toByteArray(getInputStream());
	}

	@Override
	public String getContentType() {
		return part.getContentType();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return part.getInputStream();
	}

	@Override
	public String getName() {
		return part.getName();
	}

	@Override
	public String getOriginalFilename() {
		return part.getSubmittedFileName();
	}

	@Override
	public long getSize() {
		return part.getSize();
	}

	@Override
	public boolean isEmpty() {
		return StringUtils.isBlank(getOriginalFilename());
	}
	
	@Override
	public void transferTo(File dest) throws IOException {
		part.write(dest.getCanonicalPath());
		// 현재 파트를 어디에 저장할건지~
	}

}
