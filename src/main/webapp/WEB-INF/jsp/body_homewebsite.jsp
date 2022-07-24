<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file= "taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROJECT JPA WEBSITE</title>
    <!-- css -->
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/decorator_homewebsite/css/styles.css"/> 
    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/decorator_homewebsite/assets/favicon.ico"/>
   <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
   <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
   <!-- Core theme JS-->
        <script src="<c:url value='/decorator_homewebsite/js/scripts.js' />"></script>
</head>
<body>  
        <!-- Header-->
           <jsp:include page="header_homewebsite.jsp" />
        <!-- End Header-->
        <!-- Body Start-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Welcome to my website</h1>
                    <p class="lead fw-normal text-white-50 mb-0">This website build by Spring Boot - JPA</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                
                 <c:forEach items="${listProductForm }" var="p">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="${pageContext.request.contextPath}/productImage?id=${p.productId}" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">${p.productName }</h5>
                                    <!-- Product price-->
                                   ${p.productPrice } vnđ
                                  <h5 class="fw-bolder">Amount:</h5>
                                    ${p.productAmount } 
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
                            </div>
                        </div>
                         </div>
                      </c:forEach>
                </div>
                
            </div>
        </section>
         <!-- Body End-->
     <!--  Footer -->
            <jsp:include page="footer_homewebsite.jsp" />
     <!-- End Footer -->
</body>
</html>