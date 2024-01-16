<%--
* [[개정이력(Modification Information)]]
* 수정일                 수정자      수정내용
* ----------  ---------  -----------------
* 2022. 8. 24.      박채록      최초작성
* Copyright (c) 2022 by DDIT All right reserved
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<form action="/Otion/blog/uploadFile" enctype="multipart/form-data" method="post">
	<input type="file" name="file" value=""><br>
	<input type="submit" value="채록채록">
	<sec:csrfInput/>
</form>