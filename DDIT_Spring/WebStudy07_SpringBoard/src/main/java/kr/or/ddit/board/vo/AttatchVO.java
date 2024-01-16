package kr.or.ddit.board.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of={"attNo", "boNo"})
@ToString(exclude= {"atchFile"})
@NoArgsConstructor
public class AttatchVO {

	private transient MultipartFile atchFile;
	public AttatchVO(MultipartFile atchFile) {
		super();
		this.atchFile = atchFile;
		attFilename = atchFile.getOriginalFilename();
		attSavename = UUID.randomUUID().toString();
		attMime = atchFile.getContentType();
		attFilesize = atchFile.getSize();
		attFancysize = FileUtils.byteCountToDisplaySize(attFilesize);		
	}
	
	public void saveTo(File saveFolder) throws IOException {
		if(atchFile!=null)
			atchFile.transferTo(new File(saveFolder, attSavename));
	}
	
	@NotNull
	private Integer attNo;
	@NotNull
	private Integer boNo;
	@NotBlank
	private String attFilename;
	@NotBlank
	private String attSavename;
	private String attMime;
	@NotNull
	private Long attFilesize;
	@NotBlank
	private String attFancysize;
	private Integer attDownload;
}
