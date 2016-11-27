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
<!-- 禁用响应式布局 -->
</head>
<body>

	<!--   	导航条开始    -->
	<%@ include file="/WEB-INF/jsp/public/nav.jspf"%> 
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

				<h2 class="sub-header">添加试题</h2>
				<div class="table-responsive">
					<form action="questionAction_createQuestionAnswer" class="form-horizontal"
						role="form" method="post">

						<table class="table table-responsive">
							<thead>
								<tr>
									<td>
										<button id="btnAdd" type="button" class="btn btn-danger">添加选项</button>
									</td>
								</tr>
								<tr>
									<th class="col-md-12 col-lg-12 col-sm-12">输入值</th>
								</tr>
							</thead>

							<tbody id="mybody">
								<tr class="alert alert-dismissable">
									<td><input type="text"
										style="font-size: 18px; font-family: '微软雅黑';" name="answerlist"
										class="form-control required" value="int a=b;" /></td>
										<s:hidden name="fractionlist" value="100"></s:hidden>
									<td><button type="button" class="close"
											data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button></td>
								</tr>
							</tbody>
						</table>
						<button id="btn_submit" type="submit" class="btn btn-lg btn-success btn-block">确认</button>
					</form> 
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>

	<script type="text/javascript">
		$(function() {
			//按钮点击事件
			$("#btnAdd")
					.click(
							function() {
								//接收用户输入的列数据

								//构建新行
								var newRow = "<tr class='alert alert-dismissable'><td><input type='text' style='font-size: 18px;font-family: '微软雅黑';' name='answerlist' class='form-control required' value='int a=b;'/></td><td><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></td></tr>";
								//为表格追加新行
								$("#mybody").append(newRow);
							});
		});
	</script>

</body>
</html>
