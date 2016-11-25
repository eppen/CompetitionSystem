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
<!-- 文件上传自定义框架 -->
<link href="<%=basePath%>js/LXXUploadNeeded/LXXUploadPic.css"
	rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/LXXUploadNeeded/jquery-2.2.1.js"></script>
<script src="<%=basePath%>js/LXXUploadNeeded/LXXUploadPic.js"></script>
<!-- 文件上传相关验证 -->
<script src="<%=basePath%>js/fileuploadvalidate.js"></script>
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

						<s:hidden name="type" value="0"></s:hidden>
						<div style="padding-left: 3px;">
							<table class="table table-striped">
								<tr>
									<td width="100px"><b>主题类型</b></td>
									<td><select class=" btn btn-default"
										onchange="window.location=this.value;">
											<option value="topicAction_toCreateTopicUI">新增题目</option>
											<option value="topicAction_toCreateNormalTopicUI">知识讨论</option>
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
								<tr>
									<td width="100px"><b>试题名称</b></td>
									<td><s:textfield type="text"
											cssStyle="font-size: 20px; font-family: '微软雅黑';width:100%"
											name="questionTitle"
											cssClass="InputStyle text-center form-control required"
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
									<td><s:textarea name="content"
											cssClass="form-control required" rows="6"
											cssStyle="width: 100%; height: 100px;"></s:textarea></td>
								</tr>
								<tr>
									<td width="100px"><b>试题图片</b></td>
									<td><div id="LXXUploadPic" LXXCol="5" LXXRow="1"
											LXXWidth="100" LXXHeight="100"></div></td>
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
											name="runtime" cssClass="form number required" />ms</td>
								</tr>
								<tr>
									<td width="100px"><b>内存要求</b></td>
									<td><s:textfield type="text"
											style="font-size: 18px; font-family: '微软雅黑'; width: 10%;"
											name="memory" cssClass="form number required" />MB</td>
								</tr>
								<tr height="30" class="Tint">
									<td class="center" colspan="2" align="center">
										<button type="submit" class="btn btn-success">确认</button>
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

	<script type="text/javascript">
		var editor = new FCKeditor('content');
		editor.BasePath = "fckeditor/";
		editor.ToolbarSet = "simple"; //"simple"; 
		editor.Width = "100%";
		editor.Config['EnableAdvanceTable'] = false;
		editor.ReplaceTextarea();

		var editor2 = new FCKeditor('topicContent');
		editor2.BasePath = "fckeditor/";
		editor2.ToolbarSet = "simple"; //"simple";
		editor2.Width = "100%";
		editor2.Config['EnableAdvanceTable'] = false;
		editor2.ReplaceTextarea();
	</script>


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>

</html>