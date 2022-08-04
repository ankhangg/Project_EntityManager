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
      <!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${pageContext.request.contextPath}/productImage?id=${detailProduct.productId}" alt="..." /></div>
                    <div class="col-md-6">
                        <div class="small mb-1"></div>
                        <h1 class="display-5 fw-bolder">${detailProduct.productName }</h1>
                        <div class="fs-5 mb-5">
                            <%-- <span class="text-decoration-line-through">${detailProduct.productPrice } vnđ</span> --%>
                             <span>Price: ${detailProduct.productPrice } vnđ</span>
                             <br>
                             <span>Amount: ${detailProduct.productAmount }</span>
                        </div>
                        <p class="lead">${detailProduct.productDescription }</p>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Related items section-->
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
         <!-- Body End-->
     <!--  Footer -->
            <jsp:include page="footer_homewebsite.jsp" />
     <!-- End Footer -->
</body>
</html>