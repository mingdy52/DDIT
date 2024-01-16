
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link
	href="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/skin-win8/ui.fancytree.min.css"
	rel="stylesheet">
<script
	src="//cdn.jsdelivr.net/npm/jquery.fancytree@2.27/dist/jquery.fancytree-all-deps.min.js"></script>
<style>
.blogCateList-cate {
	float: left;
	margin-left: 50px;
	width: 242px;
	height: 200px;

}
.blogCateList-cate-name{
	color: black;
	font-weight: bold;
}
#folder {
	color:#8ca3bd;
	font-size: 210px;
}
#folder:hover{
	color: darkgray;
}

#catebody {
	margin-left: 100px;
}

.groupicon{
	font-size: 30px;
}
.pjNum{
	width: 80px;
	margin-top: 10px;

}
</style>
<sec:authentication property="principal.realMember.memId" var="memId" />
<div id="catebody" style="text-align: center;">
	<c:if test="${not empty projectList }">
		<c:forEach items="${projectList }" var="project">
			<div class="blogCateList-cate">
			  <div style="height: 250px;">
				<a href="${cPath }/project/${project.pjId }/home" class=" ri-folder-3-fill" id="folder"></a>
			  </div>
				<h4 class="blogCateList-cate-name">${project.pjName }</h4>
				<h5>${project.pjStart } ~ ${project.pjEnd }</h5>
				<div class="d-flex flex-wrap justify-content-center"> 
  				  <i class="ri-group-2-fill groupicon"></i>
				  <h4 class="pjNum">${fn:length(project.colleagueList)}/${project.pjPnum }</h4>
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>
<div class="btn-group dropup mt-1 me-1"
	style="position: fixed; left: 45cm; bottom: 40cm; z-index: 1001">
	<button type="button" class="btn btn-secondary dropdown-toggle"
		data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		Dropup <i class="mdi mdi-chevron-up"></i>
	</button>
	<div class="dropdown-menu" style="">
		<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item"
			href="#">Another action</a> <a class="dropdown-item" href="#">Something
			else here</a>
		<div class="dropdown-divider"></div>
		<a class="dropdown-item" href="#">Separated link</a>
	</div>
</div>
