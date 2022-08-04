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
	 
	 <!-------------------------------- Tu them ------------------------------->
 <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
     <script data-require="bootstrap@*" data-semver="3.1.1" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script> 
     <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" />    
     <link rel="stylesheet" href="${pageContext.request.contextPath}/decorator_deletecate_admin/deletecss.css" class="ace-main-stylesheet" id="main-ace-style" /> 
        <script src="script.js"></script>
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
		<!----------------------------- Delete Start -------------------------------->
 	 <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script data-require="bootstrap@*" data-semver="3.1.1" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="script.js"></script> 
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
                </div>
                <div class="modal-body">
                    <p>You are about to delete <b><i class="title"></i></b> record, this procedure is irreversible.</p>
                    <p>Do you want to proceed?</p>
                </div>
                 <form:form modelAttribute="productdelete" method="POST" enctype="multipart/form-data" action="deleteproduct">
                <div class="modal-footer">
                     <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger btn-ok">Delete</button>
                </div>
                     <div class="form-group">
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput">Id Product: ${productdelete.productId } </label>
     <input type="hidden" class="form-control" id="formGroupExampleInput" name="productdId" value="${productdelete.productId }" >
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Name Product: ${productdelete.productName }</label>
    <input type="hidden" class="form-control" id="formGroupExampleInput" name="productName" value="${productdelete.productName }"/>
  </div>
   <div class="form-group">
    <label for="formGroupExampleInput2">Price Product: ${productdelete.productPrice }</label>
    <input type="hidden" class="form-control" id="formGroupExampleInput" name="productPridce" value="${productdelete.productPrice }" >
  </div>
<!--   Hidden Start -->
     <div class="form-group">
   <%--   <form:input type="hidden" class="form-control" id="formGroupExampleInput" path="categoryProduct" value="${productdelete.categoryProduct }" /> --%>
      <input type="hidden" class="form-control" id="formGroupExampleInput" name="productId" value="${productId}"> 
  </div>
  <!--   Hidden End -->
  <div class="form-group">
    <label for="formGroupExampleInput2">Image Product</label>
  </div>
    <td><img  src="${pageContext.request.contextPath}/productImage?id=${productdelete.productId}" width="100"/></td>
     <div class="form-group">
  </div>
     <div class="form-group">
  </div>
                </form:form>
            </div>
        </div>
    </div>
    <br />
    <button class="btn btn-default" data-record-id="54" data-record-title="${productdelete.productName }" data-toggle="modal" data-target="#confirm-delete">
       Delete Name Item: ${productdelete.productName }
    </button>
    <a href="${pageContext.request.contextPath}/showproductadmin">Back</a>
    <script>
        $('#confirm-delete').on('click', '.btn-ok', function(e) {
            var $modalDiv = $(e.delegateTarget);
            var id = $(this).data('recordId');
            // $.ajax({url: '/api/record/' + id, type: 'DELETE'})
            // $.post('/api/record/' + id).then()
            $modalDiv.addClass('loading');
            setTimeout(function() {
                $modalDiv.modal('hide').removeClass('loading');
            }, 1000)
        });
        $('#confirm-delete').on('show.bs.modal', function(e) {
            var data = $(e.relatedTarget).data();
            $('.title', this).text(data.recordTitle);
            $('.btn-ok', this).data('recordId', data.recordId);
        });
    </script>
	<!----------------------------- Delete End -------------------------------->
		<!--  Footer -->
		<jsp:include page="footer_adminwebsite.jsp" />
		<!-- End Footer -->
	</div>
</div>
<!-- Body End-->
</html>