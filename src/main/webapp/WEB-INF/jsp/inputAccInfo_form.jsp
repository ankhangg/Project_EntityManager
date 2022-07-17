<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file= "taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Update Info Account</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/decorator_createaccount/css/styles.css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Account Information</h3></div>
                                    <div class="card-body">
                                          <c:if test="${not empty alertmessage}"> 
<div class="alert alert-${typealert }">
${alertmessage }
</div>
        </c:if> 
                                         <form:form modelAttribute="accountInfoForm"  enctype="multipart/form-data"  method="POST" action="updateif" >
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <form:input path="firstName" class="form-control" id="inputFirstName" type="text" placeholder="Enter your first name" />
                                                        <label for="inputFirstName">First name</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating">
                                                        <form:input path="lastName" class="form-control" id="inputLastName" type="text" placeholder="Enter your last name" />
                                                        <label for="inputLastName">Last name</label>
                                                    </div>
                                                </div>
                                            </div>
                                             <div class="form-floating mb-3">
                                                <form:input path="addRess" class="form-control" id="inputEmail" type="text" placeholder="Enter your address" />
                                                <label for="inputEmail">Address</label>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <form:input path="email" class="form-control" id="inputPassword" type="email" placeholder="Enter your email" />
                                                        <label for="inputPassword">Email</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <form:input path="phoneNumber" class="form-control" id="inputPasswordConfirm" type="text" placeholder="Enter your phone number" />
                                                        <label for="inputPasswordConfirm">Phone number</label>
                                                    </div>
                                                </div>
                                            </div>
                                             <!-- Not show on screen start -->
                                               <div class="form-floating mb-3">
                                                <form:hidden path="accinfoId" value="${accountInfoForm.accinfoId }"/>
                                            </div>
                                             <!-- Not show on screen end -->
                                            <div class="mt-4 mb-0">
                                                <div class="d-grid">
                                                <input class="btn btn-primary btn-block" type="submit" value="Input Info Account" />
                                                </div>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="${pageContext.request.contextPath}/home">Back</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="<c:url value='/decorator_createaccount/js/scripts.js' />"></script>
    </body>
</html>
