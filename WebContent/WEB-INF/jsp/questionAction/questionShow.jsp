<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="题库">
<meta name="author" content="黄跃然 huangyueran">
<title>练习系统</title>
<%@ include file="/WEB-INF/jsp/public/header2.jspf"%>
<link href="<%=basePath%>css/imageFD.css" rel="stylesheet" />
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
					<li class="active navbar-btn"><a
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
	<div class="container">
		<div class="row">

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-1 main">

				<!-- 路径 -->
				<ol class="breadcrumb">
					<li><a href="homeAction_toCompetitionSystemUI">试题集 <span
							class="glyphicon glyphicon-chevron-right"></span></a> <s:a
							action="questionAction_toQuestionListUI?questionSetId=%{questionSetId}">${scope.title } <span
								class="glyphicon glyphicon-chevron-right"></span>
						</s:a>
					<li class="active">${title }<span
						class="glyphicon glyphicon-chevron-right"></span></li>
					</li>
				</ol>

				<div class="table-responsive jumbotron">
					<table class="table table-condensed">
						<tbody style="font-size: 16px; font-family: '微软雅黑';">
							<th style="font-size: 25px; text-align: center;">${title }</th>
							<tr class="text-info"
								style="font-size: 25px; text-align: center;">
								<td>时间限制：${runtime }ms 内存限制：${memory }MB</td>
							</tr>
							<tr>
								<td><b>问题描述</b></td>
							</tr>
							<tr>
								<td>${description }</td>
							</tr>
							<tr>
								<td>
									<div class="col-md-7" style="margin-top: 2px">
										<s:if test="imagePath1!=null">
											<p>
												<a id="example1" href='<s:property value ="imagePath1" />'><img
													class="img-responsive"
													src='<s:property value ="imagePath1" />'></a>
											</p>
										</s:if>
										<s:if test="imagePath2!=null">
											<p>
												<a id="example2" href='<s:property value ="imagePath2" />'><img
													class="img-responsive"
													src='<s:property value ="imagePath2" />'></a>
											</p>
										</s:if>
										<s:if test="imagePath3!=null">
											<p>
												<a id="example3" href='<s:property value ="imagePath3" />'><img
													class="img-responsive"
													src='<s:property value ="imagePath3" />'></a>
											</p>
										</s:if>
										<s:if test="imagePath4!=null">
											<p>
												<a id="example4" href='<s:property value ="imagePath4" />'><img
													class="img-responsive"
													src='<s:property value ="imagePath4" />'></a>
											</p>
										</s:if>
										<s:if test="imagePath5!=null">
											<p>
												<a id="example5" href='<s:property value ="imagePath5" />'><img
													class="img-responsive"
													src='<s:property value ="imagePath5" />'></a>
											</p>
										</s:if>

									</div>
								</td>
							</tr>
							<tr>
								<td><b>输入格式</b></td>
							</tr>
							<tr>
								<td>${inputFormat }<br /></td>
							</tr>
							<tr>
								<td><b>输出格式</b></td>
							</tr>
							<tr>
								<td>${outputFormat }<br /></td>
							</tr>
							<tr>
								<td><b>注意</b></td>
							</tr>
							<tr>
								<td>${careful }<br /></td>
							</tr>
							<tr>
								<td><b>样例输入</b></td>
							</tr>
							<tr>
								<td>${sampleInput }</td>
							</tr>
							<tr>
								<td><b>样例输出</b></td>
							</tr>
							<tr>
								<td>${sampleOutput }</td>
							</tr>
							<tr>
								<td><b>提示</b></td>
							</tr>
							<tr>
								<td>${cue }<br /></td>
							</tr>
							<tr>
								<td><center>
										<s:a
											action="testAnswerAction_toSubmitAnswerUI?questionId=%{id}"
											cssClass="btn btn-warning btn-lg">提交答案</s:a>
									</center></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>

	<script type="text/javascript" src="js/imageFD/jquery.min.js"></script>
	<script type="text/javascript" src="js/imageFD/jquery.imgbox.pack.js"></script>
	<script>
		$(function() {
			$("#example1").imgbox();
			$("#example2").imgbox();
			$("#example3").imgbox();
			$("#example4").imgbox();
			$("#example5").imgbox();
		});
	</script> 

</body>

</html>