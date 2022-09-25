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
		<form:form enctype="multipart/form-data" method="POST" action="listreceipt"> 
			<table class="table">
				<thead>
					<tr>
						<th>Receipt Id:</th>
						<th>Date:</th>
						<th>Total Sum:</th>
						<th>Function</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listreceipt }" var="l">
						<tr>
							<td scope="row">${l.receiptId }</td>
							<td>${l.createdDate }</td>
							<td>${l.totalSum }</td>
							<td>
		<a href="${pageContext.request.contextPath}/showreceipt?idreceipt=${l.receiptId}">View Receipt</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
					<button type="submit" class="btn btn-primary">Back</button>
		</form:form>
	</section>
	<!-- Body End-->
	<!--  Footer -->
	<jsp:include page="footer_homewebsite.jsp" />
	<!-- End Footer -->
</body>
</html>