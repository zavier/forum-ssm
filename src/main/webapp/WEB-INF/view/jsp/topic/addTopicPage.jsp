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

    <title>发帖</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath %>/css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=contextPath %>/css/signin.css" rel="stylesheet">
    
	<!-- 配置文件 -->
    <script type="text/javascript" charset="utf-8" src="<%=contextPath %>/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" charset="utf-8" src="<%=contextPath %>/ueditor/ueditor.all.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=contextPath %>/ueditor/lang/zh-cn/zh-cn.js"></script>
    
  </head>

  <body>

    <div class="container">

      <form action="<%=contextPath %>/topic/addTopic/${boardId}" method="post" data-toggle="validator" role="form">
        <h2 class="form-signin-heading col-md-4 col-md-offset-5">发帖</h2>
        <div class="form-group has-feedback">
		    <label for="topicTitle" class="sr-only">topicTitle</label>
		    <input type="text" class="form-control" id="topicTitle" name="topicTitle" placeholder="在此输入标题" data-error="标题不能为空"  required></input>
		    <div class="help-block with-errors"></div>
		</div>
		
		<!-- 加载编辑器的内容 -->
        <script id="addTopicContainer" name="topicContent" type="text/plain">
            
        </script>
        
        <button id="submit_button" class="btn btn-lg btn-primary btn-block" type="submit">提交</button>
      </form>
    </div> <!-- /container -->
    
	<script src="<%=contextPath %>/js/jquery/jquery.min.js"></script>
	<script src="<%=contextPath %>/js/bootstrap/bootstrap.min.js"></script>
	<script src="<%=contextPath%>/js/bootstrap/validator.js"></script>
	<script src="<%=contextPath %>/my/my.js"></script>

    <!-- 实例化编辑器 -->
    <script type="text/javascript">
        var ue = UE.getEditor('addTopicContainer');
    </script>
  </body>
</html>
