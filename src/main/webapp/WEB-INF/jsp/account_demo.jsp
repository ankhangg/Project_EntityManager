<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@include file= "taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="accountForm"  enctype="multipart/form-data" method="POST" action="account"> 
  <div class="form-group">
    <label for="formGroupExampleInput">Username: </label>
        <form:input path="userName" placeholder="Type Username here"/>  
  </div>
  <div class="form-group">
    <label for="formGroupExampleInput2">Password:</label>
  <form:input path="passWord" placeholder="Type Password here" /> 
  </div>
         <div class="form-group">
    <input type="submit" value="Submit" />
     <input type="reset" value="Clear" />
  </div>
</form:form>
</body>
</html>