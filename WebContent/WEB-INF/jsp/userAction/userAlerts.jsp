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
	<%@ include file="/WEB-INF/jsp/public/nav.jspf"%>
	<!--   	导航条结束    -->

	<!-- 中间内容开始 -->
	<div class="container-fluid">
		<div class="row">
			<!-- 侧边栏 -->
			<%@ include file="/WEB-INF/jsp/public/leftMenu.jspf"%>

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">我的提醒</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>回复我的</th>
								<th></th>
							</tr>
						</thead>
						<tbody>

							<s:iterator value="recordList">
								<tr>
									<td><s:a action="topicAction_toTopicShowUI?id=%{topic.id}" target="_blank">${author.name }：回复
											${user.name} :${content }</s:a> &nbsp;&nbsp;<span><s:date
												name="postTime" format="yyyy-MM-dd hh:mm:ss" /></span></td> 
									<td><span class="text-muted">回复我的主题：“</span>
									<s:a action="topicAction_toTopicShowUI?id=%{topic.id}"
											target="_blank">${topic.title }</s:a><span class="text-muted">”</span></td>
									<td><s:a action="topicAction_toTopicShowUI?id=%{topic.id}"><span
											class="glyphicon glyphicon-comment"></span>回复</s:a></td>
								</tr>
							</s:iterator>


						</tbody>
					</table>
					<s:form id="pageForm" action="userAction_toUserAlertsUI"
						role="search" method="post"> 
					</s:form>
					<!-- 分页开始 -->
					<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
					<!-- 分页结束 -->
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>
