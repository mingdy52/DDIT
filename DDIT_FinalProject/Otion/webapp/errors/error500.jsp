<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set  var="cPath" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html lang="ko" class="os_mac chrome pc version_56_0_2924_87">
 <head>
        
        <meta charset="utf-8" />
        <title>Error 500 </title>
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

    </head>

    <body class="authentication-bg">
        <div class="my-5 pt-sm-5">
            <div class="container">
    
                <div class="row">
                    <div class="col-md-12">
                        <div class="text-center">
                            <div class="error-svg">
                                <svg viewBox="0 -100 999 300">
                                    <symbol id="s-text">
                                        <text text-anchor="middle" x="50%" y="50%">500</text>
                                    </symbol>
    
                                    <g class="g-ants">
                                        <use xlink:href="#s-text" class="text"></use>
                                        <use xlink:href="#s-text" class="text"></use>
                                        <use xlink:href="#s-text" class="text"></use>
                                        <use xlink:href="#s-text" class="text"></use>
                                        <use xlink:href="#s-text" class="text"></use>
                                    </g>
                                </svg>
                            </div>
                            <h2 class="mt-4">Internal Server Error</h2>
                            <p class="text-muted mt-2">It will be as simple as Occidental in fact, it will be Occidental</p>
                            <div class="mt-4 pt-2">
                                 <a class="btn btn-primary btn-lg waves-effect waves-light" href="${cPath}/">Back to
                                    HOME</a>
                            </div>
                        </div>
    
                    </div>
                </div>
            </div>
        </div>

        <!-- JAVASCRIPT -->
        <script src="${cPath}/resources/assets/libs/jquery/jquery.min.js"></script>
        <script src="${cPath}/resources/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="${cPath}/resources/assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="${cPath}/resources/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="${cPath}/resources/assets/libs/node-waves/waves.min.js"></script>
    
        <script src="${cPath}/resources/assets/js/app.js"></script>
    
    </body>
</html>