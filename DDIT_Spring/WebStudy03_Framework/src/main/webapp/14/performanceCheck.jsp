<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>14/performanceCheck.jsp</title>
</head>
<body>
	<h4> 성능 향상 요소 </h4>
	<pre>
		공간적 요소(memory 소요량) : String -> StringBuffer
			String str = "ab";
			str += "dc";	String.format("%s dc", "ab")
			=> abdc 
			-> 메모리 공간이 세개가 됌. 기본형은 주소가 없으니까
			
			StringBuffer sb = new StringBuffer("ab");
			sb.append("dc")
			=> abdc
			-> 하나의 메모리 공간. 메모리상 이게 더 효율적.
			
		시간적 요소(소요 시간) : latency time + processing time
			
		<a href="oneConnOneProcess.jsp">한번의 처리와 한번의 연결지연 : 12ms, 1ms</a>
		<a href="100Conn100Process.jsp">백번의 처리와 백번의 연결지연 : 1100ms, 55ms</a>
		- 2700보다 작다 = 두 시간 비율이 1:1이 아님.
		<a href="oneConn100Process.jsp">백번의 처리와 한번의 연결지연 : 10ms, 1ms</a>
		<a href="100ConnOneProcess.jsp">한번의 처리와 백번의 연결지연 : 930ms, 18ms</a>
	</pre>

</body>
</html>