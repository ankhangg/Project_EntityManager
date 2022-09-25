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
		<form:form modelAttribute="receipt" enctype="multipart/form-data" method="POST" action="showreceipt"> 
			<table class="table">
				<thead>
					<tr>
						<th>Product Name</th>
						<th>Price</th>
						<th>Number</th>
						<th>Sum</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${receipt.listProductReceipts }" var="l">
						<tr>
							<td scope="row">${l.productReceiptName }</td>
							<td>${l.productReceiptPrice }</td>
							 <td>${l.productReceiptNumber }</td> 
							<td>${l.productCartSum }</td>
							<td>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			 <h5>----------INFORMATION USER----------</h5><br>
			<label>Full Name: ${receipt.fullName }</label><br>
			<label>Phone Number: ${receipt.phoneNumber }</label><br>
			<label>Address: ${receipt.addRess }</label><br><br>
			 <h5>----------TOTAL CART----------</h5><br>
			 <label>Sum Cart: ${receipt.sumTotal }</label><br>
			 <form:hidden path="userName"/>
			 <button type="submit" class="btn btn-primary">Back</button>
			 </form:form>
	</section>
	<!-- Body End-->
	<!--  Footer -->
	<jsp:include page="footer_homewebsite.jsp" />
	<!-- End Footer -->
</body>
</html>