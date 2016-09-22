<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String contextPath = request.getContextPath();
	if(contextPath == "/"){
		contextPath = "";
	}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>注册</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath %>/css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=contextPath %>/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <%-- <script src="<%=contextPath %>/assets/js/ie-emulation-modes-warning.js"></script> --%>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <div class="container">

      <form data-toggle="validator" role="form" class="form-signin" action="<%=request.getContextPath() %>/user/register" method="post" >
        <h2 class="form-signin-heading col-md-4 col-md-offset-4">注册</h2>
        <div class="form-group has-feedback">
	        <label for="inputUserName" class="sr-only">userName</label>
	        <input type="text" id="inputUserName" name="userName" class="form-control" data-minlength="6" maxlength="12" placeholder="用户名(大于6位)" data-error="用户名必须为6~12位" required autofocus>
	        <div class="help-block with-errors"></div>
        </div>
		<div class="form-group has-feedback">
	        <label for="inputPassword" class="sr-only">Password</label>
	        <input type="password" id="inputPassword" name="password" class="form-control" data-minlength="6" placeholder="密码(大于6位)" data-error="密码必须不小于6位" required>
	        <div class="help-block with-errors"></div>
	    </div>
        <div class="checkbox">
        </div>
        <button id="submit_button" class="btn btn-lg btn-primary btn-block" type="submit">注册</button>
      </form>
    </div> <!-- /container -->

	<script src="<%=contextPath %>/js/jquery/jquery.min.js"></script>
	<script src="<%=contextPath %>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=contextPath%>/js/bootstrap/validator.js"></script>
	<script src="<%=contextPath %>/my/my.js"></script>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <%-- <script src="<%=contextPath %>/assets/js/ie10-viewport-bug-workaround.js"></script> --%>
  </body>
</html>
