<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="1kb" autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 응답 버퍼, out 을 통해 관리함 </h4>
<pre>
	JSPWriter 를 통해 버퍼 설정/상태확인/상태 변경 작업을 함.
	버퍼 크기 : <%=out.getBufferSize() %>
	잔여 크기 : <%=out.getRemaining() %>
	<%
		for(int i = 1;  i <= 100; i++){
			out.println(i + "번째 반복");
			if(out.getRemaining() < 20){
				out.flush(); // 내보낸다. 나가면 내가 어쩔 수 없음. 데이터 갈아치울 때 사용
// 				out.clearBuffer(); // 
			}
			if(i == 99){
				throw new RuntimeException("강제 발생 예외");
				// 데이터의 일부가 나가 있기 때문에 에러 페이지 발생 안함.
			}
		}
	%>
</pre>
</body>
</html>