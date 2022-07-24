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
		<main>
			<div class="container-fluid px-4">
				<h1 class="mt-4">Category Product Form</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="index.html">Manage
							Category</a></li>
					<li class="breadcrumb-item active">Category</li>
				</ol>
				<div class="card mb-4">
					<div class="card-body">
						<c:if test="${not empty alertmessage}">
							<div class="alert alert-${typealert }">${alertmessage }</div>
						</c:if>
						<form:form modelAttribute="categoryForm" enctype="multipart/form-data" method="POST" action="addcategory"> 
							<div class="row">
								<div class="col">
									<form:input path="cateprodCode" type="text" class="form-control" placeholder="Category Code" />
								</div>
								<div class="col">
									<form:input path="cateprodName" type="text" class="form-control" placeholder="Category Name" />
								</div>
								<!-- Not show on screen start -->
								<div class="col">
									<form:hidden path="createdBy" 
										value="${pageContext.request.userPrincipal.name}" />
								</div>
								<!-- Not show on screen end -->
								<div class="col">
									<button type="submit" class="btn btn-primary">Create
										Category</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</main>
		<!--  Footer -->
		<jsp:include page="footer_adminwebsite.jsp" />
		<!-- End Footer -->
	</div>
</div>
<!-- Body End-->
</html>