<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <!-- <link rel="icon" href="../../favicon.ico"> -->

    <title>首页</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath %>/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    
    <link href="<%=contextPath %>/css/bootstrap/bootstrap-table.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=contextPath %>/css/dashboard.css" rel="stylesheet">

  </head>

  <body onload="initSearchBoardAndTopic();">

    <jsp:include page="commonNavigationBar.jsp"></jsp:include>

	<!-- 新建板块modal 开始-->
	<div class="modal fade" id="addBoardModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新建版块</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="boardName" class="sr-only">boardName</label> <input
								type="text" class="form-control" id="addboardName" name="boardName"
								placeholder="版块名称" />
						</div>
						<div class="form-group">
							<label for="boardDesc" class="sr-only">boardDesc</label>
							<textarea rows="5" class="form-control" id="addboardDesc"
								name="boardDesc" placeholder="版块描述"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary"
						onclick="addBoardInfo();">提交</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 新建板块modal 结束-->
	
	<!-- 修改板块modal 开始 -->
	<div class="modal fade" id="updateBoardModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改版块</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group sr-only">
							<label for="boardId" class="sr-only">boardId</label> <input
								type="text" class="form-control" id="boardId" name="id" />
						</div>
						<div class="form-group">
							<label for="boardName" class="sr-only">boardName</label> <input
								type="text" class="form-control" id="updateboardName" name="boardName" />
						</div>
						<div class="form-group">
							<label for="boardDesc" class="sr-only">boardDesc</label>
							<textarea rows="5" class="form-control" id="updateboardDesc"
								name="boardDesc"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button id="submit_button" class="btn btn-primary" type="button"
						onclick="updateBoardInfo();">更改板块信息</button>
					<button id="delete_button" class="btn btn-default" type="button"
						onclick="deleteBoard();">删除板块</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 修改板块modal 结束 -->

	<div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar" id="boardList">
            
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		  <br />
		  <div class="text-right">
		  <shiro:authenticated>
		  	<button class="btn btn-primary" data-toggle="modal" data-target="#addBoardModal">新建版块</button>
		  	<button class="btn btn-primary" id="updateBoardPage" data-toggle="modal" data-target="#updateBoardModal">管理版块</button>
          	<a href="<%=contextPath %>/board/addTopicPage" id="posting" class="btn btn-primary">发表主题帖</a>
		  </shiro:authenticated>
		  </div>
		  <br />
          <div class="table-responsive" id="bootstrapTableStation">
            <table class="table table-striped" id="table-pagination">
              <thead>
                <tr>
                  <th class="col-sm-7">标题</th>
                  <th class="col-sm-2">发帖人</th>
                </tr>
              </thead>
              <tbody>
              	
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

	<script type="text/javascript">
		var contextPath = '<%=contextPath %>';
	</script>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=contextPath %>/js/jquery/jquery.min.js"></script>
    <script src="<%=contextPath %>/js/bootstrap/bootstrap.min.js"></script>
    <script src="<%=contextPath %>/js/bootstrap/bootstrap-table.js"></script>
    <script src="<%=contextPath %>/js/bootstrap/bootstrap-table-zh-CN.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <%-- <script src="<%=contextPath %>/assets/js/vendor/holder.min.js"></script> --%>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <%-- <script src="<%=contextPath %>/assets/js/ie10-viewport-bug-workaround.js"></script> --%>
    
    <script src="<%=contextPath %>/my/my.js"></script>
  </body>
</html>
