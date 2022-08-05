<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<style type="text/css">
  .error{
  color: red
  }
</style>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
	crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest"
	crossorigin="anonymous"></script>
<script
	src="<c:url value='/decorator_adminwebsite/js/datatables-simple-demo.js' />"></script>
	
	<script src="<c:url value='/decorator_adminwebsite/js/scripts.js' />"></script>

     

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
		<!-------------------- Form Product Start --------------------->
		<main>
			<script src="<c:url value='/decorator_adminwebsite/js/scripts.js' />"></script>
			<div class="container-fluid px-4">
				<h1 class="mt-4">Product Product Form</h1>
				<ol class="breadcrumb mb-4">
					<li class="breadcrumb-item"><a href="index.html">Input
							Product</a></li>
					<li class="breadcrumb-item active">Product</li>
				</ol>
				<div class="card mb-4">
					<div class="card-body">
						<c:if test="${not empty alertmessage}">
							<div class="alert alert-${typealert }">${alertmessage }</div>
						</c:if>
						<form:form modelAttribute="productupdate" enctype="multipart/form-data" method="POST" action="updateproduct">
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="inputEmail4">Product Name</label> <form:input type="text" path="productName"
										class="form-control" id="inputEmail4" placeholder="Input Product Name"/>
										<form:errors path="productName" class="error"/>
								</div>
							</div>
							<div class="form-group">
								<div class="form-group">
    <label for="exampleFormControlTextarea1">Product Description</label>
    <form:textarea path="productDescription" class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Input Product Description"></form:textarea>
     <form:errors path="productDescription" class="error"/>
  </div>
							</div>
							<div class="form-row">
								 <div class="form-group col-md-6">
									 <label for="inputCity">Product Price</label> 
								<form:input type="number" data-type="currency" path="productPrice" 
											class="form-control" id="inputZip"
											placeholder="Input Product Price" /> 
								<form:errors path="productPrice" class="error"/>
								</div> 
								<div class="form-group col-md-4">
									<label for="inputState">Category</label> 
									<form:select
										class="custom-select" path="idCategory">
										<option selected value="${idCate}">${nameCate}</option>
										 <c:forEach var="cate" items="${listCategoryProducts}">
										<option  value="${cate.cateprodId }">${cate.cateprodName }</option>
										</c:forEach>
									</form:select>
									<form:errors path="idCategory" class="error"/>
									</div>
									<div class="form-group col-md-2">
										<label for="inputZip">Product Amount</label>
										<form:input type="number" path="productAmount"
											class="form-control" id="inputZip"
											placeholder="Input Product Amount" />
									</div>
									<!-- Input File Start -->
 									<div class="custom-file">
										<h5>Product Image</h5>
										<%-- <form:input type="file" class="custom-file-input" id="customFile" path="thumbNail"/> --%>
										    <td><img  src="${pageContext.request.contextPath}/productImage?id=${productupdate.productId}" width="100"/></td>
										    <form:input type="file" path="fileDataUpdateCanNull"/>
									</div>
									<!-- Input File End -->
								</div>
								<!---- Not Show On Screen Start ---->
								  <div class="col">
                                       <form:hidden path="modifiedBy" value="${pageContext.request.userPrincipal.name}"/>
                                       <%-- <form:hidden path="productId" value="${productId}"/> --%>
                                       <form:hidden path="productId"/>
                                        <form:hidden path="productThumbnail"/>
                                            </div>
								<!---- Not Show On Screen End ---->
								
								<button type="submit" class="btn btn-primary">Input</button>
						</form:form>
					</div>
				</div>
			</div>
		</main>
		<!-------------------- Form Product End --------------------->
		<!--  Footer -->
		<jsp:include page="footer_adminwebsite.jsp" />
		<!-- End Footer -->
	</div>
</div>
<!-- Body End-->
</html>