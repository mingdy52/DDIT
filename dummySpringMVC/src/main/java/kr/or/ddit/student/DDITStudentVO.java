package kr.or.ddit.student;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.io.FileUtils;
import org.hibernate.validator.constraints.br.TituloEleitoral;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 등록할 학생 한명의 정보를 담을 ValueObject
 * JavaBean 규약(VO(ValueObject), DTO(DataTransferObject))
 * 1. value 를 담을 수 있는 property 필요.
 * 2. property 캡슐화 (정해진 절차하에서만 상태를 변경할 수 있음)
 * 3. property 상태를 변경할 수 있는 인터페이스(setter)
 * 4. property 상태를 접근할 수 있는 인터페이스(getter)
 * 		-> set[get] 프로퍼티명을 첫문자만 대문자로 바꾼 suffix ex)getName
 * 5. 객체의 상태를 비교할 수 있는 인터페이스 제공. --> 데이터 프라이머리 키 를 비교함.
 * 		==, equals
 * 6. 상태를 직접 확인할 수 있는 인터페이스 : toString 
 * 		-> toString 은 가능하면 꼭 필요한 것들로 만들 것.
 * 7. 객체가 매체를 통해 전송 혹은 저장될 수 있는 직렬화가 가능.
 *
 */

@Data
@EqualsAndHashCode(of="id")
@ToString(exclude="photo")
public class DDITStudentVO implements Serializable {
	@NotBlank(groups=UpdateGroup.class) // 컨트롤러에서 vo 검증은 insert 일 때 하는 거니까 검증 안함. 
	private String id;
	@NotBlank
	private String name; // 전역변수, property, field
	@Min(19)
	private int age;
	@NotBlank
	private String hp;
	@Email
	private String email;
	private String address;
	private String grade;
	private String school;
	private String subject;
	private String gdt;
	private String gender;
	private String[] licenses;
	private MultipartFile photo; // 이미지 파일
	private String photoName; // 이미지 파일의 이름
	
	
	public void setPhoto(MultipartFile photo) {
		if(photo!=null && !photo.isEmpty()) {
			this.photo = photo;
			this.photoName = UUID.randomUUID().toString();
		}
	}
	public void saveTo(File saveFolder) throws IOException {
		if(this.photo != null) {
			File saveFile = new File(saveFolder, this.photoName);
			FileUtils.copyInputStreamToFile(photo.getInputStream(), saveFile);
		}
	}
	
}
