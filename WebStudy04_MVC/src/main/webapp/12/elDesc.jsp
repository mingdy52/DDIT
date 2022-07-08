<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>표현 언어(Expression Language) : Model2 구조에서 활용</h4>
	<pre>
		: 값을 출력하기 위한 기능만을 갖고있는 언어. 제어문의 형태가 없음. \${속성명 : 영문, 숫자 } -> 스크립트 형태 언어
		<%
			String sample = "샘플";
// 			pageContext.setAttribute("sample", sample);
			pageContext.setAttribute("blank", "   ");
			pageContext.setAttribute("list", Arrays.asList("v1", "v2"));
// 			pageContext.setAttribute("list", null);
			pageContext.setAttribute("test", "a");
			
			MemberVO member1 = new MemberVO();
			pageContext.setAttribute("member", member1);
			
			MemberVO member2 = new MemberVO();
			member2.setMemName("신규");
			request.setAttribute("member", member2);
		%>
		<%= sample %>, ${sample }
		
		1. EL 연산자
			1) 산술연산자 : +-/*%, +: 오직 산술연산자만! 컨캣 연산 안함.
				${1+1 }, ${"1" + 1 } -- 알아서 변수의 형태를 변환해서 사용함. 스크립트니까 실행시에 변수의 타입이 결정
				\${"a" + 1 } -- 넘버 포맷 예외 발생
				${a + 1 } -- a는 데이터로 간주하는게 아닌 속성으로 간주함. a 라는 속성이 없으니까 null 이뜨는데 이 널을 0으로 바꿔서 연산함.
				${sample + 1 } -- 넘버 포맷 예외 발생
			2) 논리연산자 : &&(and),  ||(or),  !(not)
				${true and true }, ${"true" and true } -- 강제로 논리값으로 바꿈
				${a and true } -- a는 데이터로 간주하는게 아닌 속성으로 간주함. a 라는 속성이 없으니까 null 이 뜨는데 null은 논리 연산 못함. -- false
				${a or true } -- a는 데이터로 간주하는게 아닌 속성으로 간주함. a 라는 속성이 없으니까 null 이 뜨는데  or true 니까 -- true
			3) 단항연산자 : empty (StringUtils 의 is(not)blank 와 비슷)
				- 속성의 존재여부 -> null 여부 -> String(length), Collection(size)
				el 은 값을 가져오기 위해 존재하기 때문에 할당 연산자는 존재하지 않음. but 가장 최근에 나온 el은 할당연산자 지원함. 우리나라는 아직...
				${ empty sample } -- false
				${ empty a } -- true
				${ empty blank } -- true empty는 null 체크만 하는게 아님. 데이터의 길이를 체크해서 판단함. blank 에 공백을 넣어보면 알음.
				${ not empty list } -- true
				
				
				1. 속성이 있나 없나
				2. 속성이 null 인가
				3. 객체의 길이가 0인가
				
			4) 3항 연산자 : 조건식 ? 참 : 거짓
				${not empty test? "있다" : "없다" } -- 있다.
				
				**
				1. 컨캣 지원안함
				2. 연산의 중심은 연산자
				
		2. 객체 접근 방법
			${member } -- null 이 나오지 않음. el 은 null 을 출력할 때 강제로 화이트 스페이스로 바꿔서 처리함.
			효율적으로 찾으려고 작은 영역 스코프부터 찾아봄 -- member1
			${requestScope.member } -- member2
			${requestScope.member.getMemName() }-- 옛날버전은 메소드 호출 못함. 
			${requestScope.member.memName } -- 프로퍼티는 접근 못함! 내부적으로 게터 사용
			
			${requestScope.member.getMemTest() }
			${requestScope.member.memTest }
			
			${requestScope.member["memName"] }
			
			1) scope 객체 : pageScope, requestScope, sessionScope, applicationScope 
			
		3. 집합 객체 접근 방법 : <a href="elCollection.jsp">기본객체</a>
		
		4. 이벤트 헨들러를 target 대해 listening 할 수 있도록 부착함 : web.xml -> listener
		
	</pre>
</body>
</html>