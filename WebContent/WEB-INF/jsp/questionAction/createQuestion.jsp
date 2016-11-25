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
<!-- 1，引入js文件 -->
<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
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

				<h2 class="sub-header">添加试题</h2>
				<div class="table-responsive">
					<s:form action="questionAction_createQuestion" method="post"
						enctype="multipart/form-data" cssClass="form-horizontal"
						role="form">

						<table class="table table-striped">
							<tr height="50" class="Tint">
								<td width="50px" class="Deep"><b>试题名称</b></td>
								<td class="no_color_bg"><input type="text"
									style="font-size: 20px; font-family: '微软雅黑';" name="title"
									class="InputStyle text-center form-control required"
									placeholder="请输入试题名称" style="width:100%" /></td>
							</tr>
							<s:hidden name="type" value="2"></s:hidden>
							<tr>
								<td width="100px"><b>编程语言</b></td>
								<td><select name=language class=" btn btn-default required">
										<option value="JAVA">JAVA</option>
								</select></td>
							</tr>
							<tr>
								<td width="100px"><b>所属试题集</b></td>
								<td><s:select name="scopeId"
										cssClass="SelectStyle btn btn-default" list="questionSets"
										listKey="id" listValue="title" headerKey="">
									</s:select></td>
							</tr>
							<tr>
								<td width="100px"><b>问题描述</b></td>
								<td><textarea name="description" class="form-control"
										rows="3"></textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>问题内容</b></td>
								<td><textarea name="content" class="form-control required"
										rows="6" style="width: 100%; height: 100px;"></textarea></td>
							</tr>
							<tr> 
								<td width="100px"><b>试题图片</b></td>
								<td>
									<div id="LXXUploadPic" LXXCol="5" LXXRow="1" LXXWidth="100"
										LXXHeight="100"></div>
								</td>
							</tr>
							<tr>
								<td width="100px"><b>输入格式</b></td>
								<td><textarea name="inputFormat" class="form-control"
										rows="3"></textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>输出格式</b></td>
								<td><textarea name="outputFormat" class="form-control"
										rows="3"></textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>样例输入</b></td>
								<td><textarea name="sampleInput" class="form-control"
										rows="3"></textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>样例输出</b></td>
								<td><textarea name="sampleOutput" class="form-control"
										rows="3"></textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>注意</b></td>
								<td><textarea name="careful" class="form-control" rows="3"></textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>提示</b></td>
								<td><textarea name="cue" class="form-control" rows="3"></textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>耗时要求</b></td>
								<td><input type="text"
									style="font-size: 18px; font-family: '微软雅黑'; width: 10%;"
									name="runtime" class="form required" value="500" />ms</td>
							</tr>
							<tr>
								<td width="100px"><b>内存要求</b></td>
								<td><input type="text"
									style="font-size: 18px; font-family: '微软雅黑'; width: 10%;"
									name="memory" class="form required" value="5.545" />MB</td>
							</tr>
						</table>
						<button class="btn btn-lg btn-primary btn-block" type="submit">确认</button>
					</s:form>
				</div>
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
	</script>

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>
