<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>  
  <meta charset="utf-8" />
        <title>Main</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="assets/images/favicon.ico">
        <!-- Bootstrap Css -->
        <link href="${cPath}/resources/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="${cPath}/resources/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="${cPath}/resources/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />

  <!-- ===============================================-->
    <!--    Document Title-->
    <!-- ===============================================-->
    <title>Executive | Landing, Corporate &amp; Business Templatee</title>


    <!-- ===============================================-->
    <!--    Favicons-->
    <!-- ===============================================-->
    <link rel="apple-touch-icon" sizes="180x180" href="${cPath}/resources/ex/assets/img/favicons/apple-touch-icon.png">
    <link rel="icon" type="image/png" sizes="32x32" href="${cPath}/resources/ex/assets/img/favicons/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="${cPath}/resources/ex/assets/img/favicons/favicon-16x16.png">
    <link rel="shortcut icon" type="image/x-icon" href="${cPath}/resources/ex/assets/img/favicons/favicon.ico">
    <link rel="manifest" href="assets/img/favicons/manifest.json">
    <meta name="msapplication-TileImage" content="assets/img/favicons/mstile-150x150.png">
    <meta name="theme-color" content="#ffffff">


    <!-- ===============================================-->
    <!-- ===============================================-->
    <link href="${cPath}/resources/ex/assets/css/theme.css" rel="stylesheet" />
    	<!--     제이쿼리 -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>
<style>
/*    body { */
/*    background-color: white; */
/* } */

	#top-menu{
	   
	   font-size: 40px;
	   text-align: center;
	   margin-top: 5%;
	}
	
	#menu-name{
	    margin-left: 4%;
	   margin-right: 4%;
	   color: #484646;
	}
	#logoName{
	   padding-top: 10px;
	   padding-left: 40px;
	   color:#717CFF;
	   font-size: 30px;
	   font-weight: bold; 
	}
	
	
	.main-area{
	   margin-top: 3%;
	   padding: 20px;
	   border-color: red;
	   border: 3px;
	}
            
         #contents{
         float: left;
         width: 90%;
         
            
         }
         
         #details{
            margin-top: 10%;
            margin-bottom: 5%;
            height: auto;
            min-height: 500px;
             
         }
         
      #footer{
         width: 100%;
         float: left;
         margin-top: 5%;
         text-align: center;
        
      }
         
         #mylist{
            list-style: none;
            font-size: 20px;
            padding-top: 10px;
            margin-top: 1px;
         }
         
         #kgb{
            background-color: lightgray;
            padding-right: 12px;
     
         }
         
         #contents{
         	margin: auto;
         }

</style>
<body>
<!-- Begin page -->
   <div id="body">
	 <header>
			<tiles:insertAttribute name="OtionHeader"/>		
             
            </header>

	
	<header class="navbar navbar-expand-lg navbar-light" data-navbar-on-scroll="data-navbar-on-scroll">
     		<tiles:insertAttribute name="OtionMenu"/>		

      </header>
      
      <section class="py-0" id="home">
        <div class="bg-holder d-none d-md-block" style="background-image:url(assets/img/gallery/hero.png);background-position:right bottom;background-size:contain;margin-top:5.625rem;">
        </div>
        <!--/.bg-holder-->

        <div class="bg-holder d-block d-md-none" style="background-image:url(assets/img/illustrations/hero-bg.png);background-position:right top;background-size:contain;margin-top:5.625rem;">
        </div>
        <!--/.bg-holder-->
	
	</section>
            

<div class="container">
     <div id="details">

<!--        		내용 -->
             <div id="contents">
      			  <tiles:insertAttribute name="noticelist"/>
      		</div>   
      </div>
</div>
         
<!--      푸터 -->
  <section id="footer" class="py-0 bg-primary">
		<tiles:insertAttribute name="OtionFooter" />
   </section>
    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->


 <tiles:insertAttribute name="OtionScript"/>

</body>
            
</html>