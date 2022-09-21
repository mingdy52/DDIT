package kr.or.ddit.blog.vo;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import kr.or.ddit.common.validate.InsertGroup;
import kr.or.ddit.common.validate.UpdateGroup;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@EqualsAndHashCode(of="postNum")
//@ToString(exclude= {"blReplyContent"})
public class BlogReplyVO {
	private Integer rnum;
	private String blReplyDelYn;
	@NotBlank(groups= {UpdateGroup.class})
	private String blReplyNum;
	@NotBlank(groups= {InsertGroup.class})
	private String postNum;
	private String blReplyWriter;
	
	@NotBlank(groups= {InsertGroup.class,UpdateGroup.class})
	@Size(min = 1, max = 150)
	private String blReplyContent;
	private String blReplyDate;
	private String parentBlReplyNum;
	
	private String postTitle;
	private String blogerId;
	private List<String> blReplyNumList;
	
	//대댓글시 필요한 댓글의 Id
	private String ownerWriter;
}
