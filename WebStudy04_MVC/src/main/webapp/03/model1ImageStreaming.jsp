<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"
	integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
	crossorigin="anonymous">
	
</script>

</head>
<body>
	<%
// 		String folderPath = "D:\\contents";
// 		File folder = new File(folderPath);
// 		File[] children = folder.listFiles((dir, name)->{
// 			String mime = application.getMimeType(name);
// 			return mime != null && mime.startsWith("image/");
// 		});
	//	<option value='value'>text</option>
		
// 		String pattern = "<option>%s</option>";
// 		StringBuffer options = new StringBuffer();
// 		for (File tmp : children) {
// 			options.append(String.format(pattern, tmp.getName()));
// 		}
// 		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
// 		dataMap.put("options", options);
	%>
	<%!
		public File[] getChildren(File folder){
			File[] children = folder.listFiles((dir, name)->{
				String mime = getServletContext().getMimeType(name);
				return mime != null && mime.startsWith("image/");
			});
			return children;
		}
	%>
	
	<form action="<%=request.getContextPath() %>/02/streaming.do" id="imageForm">
		<select name="image">
		
			<% // 서블릿 소스 파싱시에 지역코드화(_JSPservice)
				
				String folderPath = "D:\\contents";
				File folder = new File(folderPath);
			
				File[] children = getChildren(folder);
				
				for (File tmp : children) { %>
				<option><%=tmp.getName() %></option>
			<% } %>
			
		</select> <input type="submit" value="전송" />
	</form>
	<div id="imageArea"></div>

	<script>
		let selectTag = $("select[name='image']").on("change", function() {
			//          $(this.form).trigger("submit");
			$(this.form).submit();
		});
		let imageArea = $("#imageArea");
		const SRCPTRN = "<%=request.getContextPath() %>/02/streaming.do?image=%v";
		$("#imageForm").on("submit", function(event) {
			event.preventDefault();
			//<img src="./streaming.do?image=%v"/>
			let imageName = $(this.image).val();
			let imgTag = $("<img>").attr("src", SRCPTRN.replace("%v", imageName));
			imageArea.html(imgTag);
			return false;
		});
	</script>

</body>
</html>