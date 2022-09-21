package kr.or.ddit.common.register.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = { "memId" })
@ToString(exclude = { "memPass", "memCheckPass" })
public class RegisterVO {
	@NotBlank(groups = { InsertGroup.class })
	private String memId;
	@NotBlank(groups = { DeleteGroup.class }, message = "비밀번호를 입력해주세요")
	@Size(min = 4, max = 8, groups = { Default.class, DeleteGroup.class }, message = "비밀번호는 4~8자 사이입니다.")
	private String memPass;
	@NotBlank(groups = { InsertGroup.class })
	private String memCheckPass;
	@NotBlank(groups = { InsertGroup.class })
	private String memName;
	private String memNick;
	@NotBlank(groups = { InsertGroup.class })
	@Email(message = "이메일 계정 확인")
	private String memMail;
	@NotBlank(groups = { InsertGroup.class })
	@Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}")
	private String memHp;
	private Integer accumRep;
}
