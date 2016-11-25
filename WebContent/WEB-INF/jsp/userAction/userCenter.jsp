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
			<%@ include file="/WEB-INF/jsp/public/leftMenu.jspf"%>


			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h2 class="sub-header">个人信息</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<tbody>
							<tr>
								<td><b>用户名</b></td>
								<td>${user.username }</td>
							</tr>
							<tr>
								<td><b>姓名</b></td>
								<td>${user.name }</td>
							</tr>
							<tr>
								<td><b>性别</b></td>
								<td>${user.gender}</td>
							</tr>
							<tr>
								<td><b>出生日期</b></td>
								<td><s:date name="#session.user.birthday"
										format="yyyy年MM月dd日" /></td>
							</tr>
							<tr>
								<td><b>班级</b></td>
								<td>${user.classes }</td>
							</tr>
							<tr>
								<td><b>手机号码</b></td>
								<td>${user.telephone }</td>
							</tr>
							<tr>
								<td><b>电子邮箱</b></td>
								<td>${user.email }</td>
							</tr>
							<tr>
								<td><b>QQ</b></td>
								<td>${user.qq }</td>
							</tr>
							<tr>
								<td><b>积分</b></td>
								<td>${user.score }</td>
							</tr>
							<tr>
								<td><b>用户级别</b></td>
								<td>${user.role.name}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>

</body>
</html>
