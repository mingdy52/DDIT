<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Project</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta content="Premium Multipurpose Admin & Dashboard Template"
	name="description" />
<meta content="Themesdesign" name="author" />
<!-- App favicon -->
<link rel="shortcut icon"
	href="${cPath }/resources/assets/images/favicon.ico">

<!-- jquery.vectormap css -->
<link
	href="${cPath }/resources/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.css"
	rel="stylesheet" type="text/css" />

<!-- Bootstrap Css -->
<link href="${cPath }/resources/assets/css/bootstrap.min.css"
	id="bootstrap-style" rel="stylesheet" type="text/css" />
<!-- Icons Css -->
<link href="${cPath }/resources/assets/css/icons.min.css"
	rel="stylesheet" type="text/css" />
<!-- App Css-->
<link href="${cPath }/resources/assets/css/app.min.css" id="app-style"
	rel="stylesheet" type="text/css" />
<style>
.teamName {
	font-size: 32.5px;
	color: white
}

#proContents {
	width: 100%;
}

#projList {
	padding-top: 15px;
	padding-bottom: 6px;
}

#project {
	width: 73%;
	margin-left: 1%;
	float: left;
	height: 900px;
	overflow: scroll;
	
}

#message {
	width: 20%;
	float: right;
	min-height: 800px;
}

.nav-mine {
	font-size: 15px;
	padding: 10px;
	margin-right: 10px;
	color: black;
	font-weight: bold;
}

a:hover {
	color: blue;
}
</style>
</head>

<body data-topbar="dark">

	<!-- Begin page -->
	<div id="layout-wrapper">
		<header id="page-topbar">
			<!--                 projheader -->
			<tiles:insertAttribute name="ProjHeader" />
		</header>
	</div>

	<!-- ========== Left Sidebar Start ========== -->
	<div class="vertical-menu">
		<tiles:insertAttribute name="sidebody" />
	</div>
	<!-- Left Sidebar End -->



	<!-- ============================================================== -->
	<!-- Start right Content here -->
	<!-- ============================================================== -->
	<div class="main-content">
		<div class="page-content">
			<div class="container-fluid">

				<!-- start page title -->
				<div class="row">
					<div class="col-12">
						<div
							class="page-title-box d-flex align-items-center justify-content-between"
							style="padding-bottom: 0; padding-left: 14px;">
							<tiles:insertAttribute name="ProjMenu" />

						</div>
					</div>
				</div>
				<!-- end page title -->

				<div id="proContents"> 
					<div class="row" id="project" style="overflow: scroll;">
						<div class="card">
							<div class="card-body">
								<div class="tab-content">
									<div class="tab-pane fade show active">
										<tiles:insertAttribute name="bodies" />
									</div>
								</div>
							</div>
							<!-- end card-body -->
						</div>
						<!-- end card -->
					</div>
					<!-- end row -->
					<!--                     메세지 -->
					<div class="chat-leftsidebar me-lg-4" id="message">
						<div class="card">
							<div class="p-4">
								<tiles:insertAttribute name="ProjMessage" />
							</div>
						</div>
					</div>
				</div>


			</div>
			<!-- End Page-content -->
		</div>
		<!-- end main content-->
	</div>
	<!-- END layout-wrapper -->



	<!-- Right bar overlay-->
	<div class="rightbar-overlay"></div>

	<!-- JAVASCRIPT -->
	<script src="${cPath }/resources/assets/libs/jquery/jquery.min.js"></script>
	<script
		src="${cPath }/resources/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="${cPath }/resources/assets/libs/metismenu/metisMenu.min.js"></script>
	<script
		src="${cPath }/resources/assets/libs/simplebar/simplebar.min.js"></script>
	<script src="${cPath }/resources/assets/libs/node-waves/waves.min.js"></script>


	<!-- apexcharts -->
	<script
		src="${cPath}/resources/assets/libs/apexcharts/apexcharts.min.js"></script>

	<!-- jquery.vectormap map -->
	<script
		src="${cPath}/resources/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="${cPath}/resources/assets/libs/admin-resources/jquery.vectormap/maps/jquery-jvectormap-us-merc-en.js"></script>

	<script src="${cPath}/resources/assets/js/pages/dashboard.init.js"></script>

	<!-- App js -->
	<script src="${cPath}/resources/assets/js/app.js"></script>
	<!--     제이쿼리 -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</body>

</html>