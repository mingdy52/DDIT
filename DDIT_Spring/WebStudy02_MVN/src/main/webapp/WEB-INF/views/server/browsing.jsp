<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/js/fancytree/ui.fancytree.min.css"> --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/fancytree/jquery.fancytree-all-deps.min.js"></script> --%>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<link href="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/skin-win8/ui.fancytree.min.css" rel="stylesheet">
<script src="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/jquery.fancytree-all-deps.min.js"></script>

</head>
<body>
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
			 	url : "<%=request.getContextPath() %>/server/browsing.do"
			 },
			 lazyLoad:function(event, data){
				 console.log(event);
				 console.log(data);
				 var node = data.node;
			      // Load child nodes via Ajax GET /getTreeData?mode=children&parent=1234
			      data.result = {
			        url: "<%=request.getContextPath() %>/server/browsing.do",
			        data: {mode: "children", parent: node.key},
			        cache: false
				 }
			 }

	});
</script>
</body>
</html>