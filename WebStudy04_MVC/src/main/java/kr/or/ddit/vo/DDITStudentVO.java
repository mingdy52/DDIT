package kr.or.ddit.vo;

import java.io.Serializable;
import java.util.Arrays;

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

public class DDITStudentVO implements Serializable {
	private String id = "a001";
	private String name; // 전역변수, property, field
	private int age;
	private String hp;
	private String email;
	private String address;
	private String grade;
	private String school;
	private String subject;
	private String gdt;
	private String gender;
	private String[] licenses;
	private String photo; // 이미지 파일의 이름
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getGdt() {
		return gdt;
	}
	public void setGdt(String gdt) {
		this.gdt = gdt;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String[] getLicenses() {
		return licenses;
	}
	public void setLicenses(String[] licenses) {
		this.licenses = licenses;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DDITStudentVO other = (DDITStudentVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}
