function waiting(){
	alert("请等待后续完善...");
}

function checkRegisterInfo(){
	var username = $("#inputUserName").val();
	var trim_username = $.trim(username);
	var password = $("#inputPassword").val();
	if(trim_username.length < 6 || trim_username.length > 8){
		alert("账号应6~8位!");
		return false;
	}
	if(password.length < 6 || password.length > 12){
		alert("密码应6~12位!");
		return false;
	}
	return true;
}

function checkIfLogined(){
	var loginButtonText = $("#loginLink").text();
	if(loginButtonText != '登录'){					//已经登录
		var userId = $("#loginedUserId").text();
		$("#loginLink").attr('href', contextPath + '/user/showUserInformation/' + userId);
	}else{											//还未登录
		$("#loginLink").attr('href', contextPath + '/user/login/loginPage');
	}
	
	var registerButtonText = $("#registerLink").text();
	if(registerButtonText == '注册'){
		$("#registerLink").attr('href',contextPath + '/user/registerPage');
	}else if(registerButtonText == '注销'){
		$("#registerLink").attr('href',contextPath + '/user/login/doLogout');
	}
}

function initSearchBoardAndTopic(){
	//查询并列出所有版块及对应主题帖
	var boardUrl = '/board/searchAllBoards';
	var boardReqType = 'GET';
	var boardParams = new Object();
	boardParams.page = 1;
	boardParams.size = 10;
	commonAjax(boardUrl, boardParams, boardReqType, listAllBoardsAndTopic);
	
}

function listAllBoardsAndTopic(data){
	$('#boardList').empty();
	var html = '';
	$.each(data,function(key, value){
		html += '<li><a href="javascript:void(0);"'
			 + ' onclick="changeBoardColor('+ key + ',\'' + value.id +'\')">' 
			 + value.boardName + '</a></li>';
	});
	$('#boardList').append(html);
	$('#boardList li:first').addClass('active');
	
	//对添加主题帖的链接进行初始化
	$("#posting").attr('href', contextPath + '/topic/addTopicPage/' + data[0].id);
	
	//对管理主题帖的链接进行初始化
	$("#updateBoardPage").attr('onclick', 'updateBoardPage("' + data[0].id + '")');
	
	//查询并展示初始版块下的主题帖
	listBoardTopic(data[0].id);
}

function emptyBootstrapTable() {
	$("#bootstrapTableStation").empty();
	$("#bootstrapTableStation").append("<table id='table-pagination'></table>");
}

//查询并展示某版块下的主题帖
function listBoardTopic(boardId){
	emptyBootstrapTable();
	$('#table-pagination').bootstrapTable({
		url: contextPath + '/topic/listBoardTopics/' + boardId, //请求后台的URL（*）
		method: 'GET', //请求方式（*）
		toolbar: '#toolbar', //工具按钮用哪个容器
		striped: true, //是否显示行间隔色
		cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination: true, //是否显示分页（*）
		sortName: 'topicId', //进行排序的列
		sortable: true, //是否启用排序
		sortOrder: "asc", //排序方式
//		queryParams: querypar, //传递参数（*）
		sidePagination: "client", //分页方式：client客户端分页，server服务端分页（*）
		pageNumber:1, //初始化加载第一页，默认第一页
		pageSize: 10, //每页的记录行数（*）
		pageList: [10, 25, 50, 100], //可供选择的每页的行数（*）
		strictSearch: true,
		clickToSelect: true, //是否启用点击选中行
//		height: 460, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId: 'topicId', //每一行的唯一标识，一般为主键列
		cardView: false, //是否显示详细视图
		detailView: false, //是否显示父子表
		columns: [{
			field: 'topicId',
			title: '序号',
			formatter: indexFormatter
		},{
			field: 'topicTitle',
			title: '标题',
			formatter: topicTitleFormatter
		},{
			field: 'userName',
			title: '发帖人',
		},]
	});
	
}

//显示序号列
var indexFormatter = function(value, row, index) {
	return index + 1;
}

// 显示主题帖标题列
var topicTitleFormatter = function(value, row, index) {
	return '<a href="topic/showTopicDetails/' + row.topicId + '">' 
			 + row.topicTitle + '</a>';
}

//单机触发版块的背景颜色变化
function changeBoardColor(key, boardId){
	$('#boardList li').removeClass('active');
	$('#boardList li:eq('+ key + ')').addClass('active');
	
	//根据选择的版块，对添加主题帖的链接进行更改
	$("#posting").attr('href', contextPath + '/topic/addTopicPage/' + boardId);
	
	//根据选择的板块，对管理主题帖的事件进行更改
	$("#updateBoardPage").attr('onclick', 'updateBoardPage("' + boardId + '")');
	
	//根据所选的版块，对展示的主题帖进行更改
	listBoardTopic(boardId);
}

//检查提交的主题帖子信息是否正确
function checkTopicInfo(){
	var topicTitle = $("#topicTitle").val();
	var topicTitle_trim = $.trim(topicTitle);
	var topicContent = $("#topicContent").val();
	var topicContent_trim = $.trim(topicContent);
	
	var topicTitleLen = topicTitle_trim.length;
	if(topicTitleLen > 20){
		alert("标题不能为空,且应小于20个字");
		return false;
	}
	if(topicContent_trim == ''){
		alert("正文内容不能为空");
		return false;
	}
	return true;
}

//检查提交的版块信息是否正确
function checkBoardInfo(){
//	var boardName = $("#boardName").val();
//	var boardDesc = $("#boardDesc").val();
//	var boardName_trim = $.trim(boardName);
//	var boardDesc_trim = $.trim(boardDesc);
//	if(boardName_trim.length > 10 || boardName_trim == ""){
//		alert("名称应在1~10个字之间");
//		return false;
//	}
	return true;
}

//修改板块，查询并显示板块信息
function updateBoardPage(boardId){
	var url = '/board/getBoardInfo';
	var params = {boardId:boardId};
	commonAjax(url, params, 'get', showBoardInfo);
}

//更新板块时，将板块信息显示在更新板块modal
function showBoardInfo(data){
	$("#boardId").val(data.result[0].id);
	$("#updateboardName").val(data.result[0].boardName);
	$("#updateboardDesc").val(data.result[0].boardDesc);
}

//更新板块信息
function updateBoardInfo(){
	if(!checkBoardInfo()){	//检查板块信息是否符合要求
		return false;
	}
	var boardId = $("#boardId").val();
	var boardName = $("#updateboardName").val();
	var boardDesc = $("#updateboardDesc").val();
	var url = '/board/addOrUpdateBoard';
	var params = new Object();
	params.id = boardId;
	params.boardName = boardName;
	params.boardDesc = boardDesc;
	
	commonAjax(url, params, 'POST', showResult);
	// 关闭修改板块模态框
	$("#updateBoardModal").modal('hide');
}

//新增板块信息
function addBoardInfo(){
	if(!checkBoardInfo()){	//检查板块信息是否符合要求
		return false;
	}
	var boardName = $("#addboardName").val();
	var boardDesc = $("#addboardDesc").val();
	var url = '/board/addOrUpdateBoard';
	var params = new Object();
	params.boardName = boardName;
	params.boardDesc = boardDesc;
	
	commonAjax(url, params, 'POST', showResult);
	// 关闭添加板块模态框
	$("#addBoardModal").modal('hide');
	// 清空添加模态框中的内容
	clearAddBoardModal();
}

// 清空添加模态框中的内容
function clearAddBoardModal(){
	$("#addboardName").val("");
	$("#addboardDesc").val("");
}

//进行添加修改板块后的后续处理
function showResult(data){
	var message = data['message'];
	alert(message);
	parent.initSearchBoardAndTopic();
}

//删除板块
function deleteBoard(){
	var boardId = $("#boardId").val();
	var res = confirm('删除板块后导致其下的帖子全被删除，确定？');
	if (res) {
		//进行删除操作
		var url = '/board/deleteBoard';
		var params = new Object();
		params.boardId = boardId;
		commonAjax(url, params, 'GET', showResult);
	}
	// 关闭修改板块模态框
	$("#updateBoardModal").modal('hide');
}

function commonAjax(url,params,reqType,fun){
	$.ajax({
        type: reqType,
        dataType:'json',
        url: contextPath + url,
        data:params,
        async: true,
//        contentType : 'application/json;charset=utf-8',
        success : function(data) {
        	eval(fun(data));
        },
        complete : function(XMLHttpRequest, textStatus) {
        },
        error : function(data) {
        	alert('请求错误');
        }
    });
}