package kr.or.ddit.common.vo;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import kr.or.ddit.common.validate.DeleteGroup;
import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(of= {"memId"})
@ToString(exclude= {"memPass"})
@NoArgsConstructor
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
public class MemberVO implements Serializable {
	private int rnum;
    @NotBlank(groups= {DeleteGroup.class}, message="아이디를 입력해주세요")
    private String memId;
    
    @NotBlank(groups= {DeleteGroup.class},message="비밀번호를 입력해주세요")
    @Size(min=4, max=8, groups= {Default.class, DeleteGroup.class} ,message="비밀번호는 4~8자 사이입니다.")
    private String memPass;
    
    private String newPass;
    @NotBlank(groups= {InsertGroup.class})
    private String memName;
    
    @Pattern(regexp="^[가-힣a-zA-Z]{4,8}$")
    @Size(min=4, max=8, groups= {UpdateGroup.class})
    private String memNick;
    
    @NotBlank(groups= {InsertGroup.class})
    @Email(message="이메일 계정 확인")
    private String memMail;
    
    @NotBlank(groups= {InsertGroup.class})
    @Pattern(regexp="\\d{3}-\\d{3,4}-\\d{4}")
    private String memHp;
    private Integer accumRep;
    
    private String delReason;
    
    private String blackContent;
    private String blackDate;
    
    private List<RoleVO> roleList;
    
    private int roleUser;
	private int roleExpert;
	private int roleProject;
	
	public String[] getRoleListArray() {
		String[] array = null;
		if(roleList != null) {
			array = new String[roleList.size()];
			for(int i=0; i<roleList.size(); i++) {
				array[i]=roleList.get(i).getRoleCode();
			}
		}
		return array;
	}
}