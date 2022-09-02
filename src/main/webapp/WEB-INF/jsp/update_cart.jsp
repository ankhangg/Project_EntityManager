<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PROJECT JPA WEBSITE</title>
<style type="text/css">
.error {
	color: red
}
</style>
<!-- css -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/decorator_homewebsite/css/styles.css" />
<!-- Favicon-->
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/decorator_homewebsite/assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Bootstrap core JS-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
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
			<form:form modelAttribute="prodmodel" enctype="multipart/form-data" method="POST" action="editcart"> 
 <label for="colFormLabel" class="col-sm-2 col-form-label">Product Name: ${prodmodel.productCartName}</label>
  <label for="colFormLabel" class="col-sm-2 col-form-label">Product Price: ${prodmodel.productCartPrice}</label>
   <label for="colFormLabel" class="col-sm-2 col-form-label">Amount Stock: ${prodmodel.productCartAmount}</label>
   <form:input path="productCartPrice" type="hidden" class="form-control"/>
    <form:input path="productCartId" type="hidden" class="form-control"/>
      <form:input path="productId" type="hidden" class="form-control"/>
    <form:input path="userNameCart" value="${pageContext.request.userPrincipal.name}" type="hidden" class="form-control"/>
   
  
 <div class="form-group">
    <label for="formGroupExampleInput2">Product number:</label>
     <form:input type="number" class="form-control" path="productCartNumber" id="formGroupExampleInput2"/>
      <form:errors path="productCartNumber" class="error"/>
  </div>
  
  <label for="colFormLabel" class="col-sm-2 col-form-label">Product Sum: ${prodmodel.productCartSum}</label>
  <div class="col">
									<button type="submit" class="btn btn-primary">Update
										Cart</button>
								</div>
						</form:form>	
	</section>
	<!-- Body End-->
	<!--  Footer -->
	<jsp:include page="footer_homewebsite.jsp" />
	<!-- End Footer -->
</body>
</html>