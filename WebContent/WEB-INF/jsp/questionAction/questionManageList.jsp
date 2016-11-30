<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="题库">
<meta name="author" content="黄跃然 huangyueran">
<title>练习系统</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
<link href="<%=basePath%>css/dashboard.css" rel="stylesheet" />
<!-- 禁用响应式布局 -->
</head>
<body>

	<!--   	导航条开始    -->
	<nav class="nav navbar-inverse navbar-fixed-top">

		<div class="container">

			<div class="navbar-header">
				<!-- 	描述：logo -->
				<a class="navbar-brand"> <img src="img/ic_launcher.jpg"
					style="width: 75px;" />
				</a>
			</div>

			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-btn center-block text-center">
					<li class="active navbar-btn"><a href="homeAction_toIndex">首页<span
							class="sr-only"></span></a></li>
					<li id="nav2" class="navbar-btn"><a href="homeAction_toHelpUI">帮助文档<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="homeAction_toSystemNoticeUI">系统公告<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a
						href="homeAction_toCompetitionSystemUI">进入系统<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a
						href="testAnswerAction_toAnswerListUI">评测状态<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="homeAction_toExchangeCentreUI">交流中心<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="homeAction_toContactInfoUI">联系方式<span
							class="sr-only"></span></a></li>
				</ul>

				<%@ include file="/WEB-INF/jsp/public/userMenu.jspf"%>
			</div>

		</div>
	</nav>
	<!--   	导航条结束    -->

	<!-- 中间内容开始 -->
	<div class="container-fluid">
		<div class="row">
			<!-- 侧边栏 -->
			<div class="col-sm-3 col-md-2 sidebar collapse"
	style="position: absolute; top: 70px;">

	<ul class="nav nav-sidebar">
		<li class=""><a href="userAction_toUserCenterUI">个人信息</a></li>
		<li><s:a
				action="userAction_toUpdateUserUI">用户信息修改</s:a></li>
		<li><s:a
				action="userAction_toUpdateUserPasswordUI">修改密码</s:a></li>
		<li><a href="userAction_toUserAlertsUI">我的提醒&nbsp;<s:if
					test='#session.userReplysCount != 0'>
					<span class="badge" style="background-color: #FF5400;">${userReplysCount}</span>
				</s:if>
		</a></li>
		<li><a href="userAction_toUserTopicListUI">我的主题</a></li>
		<li><a href="userAction_toUserFavoriteUI">我的收藏</a></li>
		<li><a href="visitorAction_toUserVisitorUI">访客记录&nbsp;<s:if
					test='#session.userVisitorsCount != 0'>
					<span class="badge">${userVisitorsCount }</span>
				</s:if>
		</a></li>

		<s:if test="#session.user.role.name=='超级管理员'">
			<li class="alert-warning"><a href="userAction_toCreateUserUI">新建用户</a></li>
		</s:if>

		<s:if
			test="#session.user.role.name=='超级管理员' || #session.user.role.name=='管理员' ">
			<li class="alert-warning"><a
				href="userAction_toUserManageListUI">用户管理</a></li>
			<li class="active alert-warning"><a
				href="questionSetAction_toQuestionSetManageListUI">试题管理</a></li>
		</s:if>
	</ul>
</div>

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">试题管理</h2>
				<div class="table-responsive">
					<div class="container">
						<s:a action="questionAction_toCreateQuestionUI?scopeId=%{questionSetId}"  
							cssClass="btn btn-success">创建试题</s:a>
					</div>
					<!------- 搜索框 ------->
					<s:form id="pageForm"
						action="questionAction_toQuestionManageListUI?questionSetId=%{questionSetId}"
						cssClass="navbar-form navbar-left" role="search" method="post"> 					
						<div class="input-group" style="width: 50%"> 
							<s:textfield type="text" name="titleSearch"
								cssClass="form-control input-md" placeholder="请输入试题名称" />     
							<span onclick="javascript: gotoPage(1)" class="input-group-addon btn btn-primary">搜索</span>  
						</div> 
					</s:form>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>试题号</th>
								<th>试题名称</th>
								<th>试题说明</th>
								<th>语言</th>
								<th>试题类型</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody>

							<!-- 求试题集中试题个数 -->
							<s:set var="count" value="0" />
							<s:iterator value="recordList">
								<tr>
									<td>SF-${id}</td>
									<td><s:a
											action="questionAction_toQuestionShowUI?questionSetId=%{questionSetId}&id=%{id}"
											target="_Blank">${title}</s:a></td>
									<td>${description}</td>
									<td>${language}</td>
									<td>${scope.title}</td>
									<td><s:date name="createTime" format="yyyy年MM月dd日" /></td>
									<td><s:a
											action="questionAction_toUpdateQuestionUI?id=%{id}">修改</s:a></td>
									<td><s:a action="questionAction_deleteQuestion?id=%{id}"
											onClick="return confirm('确定要删除本试题吗？')">删除</s:a></td> 
									<td><s:a
											action="questionAction_toQuestionShowUI?questionSetId=%{questionSetId}&id=%{id}"
											target="_Blank">进入</s:a></td>
								</tr>
								<s:set var="count" value="scope.questionCount" />
							</s:iterator>

						</tbody>
					</table>
					<tr>
						<td>试题总数：<s:property value="#count" /></td>
					</tr>

					<!-- 分页开始 -->
					<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
					<!-- 分页结束 -->

				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>
