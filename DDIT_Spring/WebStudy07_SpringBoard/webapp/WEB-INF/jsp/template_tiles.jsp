<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    
    <title><tiles:getAsString name="title" /></title>
	
	<tiles:insertAttribute name="preScript"/>
    
<meta name="theme-color" content="#7952b3">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
      
      .profile{
      	height: 50px;
      	width: 50px;
      }
    </style>

    
    <c:if test="${not empty message }">
    	<script type="text/javascript">
    		alert("${message }");
    	</script>
    	<c:remove var="message" scope="session"/>
    </c:if>
    
  </head>
  <body>
    
	<tiles:insertAttribute name="headerMenu" />

<div class="container-fluid">
  <div class="row">
	
	<tiles:insertAttribute name="leftMenu" />

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
    
    	<tiles:insertAttribute name="body"/>
    	
    </main>
  </div>
</div>


    <tiles:insertAttribute name="postScript"/>

      <script src="https://cdn.jsdelivr.net/npm/feather-icons@4.28.0/dist/feather.min.js" integrity="sha384-uO3SXW5IuS1ZpFPKugNNWqTZRRglnUJK6UAZ/gxOX80nxEkN9NcGZTftn6RzhGWE" crossorigin="anonymous"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js" integrity="sha384-zNy6FEbO50N+Cg5wap8IKA4M/ZnLJgzc6w2NqACZaK0u0FXfOWRRJOnQtpZun8ha" crossorigin="anonymous"></script>
  </body>
</html>
    
    