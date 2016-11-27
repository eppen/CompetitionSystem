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
<!-- 表单验证 -->
<%@ include file="/WEB-INF/jsp/public/formvalidate.jspf"%>
<!-- 编辑框 -->
<script src="<%=basePath%>js/ckeditor/ckeditor.js"></script>
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

				<h2 class="sub-header">修改试题</h2>
				<div class="table-responsive">
					<form action="questionAction_updateQuestion"
						class="form-horizontal" role="form" method="post">
						<s:hidden name="id"></s:hidden>
						<table class="table table-striped">
							<tr height="50" class="Tint">
								<td width="50px" class="Deep"><b>试题名称</b></td>
								<td class="no_color_bg"><s:textfield type="text"
										cssStyle="font-size: 20px; font-family: '微软雅黑';width:100%"
										name="title" cssClass="text-center form-control required" /></td>
							</tr>
							<s:hidden name="type" value="2"></s:hidden>
							<tr>
								<td width="100px"><b>编程语言</b></td>
								<td><s:select name="language"
										cssClass=" btn btn-default required" list="#{'JAVA':'JAVA'} ">
									</s:select></td>
							</tr>
							<tr>
								<td width="100px"><b>所属试题集</b></td>
								<td><s:select name="scope.id"
										cssClass="SelectStyle btn btn-default" list="questionSets"
										listKey="id" listValue="title" headerKey="">
									</s:select></td>
							</tr>
							<tr>
								<td width="100px"><b>问题描述</b></td>
								<td><s:textarea name="description" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>问题内容</b></td>
								<td>
									<div class="grid-container">
										<div class="grid-width-100">
											<s:textarea name="content" cssClass="required"  id="editor"></s:textarea>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="100px"><b>输入格式</b></td>
								<td><s:textarea name="inputFormat" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>输出格式</b></td>
								<td><s:textarea name="outputFormat" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>样例输入</b></td>
								<td><s:textarea name="sampleInput" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>样例输出</b></td>
								<td><s:textarea name="sampleOutput" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>注意</b></td>
								<td><s:textarea name="careful" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>提示</b></td>
								<td><s:textarea name="cue" cssClass="form-control" rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>耗时要求</b></td>
								<td><s:textfield type="text"
										style="font-size: 18px; font-family: '微软雅黑'; width: 10%;"
										name="runtime" cssClass="form required number" />ms</td>
							</tr>
							<tr>
								<td width="100px"><b>内存要求</b></td>
								<td><s:textfield type="text"
										style="font-size: 18px; font-family: '微软雅黑'; width: 10%;"
										name="memory" cssClass="form required number" />MB</td>
							</tr>
						</table>
						<button class="btn btn-lg btn-primary btn-block" type="submit">修改</button>
					</form>
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->

	<script>
		window.onload = function() {
			CKEDITOR.replace('editor');
		};

		$("#submit").click(function() {
			/* CKEDITOR.replace("editor1"); */
			var title = $("#caption").val(); /*不能用.text()或.html()*/
			var val = CKEDITOR.instances.editor.getData();
		}); 
	</script>

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>
