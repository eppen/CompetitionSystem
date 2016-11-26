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
					<li class="active">编辑主题<span
						class="glyphicon glyphicon-chevron-right"></li>
					</li>
				</ol>

				<!--发表新主题开始-->
				<div class="QuictReply">
					<form action="topicAction_updateTopic" method="post">
						<s:hidden name="id"></s:hidden>
						<s:hidden name="type"></s:hidden>
						<div style="padding-left: 3px;">
							<table class="table table-striped">
								<tr>
									<td width="100px"><b>主题类型</b></td>
									<td><s:if test="type==0">新增题目</s:if> <s:elseif
											test="type==1">知识讨论</s:elseif></td>
								</tr>
								<tr height="50" class="Tint">
									<td width="50px" class="Deep"><b>标题</b></td>
									<td class="no_color_bg">${title}</td>
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
									<td width="100px"><b>试题名称</b></td>
									<td><s:textfield type="text"
											cssStyle="font-size: 20px; font-family: '微软雅黑';width:100%"
											name="questionTitle"
											cssClass="text-center form-control required"
											placeholder="请填写试题名称" /></td>
								</tr>
								<s:hidden name="questionType" value="2"></s:hidden>
								<tr>
									<td width="100px"><b>编程语言</b></td>
									<td><s:select name="language"
											cssClass=" btn btn-default required" list="#{'JAVA':'JAVA'} ">
										</s:select></td>
								</tr>
								<tr>
									<td width="100px"><b>所属试题集</b></td>
									<td><s:select name="scope.id"
											cssClass="SelectStyle btn btn-default required"
											list="questionSets" listKey="id" listValue="title"
											headerKey="">
										</s:select></td>
								</tr>
								<tr>
									<td width="100px"><b>问题描述</b></td>
									<td><s:textarea name="description" cssClass="form-control"
											rows="3"></s:textarea></td>
								</tr>
								<tr>
									<td width="100px"><b>问题内容</b></td>
									<td><div class="grid-container">
											<div class="grid-width-100">
												<s:textarea name="content" id="editor2"></s:textarea>
											</div>
										</div></td>
								</tr>
								<tr>
									<td width="100px"><b>输入格式</b></td>
									<td><s:textarea name="inputFormat" cssClass="form-control"
											rows="3"></s:textarea></td>
								</tr>
								<tr>
									<td width="100px"><b>输出格式</b></td>
									<td><s:textarea name="outputFormat"
											cssClass="form-control" rows="3"></s:textarea></td>
								</tr>
								<tr>
									<td width="100px"><b>样例输入</b></td>
									<td><s:textarea name="sampleInput" cssClass="form-control"
											rows="3"></s:textarea></td>
								</tr>
								<tr>
									<td width="100px"><b>样例输出</b></td>
									<td><s:textarea name="sampleOutput"
											cssClass="form-control" rows="3"></s:textarea></td>
								</tr>
								<tr>
									<td width="100px"><b>注意</b></td>
									<td><s:textarea name="careful" cssClass="form-control"
											rows="3"></s:textarea></td>
								</tr>
								<tr>
									<td width="100px"><b>提示</b></td>
									<td><s:textarea name="cue" cssClass="form-control"
											rows="3"></s:textarea></td>
								</tr>
								<tr>
									<td width="100px"><b>耗时要求</b></td>
									<td><s:textfield type="text"
											style="font-size: 18px; font-family: '微软雅黑'; width: 10%;"
											name="runtime" cssClass="form required" />ms</td>
								</tr>
								<tr>
									<td width="100px"><b>内存要求</b></td>
									<td><s:textfield type="text"
											style="font-size: 18px; font-family: '微软雅黑'; width: 10%;"
											name="memory" cssClass="form required" />MB</td>
								</tr>
								<tr height="30" class="Tint">
									<td class="center" colspan="2" align="center">
										<button type="submit" class="btn btn-success">确认修改</button>
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

	<script>
		window.onload = function() {
			CKEDITOR.replace('editor');
			CKEDITOR.replace('editor2'); 
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