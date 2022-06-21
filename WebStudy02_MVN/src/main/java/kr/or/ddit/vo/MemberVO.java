package kr.or.ddit.vo;

import java.io.Serializable;

// DTO
public class MemberVO implements Serializable {
	// Serializable 는 가지고 있는 메소드가 없음. 현재 데이터가 직렬화 할 수 있는지 확인하는 역할만 함.
	// 이런 인터페이스를 marker interface 라고 함. - 비슷한 애 : marker annotation
	
	private String memId;
	private transient String memPass;
//	transient : transient가 설정된 데이터는 객체가 직렬화 되더라도 직렬화에서 빠져나옴.
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memId == null) ? 0 : memId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (memId == null) {
			if (other.memId != null)
				return false;
		} else if (!memId.equals(other.memId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + "]";
	}
	
	
	
	
}
