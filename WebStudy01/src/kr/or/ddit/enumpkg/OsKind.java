package kr.or.ddit.enumpkg;

public enum OsKind {

	WINDOWS("윈도우"), ANDROID("안드로이드"), IPHONE("아이폰"), UBUNTU("우분투"), UNKNOWN("식별불가 OS");
	private String os;

	private OsKind(String os) {
		this.os = os;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public static OsKind findOs(String userAgent) {
		userAgent = userAgent.toUpperCase();

		OsKind findedOs = OsKind.UNKNOWN;
//		String os = null;
//		os = OsKind.UNKNOWN.getOs();

		for (OsKind os : OsKind.values()) {
			if (userAgent.contains(os.name())) {
				
				findedOs = os;
				break;
			}
		}
		return findedOs;
	}
	

	

	public static String findOsName(String userAgent) {
		OsKind findedOs = findOs(userAgent);
		return findedOs.getOs();

	}
	// OsKind 타입의 상수객체 5개
}
