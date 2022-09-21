<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set  var="cPath" value="<%=request.getContextPath() %>"/>
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
    <!--    Stylesheets-->
    <!-- ===============================================-->
    <link href="${cPath}/resources/ex/assets/css/theme.css" rel="stylesheet" />
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
   color:#0138B7;
   font-size: 50px;
/*    font-weight: bold; */
}


.main-area{
   margin-top: 3%;
   padding: 20px;
   border-color: red;
   border: 3px;
}
         
         #left-menu{
            width: 20%;
            float: left;
            height: 100%;

         }
         
         #left-menu-list{
            list-style: none;
            color: black;
            font-size: 20px;
            font-weight: bold;
         }

         
         #contents{
         float: left;
         width: 70%;
            
         }
         
         #details{
            margin-top: 10%;
            margin-bottom: 5%;
            height: auto;
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

</style>
<body>
        <!-- Begin page -->
        <div id="body">
            <header>
                <div class="navbar-header">
                    <div>
                       <div class="logoName-sector">
                          <a id="logoName" href="#">h</a>
                       </div>
                    </div>
                    
                    <div class="d-flex">
<!--                   로그인/로그아웃아이콘 -->
                        <div class="dropdown d-none d-lg-inline-block ms-1">
                            <button type="button" class="btn header-item noti-icon waves-effect" style="padding: 20px;">
                                <i class="dripicons-brightness-medium"></i>
                            </button>
                        </div>
                        <div class="dropdown d-inline-block">
                            <button type="button" class="btn header-item noti-icon right-bar-toggle waves-effect" style="padding: 20px;">
                                <i class="fas fa-lock"></i>
                            </button>
                        </div>
                        
<!--                         알림 -->
                        <div class="dropdown d-inline-block" >
                            <button type="button" class="btn header-item noti-icon waves-effect" id="page-header-notifications-dropdown"
                                  data-bs-toggle="dropdown" aria-expanded="false" style="padding: 20px;">
                                <i class="far fa-bell"></i>
<!--                                 <span class="noti-dot"></span> -->
                            </button>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end p-0"
                                aria-labelledby="page-header-notifications-dropdown">
                                <div class="p-3">
                                    <div class="row align-items-center">
                                        <div class="col">
                                            <h6 class="m-0"> Notifications </h6>
                                        </div>
                                        <div class="col-auto">
                                            <a href="${cPath}/news" class="small"> View All</a>
                                        </div>
                                    </div>
                                </div>
                                <div data-simplebar style="max-height: 230px;">
                                    <a href="" class="text-reset notification-item">
                                        <div class="d-flex align-items-center">
                                            <div class="avatar-xs me-3 mt-1">
                                                <span class="avatar-title bg-primary rounded-circle font-size-16">
                                                    <i class="ri-shopping-cart-line"></i>
                                                </span>
                                                
                                                <div class="noti-top-icon">
                                                    <i class="mdi mdi-heart text-white bg-danger"></i>
                                                </div>
                                            </div>
                                            
                                            <div class="flex-grow-1 text-truncate">
                                                <h6 class="mt-0 mb-1">Order placed <span
                                                        class="mb-1 text-muted fw-normal">If several languages the
                                                        grammar</span></h6>
                                                <p class="mb-0 font-size-12"><i class="mdi mdi-clock-outline"></i> 3 min ago</p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="" class="text-reset notification-item">
                                        <div class="d-flex align-items-center">
                                            <div class="mt-1">
                                               
                                                <div class="noti-top-icon">
                                                    <i class="mdi mdi-circle text-white bg-success"></i>
                                                </div>
                                            </div>
                                            <div class="flex-grow-1 text-truncate">
                                                <h6 class="mt-0 mb-1">James Lemire <span
                                                        class="mb-1 text-muted fw-normal">It will seem like simplified
                                                        English.</span></h6>
                                                <p class="mb-0 font-size-12"><i class="mdi mdi-clock-outline"></i> 1 hours ago</p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="" class="text-reset notification-item">
                                        <div class="d-flex align-items-center">
                                            <div class="avatar-xs me-3 mt-1">
                                                <span class="avatar-title bg-success rounded-circle font-size-16">
                                                    <i class="ri-checkbox-circle-line"></i>
                                                </span>
                                            </div>
                                            <div class="flex-grow-1 text-truncate">
                                                <h6 class="mt-0 mb-1">Your item is shipped <span
                                                        class="mb-1 text-muted fw-normal">If several languages coalesce the
                                                        grammar.</span></h6>
                                                <p class="mb-0 font-size-12"><i class="mdi mdi-clock-outline"></i> 3 min ago</p>
                                            </div>
                                        </div>
                                    </a>

                                    <a href="" class="text-reset notification-item">
                                        <div class="d-flex align-items-center">
                                            <div class="mt-1">
                                                <img src="${cPath}/resource/assets/images/users/avatar-4.jpg" class="me-3 rounded-circle avatar-xs"
                                                    alt="user-pic">
                                                <div class="noti-top-icon">
                                                    <i class="mdi mdi-heart text-white bg-danger"></i>
                                                </div>
                                            </div>
                                            <div class="flex-grow-1 text-truncate">
                                                <h6 class="mt-0 mb-1">Salena Layfield <span
                                                        class="mb-1 text-muted fw-normal">As a skeptical Cambridge friend
                                                        of mine occidental.</span></h6>
                                                <p class="mb-0 font-size-12"><i class="mdi mdi-clock-outline"></i> 1 hours ago</p>
                                            </div>
                                        </div>
                                    </a>

                                    <a href="" class="text-reset notification-item">
                                        <div class="d-flex align-items-center">
                                            <img src="assets/images/users/avatar-6.jpg" class="me-3 rounded-circle avatar-xs"
                                                alt="user-pic">
                                            <div class="flex-grow-1 text-truncate">
                                                <h6 class="mt-0 mb-1">Jonathon Joseph <span
                                                        class="mb-1 text-muted fw-normal">Friend of mine occidental.</span>
                                                </h6>
                                                <p class="mb-0 font-size-12"><i class="mdi mdi-clock-outline"></i> 5 min ago</p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                                <div class="p-2 border-top">
                                    <div class="d-grid">
                                        <a class="btn btn-sm btn-link font-size-14 text-center" href="javascript:void(0)">
                                            <i class="mdi mdi-arrow-right-circle me-1"></i> View More..
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="dropdown d-inline-block user-dropdown">
                            
                             <button type="button" class="btn header-item noti-icon waves-effect" id="page-header-search-dropdown"
                                data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="padding: 20px;">
                                <i class=" fas fa-user"></i>
                            </button>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-end">
                                <div class="p-3">
                                    <div class="row align-items-center">
                                        <div class="col">
                                            <h6 class="m-0">회원이름 </h6>
                                        </div>
                                        <div class="col-auto">
                                            <a href="#!" class="small"> Available</a>
                                        </div>
                                    </div>
                                </div>
                                <div data-simplebar">
                                <!-- item-->
                                <a href="" class="text-reset notification-item">
                                    <div class="d-flex align-items-center">
                                        <div class="avatar-xs me-3 mt-1">
                                            <span class="avatar-title bg-soft-primary rounded-circle font-size-16">
                                                <i class="ri-user-line text-primary font-size-16"></i> 
                                            </span>
                                        </div>
                                        <div class="flex-grow-1 text-truncate">
                                            <h6 class="mb-1">MY PAGE</h6>
                                      
                                        </div>
                                    </div>
                                </a>
                                <!-- item-->
                                <a href="" class="text-reset notification-item">
                                    <div class="d-flex align-items-center">
                                        <div class="avatar-xs me-3 mt-1">
                                            <span class="avatar-title bg-soft-warning rounded-circle font-size-16">
                                                <i class="ri-wallet-2-line text-warning font-size-16"></i> 
                                            </span>
                                        </div>
                                        
                                        
                                        <div class="flex-grow-1 text-truncate">
                                            <h6 class="mb-1">MY BLOG</h6>
                                      
                                        </div>
                                    </div>
                                </a>
                                <!-- item-->
                                <a href="" class="text-reset notification-item">
                                    <div class="d-flex align-items-center">
                                        <div class="avatar-xs me-3 mt-1">
                                            <span class="avatar-title bg-soft-secondary rounded-circle font-size-16">
                                                <i class="ri-settings-2-line text-secondary"></i> 
                                            </span>
                                        </div>
                                        <div class="flex-grow-1 text-truncate">
                                            <h6 class="mb-1">MY PROJECT <span class="badge bg-success float-end mt-1">11</span></h6>
                                         
                                        </div>
                                    </div>
                                </a>
                                <!-- item-->
                                <a href="" class="text-reset notification-item">
                                    <div class="d-flex align-items-center">
                                        <div class="avatar-xs me-3 mt-1">
                                            <span class="avatar-title bg-soft-primary rounded-circle font-size-16">
                                                <i class="ri-lock-unlock-line text-primary"></i> 
                                            </span>
                                        </div>
                                        <div class="flex-grow-1 text-truncate">
                                            <h6 class="mb-1">MY EXPERT </h6>
<!--                                             <p class="mb-0 font-size-12">Control your privacy parameters..</p> -->
                                        </div>
                                    </div>
                                </a>
                            </div>
                             <!-- 로그아웃-->
<!--                              <div class="pt-2 border-top"> -->
<!--                                 <div class="d-grid"> -->
<!--                                     <a class="btn btn-sm btn-link font-size-14 text-center" href="javascript:void(0)"> -->
<!--                                         <i class="ri-shut-down-line align-middle me-1"></i> Logout -->
<!--                                     </a> -->
<!--                                 </div> -->
                            </div>
                            </div>
                        </div>


            
                    </div>
                </div>
            </header>
            

            
            </div>
            <div>
                  <header class="navbar navbar-expand-lg navbar-light" data-navbar-on-scroll="data-navbar-on-scroll">

            <ul class="navbar-nav mx-auto pt-2 pt-lg-0 font-base">
              <li class="nav-item px-2" data-anchor="data-anchor"><a class="nav-link fw-medium active" aria-current="page" href="index.html">Home</a></li>
              <li class="nav-item px-2" data-anchor="data-anchor"><a class="nav-link" href="#">NOTICE</a></li>
              <li class="nav-item px-2" data-anchor="data-anchor"><a class="nav-link" href="#">COMMNUNITY </a></li>
              <li class="nav-item px-2" data-anchor="data-anchor"><a class="nav-link" href="#">BLOG </a></li>
              <li class="nav-item px-2" data-anchor="data-anchor"><a class="nav-link" href="#country">EXPERT </a></li>
            </ul>				
          </div>
        </div>
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
          <div id="left-menu">

               <h3>notice</h3>
               <ul id="left-menu-list">
                  <li><a href="#">notice</a></li>
                  <li><a href="#">FQA</a></li>
               </ul>

           </div>
             <div id="contents">
         <h1>내용</h1>
            <h1>내용</h1>
            <h1>내용</h1>
            <h1>내용</h1>
            <h1>내용</h1><h1>내용</h1>
            <h1>내용</h1>
            </div>
       
      </div>   
      </div>
         
         
  <section id="footer" class="py-0 bg-primary">
          <div class="container">
          <div class="row justify-content-between pb-2 pt-8">
            <div class="col-12 col-lg-auto mb-5 mb-lg-0"><a class="d-flex align-items-center fw-semi-bold fs-3" href="#"> <img class="me-3" src="assets/img/gallery/footer-logo.png" alt="..." /></a>
              <p class="my-3 text-100 fw-light">Concord Royal Court (3rd floor)<br />Dhanmondi, Dhaka 1209, Bangladesh.</p>
            </div>
            <div class="col-auto mb-3">
              <ul class="list-unstyled mb-md-4 mb-lg-0">
                <li class="mb-3"><a class="text-100 fw-light text-decoration-none" href="#!">About Us</a></li>
                <li class="mb-3"><a class="text-100 fw-light text-decoration-none" href="#!">Responsibilities</a></li>
                <li class="mb-3"><a class="text-100 fw-light text-decoration-none" href="#!">Our Services</a></li>
                <li class="mb-3"><a class="text-100 fw-light text-decoration-none" href="#!">Contact</a></li>
              </ul>
            </div>
            <div class="col-auto mb-3">
              <ul class="list-unstyled mb-md-4 mb-lg-0">
                <li class="mb-3"><a class="text-100 fw-light text-decoration-none" href="#!">Disclaimer</a></li>
                <li class="mb-3"><a class="text-100 fw-light text-decoration-none" href="#!">Disclaimer</a></li>
                <li class="mb-3"><a class="text-100 fw-light text-decoration-none" href="#!">Privacy Policy</a></li>
                <li class="mb-3"><a class="text-100 fw-light text-decoration-none" href="#!">Terms of Service</a></li>
              </ul>
            </div>
            <div class="col-auto mb-4 d-flex align-items-end">
              <ul class="list-unstyled list-inline mb-0">
                <li class="list-inline-item me-3"><a class="text-decoration-none" href="#!">
                    <svg class="bi bi-facebook" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="#ffffff" viewBox="0 0 16 16">
                      <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z"> </path>
                    </svg></a></li>
                <li class="list-inline-item me-3"><a href="#!">
                    <svg class="bi bi-twitter" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="#ffffff" viewBox="0 0 16 16">
                      <path d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"></path>
                    </svg></a></li>
                <li class="list-inline-item me-3"><a href="#!">
                    <svg class="bi bi-instagram" xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="#ffffff" viewBox="0 0 16 16">
                      <path d="M8 0C5.829 0 5.556.01 4.703.048 3.85.088 3.269.222 2.76.42a3.917 3.917 0 0 0-1.417.923A3.927 3.927 0 0 0 .42 2.76C.222 3.268.087 3.85.048 4.7.01 5.555 0 5.827 0 8.001c0 2.172.01 2.444.048 3.297.04.852.174 1.433.372 1.942.205.526.478.972.923 1.417.444.445.89.719 1.416.923.51.198 1.09.333 1.942.372C5.555 15.99 5.827 16 8 16s2.444-.01 3.298-.048c.851-.04 1.434-.174 1.943-.372a3.916 3.916 0 0 0 1.416-.923c.445-.445.718-.891.923-1.417.197-.509.332-1.09.372-1.942C15.99 10.445 16 10.173 16 8s-.01-2.445-.048-3.299c-.04-.851-.175-1.433-.372-1.941a3.926 3.926 0 0 0-.923-1.417A3.911 3.911 0 0 0 13.24.42c-.51-.198-1.092-.333-1.943-.372C10.443.01 10.172 0 7.998 0h.003zm-.717 1.442h.718c2.136 0 2.389.007 3.232.046.78.035 1.204.166 1.486.275.373.145.64.319.92.599.28.28.453.546.598.92.11.281.24.705.275 1.485.039.843.047 1.096.047 3.231s-.008 2.389-.047 3.232c-.035.78-.166 1.203-.275 1.485a2.47 2.47 0 0 1-.599.919c-.28.28-.546.453-.92.598-.28.11-.704.24-1.485.276-.843.038-1.096.047-3.232.047s-2.39-.009-3.233-.047c-.78-.036-1.203-.166-1.485-.276a2.478 2.478 0 0 1-.92-.598 2.48 2.48 0 0 1-.6-.92c-.109-.281-.24-.705-.275-1.485-.038-.843-.046-1.096-.046-3.233 0-2.136.008-2.388.046-3.231.036-.78.166-1.204.276-1.486.145-.373.319-.64.599-.92.28-.28.546-.453.92-.598.282-.11.705-.24 1.485-.276.738-.034 1.024-.044 2.515-.045v.002zm4.988 1.328a.96.96 0 1 0 0 1.92.96.96 0 0 0 0-1.92zm-4.27 1.122a4.109 4.109 0 1 0 0 8.217 4.109 4.109 0 0 0 0-8.217zm0 1.441a2.667 2.667 0 1 1 0 5.334 2.667 2.667 0 0 1 0-5.334z"> </path>
                    </svg></a></li>
              </ul>
            </div>
          </div>
          <div class="row">
            <div class="col-auto mb-2">
              <p class="mb-0 fs--1 my-2 text-100">&copy; This template is made with&nbsp;
                <svg class="bi bi-suit-heart-fill" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#2b2b2b" viewBox="0 0 16 16">
                  <path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z"></path>
                </svg>&nbsp;by&nbsp;<a class="text-100" href="https://themewagon.com/" target="_blank">ThemeWagon </a>
              </p>
            </div>
          </div>
        </div><!-- end of .container-->
   </section>














    </main>
    <!-- ===============================================-->
    <!--    End of Main Content-->
    <!-- ===============================================-->




    <!-- ===============================================-->
    <!--    JavaScripts-->
    <!-- ===============================================-->
    <script src="${cPath}/resources/ex/vendors/@popperjs/popper.min.js"></script>
    <script src="${cPath}/resources/ex/vendors/bootstrap/bootstrap.min.js"></script>
    <script src="${cPath}/resources/ex/vendors/is/is.min.js"></script>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=window.scroll"></script>
    <script src="${cPath}/resources/ex/assets/js/theme.js"></script>

    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700;800;900&amp;display=swap" rel="stylesheet">
  </body>
            
</html>