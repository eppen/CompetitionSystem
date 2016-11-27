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
		<li class="active"><s:a
				action="userAction_toUpdateUserPasswordUI?id=%{#session.user.id}">修改密码</s:a></li>
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
			<li class="alert-warning"><a
				href="questionSetAction_toQuestionSetManageListUI">试题管理</a></li>
		</s:if>
	</ul>
</div>

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">修改密码</h2>
				<div class="table-responsive">
					<form action="userAction_updateUserPassword"
						class="form-horizontal" role="form" method="post">
						<s:hidden name="id"></s:hidden>
						<table class="table table-striped">
							<tbody>
								<tr>
									<td>请输入原始密码
										<div style="color: red">
											<s:fielderror cssStyle=" list-style:none;">
												<s:param>updatepassworderror</s:param>
											</s:fielderror>
										</div>
									</td>
									<td><input type="password" name="oldPassword"
										class="focus form-control" required="required" /></td> 
								</tr>
								<tr>
									<td>输入新密码</td>
									<td><input type="password" name="password1" id="pw1"
										class="focus form-control " required="required"
										onkeyup="validate()" /></td>
								</tr>
								<tr>
									<td>请重新输入密码</td>
									<td><input type="password" name="password2" id="pw2"
										class="focus form-control " required="required"
										onkeyup="validate()" /><span id="tishi"></td>
								</tr>

							</tbody>
						</table>
						<button class="btn btn-lg btn-primary btn-block" id="submit"
							type="submit">确认修改</button>
					</form>
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->

	<script>
		function validate() {
			var pw1 = document.getElementById("pw1").value;
			var pw2 = document.getElementById("pw2").value;
			if (pw1 == pw2) {
				document.getElementById("tishi").innerHTML = "<font color='green'>两次密码相同</font>";
				document.getElementById("submit").disabled = false;
			} else {
				document.getElementById("tishi").innerHTML = "<font color='red'>两次密码不相同</font>";
				document.getElementById("submit").disabled = true;
			}
		}
	</script>

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>
