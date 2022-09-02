<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file= "taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROJECT JPA WEBSITE</title>
<style type="text/css">
  .error{
  color: red
  }
</style>
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
      <!-- Product section-->
        <section class="py-5">
                            <c:if test="${not empty alertmessage}">
							<div class="alert alert-${typealert }">${alertmessage }</div>
						</c:if>
                    <form:form modelAttribute="productcart" enctype="multipart/form-data" method="POST" action="addcart"> 
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${pageContext.request.contextPath}/productImage?id=${productcart.productId}"/></div>
                    <div class="col-md-6">

                        <div class="small mb-1"></div>
                        <h1 class="display-5 fw-bolder">${productcart.productCartName }</h1>
                         <form:input path="productCartName" type="hidden" class="form-control"/>
                        <div class="fs-5 mb-5">
                             <span>Price: ${productcart.productCartPrice } vnđ</span>
                             <form:input path="productCartPrice" type="hidden" class="form-control"/>
                             <br>
                             <span>Amount: ${productcart.productCartAmount }</span>
                              <form:input path="productCartAmount" type="hidden" class="form-control"/>
                              <br>
                             <span>Category: ${nameCate }</span>
                        </div>
                        <p class="lead">${productcart.productCartDescription }</p>
                        <!-- Not show on screen start -->
								<div class="col">
									<form:hidden path="userNameCart" 
										value="${pageContext.request.userPrincipal.name}" />
								</div> 
							    <div class="col">
                                     <form:input path="productId" type="hidden" class="form-control"/>
								</div> 
								<!-- Not show on screen end -->
                        <div class="d-flex">
                            <form:input class="form-control text-center me-3" path="productCartNumber" type="number" value="1" style="max-width: 3rem" />
                            <form:errors path="productCartNumber" class="error"/>
                            <button class="btn btn-outline-dark flex-shrink-0" type="submit">
                                <i class="bi-cart-fill me-1"></i>
                                Add To Cart
                            </button>
                             </div>
                          
                        </div>
                    </div>
            </div>
             </form:form> 
        </section>
        <!-- Related items section Start-->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Related products</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">Fancy Product</h5>
                                    <!-- Product price-->
                                    $40.00 - $80.00
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">View options</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
         <!-- Related items section End-->
         <!-- Body End-->
     <!--  Footer -->
            <jsp:include page="footer_homewebsite.jsp" />
     <!-- End Footer -->
</body>
</html>