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
<!-- 1，引入js文件 -->
<script type="text/javascript" src="<%=basePath%>fckeditor/fckeditor.js"></script>

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
					<li class="navbar-btn"><a href="homeAction_toIndex">首页<span
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
					<li class="active navbar-btn"><a
						href="homeAction_toExchangeCentreUI">交流中心<span class="sr-only"></span></a></li>
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

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-1 main">

				<!-- 路径 -->
				<ol class="breadcrumb">
					<li><a href="topicAction_toTopicListUI">交流中心 <span
							class="glyphicon glyphicon-chevron-right"></span></a>
					<li class="active">发表新主题<span
						class="glyphicon glyphicon-chevron-right"></li>
					</li>
				</ol>

				<!--发表新主题开始-->
				<div class="QuictReply">
					<form action="topicAction_createTopic" method="post">
						<s:hidden name="type" value="1"></s:hidden>
						<div style="padding-left: 3px;">
							<table class="table table-striped">
								<tr>
									<td width="100px"><b>主题类型</b></td>
									<td><select class=" btn btn-default"
										onchange="window.location=this.value;">
											<option value="topicAction_toCreateNormalTopicUI">知识讨论</option>
											<option value="topicAction_toCreateTopicUI">新增题目</option>
									</select></td>
								</tr>
								<tr height="50" class="Tint">
									<td width="50px" class="Deep"><b>标题</b></td>
									<td class="no_color_bg"><s:textfield type="text"
											cssStyle="font-size: 20px; font-family: '微软雅黑';" name="title"
											cssClass="InputStyle text-center form-control required"
											placeholder="请填写标题" style="width:100%" /></td>
								</tr>
								<tr class="Tint" height="200">
									<td valign="top" class="Deep"><b>内容</b></td>
									<td valign="top" class="no_color_bg"><s:textarea
											name="topicContent" cssClass="required"
											cssStyle="width: 100%; height: 100px;"></s:textarea></td>
								</tr>
								<tr height="30" class="Tint">
									<td class="center" colspan="2" align="center">
										<button type="submit" onclick="getContent()"
											class="btn btn-success">确认</button>
									</td>
								</tr>
							</table>
						</div>
					</form>
					<div style="height: 130px;"></div>
				</div>
				<!--发表新主题结束-->


			</div>
		</div>
	</div>
	<!-- 中间内容结束 -->


	<script type="text/javascript">
		var editor = new FCKeditor('topicContent');
		editor.BasePath = "fckeditor/";
		editor.ToolbarSet = "simple"; //"simple";
		editor.Width = "100%";
		editor.Config['EnableAdvanceTable'] = false;
		editor.ReplaceTextarea();
	</script>




	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>

</html>