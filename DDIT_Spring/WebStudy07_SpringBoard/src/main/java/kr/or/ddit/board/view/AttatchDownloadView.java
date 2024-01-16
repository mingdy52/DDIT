package kr.or.ddit.board.view;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.board.vo.AttatchVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AttatchDownloadView extends AbstractView {
	
	@Value("#{appInfo['attatchPath']}")
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		log.info("주입된 저장 경로 : {}", saveFolder.getCanonicalPath());
	}
	// 하위컨테이너라 상위 컨테이너에 있는 거 사용 가능
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse resp) throws Exception {
		AttatchVO attatch = (AttatchVO) model.get("attatch");
		
		String savename = attatch.getAttSavename();
		File saveFile = new File(saveFolder, savename);
		
		if(!saveFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 파일은 없음.");
			return;
		}
		
		String fileName = saveFile.getName();
		fileName = URLEncoder.encode(fileName, "UTF-8").replace("+", "");
		
		resp.setHeader("Content-Disposition", "attachment;filename=\""+ fileName+"\"");
		resp.setContentLengthLong(saveFile.length()); // 파일의 길이 가져오기
		
		try(
				OutputStream os = resp.getOutputStream();
			){
			FileUtils.copyFile(saveFile, os);
		}
	}

}
