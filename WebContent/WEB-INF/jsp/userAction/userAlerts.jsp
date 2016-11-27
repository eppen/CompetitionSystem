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
				action="userAction_toUpdateUserUI?id=%{#session.user.id}">用户信息修改</s:a></li>
		<li><s:a
				action="userAction_toUpdateUserPasswordUI?id=%{#session.user.id}">修改密码</s:a></li>
		<li class="active"><a href="userAction_toUserAlertsUI">我的提醒&nbsp;<s:if
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
			<li class="alert-warning"><a
				href="questionSetAction_toQuestionSetManageListUI">试题管理</a></li>
		</s:if>
	</ul>
</div>

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">我的提醒</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>回复我的</th>
								<th></th>
							</tr>
						</thead>
						<tbody>

							<s:iterator value="recordList">
								<tr>
									<td><s:a action="topicAction_toTopicShowUI?id=%{topic.id}"
											target="_blank">${author.name }：回复
											${user.name} :${content }</s:a> &nbsp;&nbsp;<span><s:date
												name="postTime" format="yyyy-MM-dd hh:mm:ss" /></span></td>
									<td><span class="text-muted">回复我的主题：“</span> <s:a
											action="topicAction_toTopicShowUI?id=%{topic.id}"
											target="_blank">${topic.title }</s:a><span class="text-muted">”</span></td>
									<td><s:a action="topicAction_toTopicShowUI?id=%{topic.id}">
											<span class="glyphicon glyphicon-comment"></span>回复</s:a></td>
								</tr>
							</s:iterator>


						</tbody>
					</table>
					<s:form id="pageForm" action="userAction_toUserAlertsUI"
						role="search" method="post">
					</s:form>
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
