package kr.or.ddit.common;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.common.vo.AttatchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttatchDownloadView extends AbstractView {

	@Value("#{appInfo['attatchPath']}")
	private File saveFolder;

	@PostConstruct
	public void init() throws IOException {
		log.info("주입된 저장 경로 : {}", saveFolder.getCanonicalPath());
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<AttatchVO> attatchList = (List<AttatchVO>) model.get("attatchVO");

		for (AttatchVO attatch : attatchList) {
			fileDownload(attatch, response);
		}

	}
	
	public void fileDownload(AttatchVO attatch, HttpServletResponse response)throws Exception{
		String saveName = attatch.getSaveNm();
		File saveFile = new File(saveFolder, saveName);
		if (!saveFile.exists()) {
			// 없는 파일을 요청 = 오류
			response.sendError(404, "해당 파일은 없음.");
			return;
		}
		// commons-compress : 폴더를 압축.
		// 한글 인코딩해야함
		String filename = attatch.getOriginNm();
		filename = URLEncoder.encode(filename, "UTF-8").replace("+", " ");

		// 해당하는 파일명을 넣어줘야함(다운을 받고 파일형태로 받겟다)
		// 파일명에 공백이 있으면 \\를 "로 묶어서 사용해야함
		response.setHeader("Content-Disposition", "attathment;filename=\"" + filename + "\"");
		// 다운로드가 아닌 해당 파일을 인터넷에 띄워줌
		// resp.setHeader("Content-Disposition", "inline;filename=\"" + filename +
		// "\"");
		response.setContentLengthLong(saveFile.length());
		// FileInputStream fis = new FileInputStream(file);
		// 파일이 넘어올 땐 2진 데이터로 넘어옴
		try (OutputStream os = response.getOutputStream();) {
			// 해당 파일을 다운로하는 것
			FileUtils.copyFile(saveFile, os);
		} catch (IOException e) {

		}
	}

}
