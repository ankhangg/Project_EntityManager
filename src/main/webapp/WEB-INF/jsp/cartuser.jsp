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
			<table class="table">
				<thead>
					<tr>
						<th>Select</th>
						<th>Product Name</th>
						<th>Price</th>
						<th>Number</th>
						<th>Sum</th>
						<th>Function</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listcartuser }" var="l">
						<tr>
							<td><input class="form-check-input" type="checkbox"></td>
							<td scope="row">${l.productCartName }</td>
							<td>${l.productCartPrice }</td>
							 <td>${l.productCartNumber }</td> 
							<td>${l.productCartSum }</td>
							<td>
		<a href="${pageContext.request.contextPath}/editcart?idcart=${l.productCartId}">Edit</a>
        <a href="${pageContext.request.contextPath}/deletecart?idcart=${l.productCartId}">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<label for="validationDefault01">Sum Cart: ${sumcart }</label>
	</section>
	<!-- Body End-->
	<!--  Footer -->
	<jsp:include page="footer_homewebsite.jsp" />
	<!-- End Footer -->
</body>
</html>