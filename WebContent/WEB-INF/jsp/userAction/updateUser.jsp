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
<link href="<%=basePath%>css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<!-- 表单验证 -->
<%@ include file="/WEB-INF/jsp/public/formvalidate.jspf"%>
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
		<li class="active"><s:a
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
			<li class="alert-warning"><a
				href="questionSetAction_toQuestionSetManageListUI">试题管理</a></li>
		</s:if>
	</ul>
</div>

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">用户信息修改</h2>
				<div class="table-responsive">
					<form action="userAction_updateUser" class="form-horizontal"
						role="form" method="post">
						<s:hidden name="id"></s:hidden>
						<table class="table table-striped">
							<tbody>
								<tr>
									<td>用户名</td>
									<td>${username }</td>
								</tr>
								<tr>
									<td>姓名</td>
									<td><s:textfield type="text"
											cssClass="focus form-control required" name="name"></s:textfield></td>
								</tr>
								<tr>
									<td>性别</td>
									<td><s:radio list="%{#{'男':'男','女':'女'}}" name="gender"
											cssClass="required"></s:radio></td>
								</tr>
								<tr>
									<td>出生日期</td>
									<td>
										<div class="input-group date form_datetime col-md-5"
											data-date="1979-09-16" data-date-format="yyyy-MM-dd"
											data-link-field="dtp_input1">
											<s:textfield cssClass="form-control date" name="birthdayStr"/>  
											<span class="input-group-addon"><span
												class="glyphicon glyphicon-remove"></span></span> <span
												class="input-group-addon"><span
												class="glyphicon glyphicon-th"></span></span>
										</div> <br />
									</td>
								</tr>
								<tr>
									<td>班级</td>
									<td><s:textfield type="text" cssClass="focus form-control"
											name="classes"></s:textfield></td>
								</tr>
								<tr>
									<td>手机号码</td>
									<td><s:textfield type="text"
											cssClass="form-control number" name="telephone"></s:textfield></td>
								</tr>
								<tr>
									<td>电子邮箱</td>
									<td><s:textfield type="text" cssClass="form-control email"
											name="email"></s:textfield></td>
								</tr>
								<tr>
									<td>QQ</td>
									<td><s:textfield type="text"
											cssClass="form-control number" name="qq"></s:textfield></td>
								</tr>
							</tbody>
						</table>
						<button class="btn btn-lg btn-primary btn-block" type="submit">保存</button>
					</form>
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>

	<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"
		charset="UTF-8"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/locales/bootstrap-datetimepicker.zh-CN.js"
		charset="UTF-8"></script>
	<script type="text/javascript">
		$('.form_datetime').datetimepicker({
			format : 'yyyy-MM-dd',
			language : 'zh-CN',
			weekStart : 1,
			todayBtn : 1,
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			minView : "month",
			showMeridian : 1
		});
	</script>


</body>
</html>
