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
<!-- 文件上传自定义框架 -->
<link href="<%=basePath%>js/LXXUploadNeeded/LXXUploadPic.css"
	rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/LXXUploadNeeded/jquery-2.2.1.js"></script>
<script src="<%=basePath%>js/LXXUploadNeeded/LXXUploadPic.js"></script>
<!-- 文件上传相关验证 -->
<script src="<%=basePath%>js/fileuploadvalidate.js"></script>
<!-- 表单验证 -->
<%@ include file="/WEB-INF/jsp/public/formvalidate.jspf"%>
<!-- 编辑框 -->
<script src="<%=basePath%>js/ckeditor/ckeditor.js"></script>
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
					<s:form action="topicAction_createTopic" method="post"
						enctype="multipart/form-data" cssClass="form-horizontal"
						role="form">
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
									<td valign="top" class="no_color_bg"><div
											class="grid-container">
											<div class="grid-width-100">
												<s:textarea name="topicContent" id="editor"></s:textarea>
											</div>
										</div></td>
								</tr>
								<tr>
									<td width="100px"></td>
									<td><div id="LXXUploadPic" LXXCol="5" LXXRow="1"
											LXXWidth="100" LXXHeight="100"></div></td>
								</tr>
								<tr height="30" class="Tint">
									<td class="center" colspan="2" align="center">
										<button type="submit" onclick="getContent()"
											class="btn btn-success">确认</button>
									</td>
								</tr>
							</table>
						</div>
					</s:form>
					<div style="height: 130px;"></div>
				</div>
				<!--发表新主题结束-->


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