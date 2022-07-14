package kr.or.ddit.memo;

import java.io.IOException;
import java.util.Base64;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.DeleteGroup;
import kr.or.ddit.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of="code")
@ToString(exclude= {"profileImage", "profileImg"})
public class MemoVO {
	@Min(value=1, groups= {UpdateGroup.class, DeleteGroup.class})
	private int code;
	@NotBlank
	private String writer;
	private String content;
	private MultipartFile profileImage; // 클라이언트가 업로드하는 이미지를 받기 위한 프로퍼티
	// 하이버네이트에는 이미지 즉, 마임 데이터를 검증할 수 있는 어노테이션 없음. 
//	1. 퍼스널 어노테이션 사용
//	2. 아래 setter 를 이용했다면 파일이 있는 것.
	
	private byte[] profileImg; // 데이터베이스의 BLOB 컬럼을 받기 위한 프로퍼티.
	// BLOB
	
	public void setProfileImage(MultipartFile profileImage) throws IOException {
		if(profileImage != null && !profileImage.isEmpty()) {
			if(!profileImage.getContentType().startsWith("image/")) {
				// 이미지 파일이 아니라면
				throw new IllegalArgumentException(String.format("%s 타입의 파일은 이미지가 아님.", profileImage.getContentType())); 
				// 파라미터가 잘못됐다.
			}
			this.profileImage = profileImage;
			this.profileImg = profileImage.getBytes();
		}
	}
	
	public String getBase64ProfileImage() {
		if(profileImg != null) {
			// Base64 -> 1.8
			// 1.8 이하 -> commos-codec
			return Base64.getEncoder().encodeToString(profileImg);
		} else {
			return null;
		}
		
	}
}
