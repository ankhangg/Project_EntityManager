<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Admin Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css"
	rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/decorator_adminwebsite/css/styles.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	crossorigin="anonymous"></script>
<script src="<c:url value='/decorator_adminwebsite/js/scripts.js' />"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
	crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<script
	src="<c:url value='/decorator_adminwebsite/js/datatables-simple-demo.js' />"></script>
</head>
<!-- Header-->
<jsp:include page="header_adminwebsite.jsp" />
<!-- End Header-->
<!-- Body Start-->
<div id="layoutSidenav">
	<!-- LayoutSide Start -->
	<jsp:include page="layoutside_adminwebsite.jsp" />
	<!-- LayoutSide End -->
	<div id="layoutSidenav_content">
		<!--  Footer -->
		<jsp:include page="footer_adminwebsite.jsp" />
		<!-- End Footer -->
	</div>
</div>
<!-- Body End-->
</html>