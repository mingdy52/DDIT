<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<link href="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/skin-win8/ui.fancytree.min.css" rel="stylesheet">
<script src="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/jquery.fancytree-all-deps.min.js"></script>

</head>
<body>
<%-- 0624
	// listener 를 이용한 이벤트 처리. 이 코드를 리스터에 넣으면 이 서비스가 맨 처음 시동될 때 이벤트가 처리됌
	application.setAttribute("cPath", request.getContextPath()); // getContextPath 는 자주 사용되니 application에 넣자
--%>
<div id="tree"></div>
<ul>
<%--
	List<File> fileList = (List) request.getAttribute("fileList");
	for(File file : fileList){
		--%>
<%-- 			<li><%=file.getName() %></li> --%>
		<%--
	}
--%>
</ul>
<script type="text/javascript">
	$("#tree").fancytree({
		 source: 
			 {
			 	url : "${cPath}/server/browsing.do"
			 },
			 lazyLoad:function(event, data){
				 console.log(event);
				 console.log(data);
				 var node = data.node;
			      // Load child nodes via Ajax GET /getTreeData?mode=children&parent=1234
			      data.result = {
			        url: "${cPath}/server/browsing.do",
			        data: {mode: "children", parent: node.key},
			        cache: false
				 }
			 }

	});
</script>
</body>
</html>