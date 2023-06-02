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
					<li class="breadcrumb-item"><a href="index.html">List
							Category</a></li>
					<li class="breadcrumb-item active">Category</li>
				</ol>
				<div class="card mb-4">
					<div class="card-body">
						<c:if test="${not empty alertmessage}">
							<div class="alert alert-${typealert }">${alertmessage }</div>
						</c:if>
                 <!-- Form Start -->
                   <table class="table">
    <thead>
      <tr>
        <th>Id Category</th>
        <th>Code Category</th>
        <th>Name Category</th>
        <th>Function</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${listCategoryProducts }" var="l">
      <tr>
        <td scope="row">${l.cateprodId }</td>
        <td>${l.cateprodCode }</td>
          <td>${l.cateprodName }</td>
         <td>
          <%-- <a href="${pageContext.request.contextPath}/updatecategory?idcategory=${l.cateprodId}">Edit</a> --%>
         <%-- <a href="${pageContext.request.contextPath}/deletecate?idcategory=${l.cateprodId}">Delete</a> --%> 
          <a href="${pageContext.request.contextPath}/updatecategory/${l.cateprodId}">Edit</a>
          <a href="${pageContext.request.contextPath}/deletecate/${l.cateprodId}">Delete</a> 
         </td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
                 <!-- Form End -->
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