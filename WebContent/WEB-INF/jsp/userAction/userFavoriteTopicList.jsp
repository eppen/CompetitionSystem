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

				<h2 class="sub-header">我的收藏</h2>
				<div class="table-responsive">

					<table class="table table-hover table-condensed"
						style="font-family: 微软雅黑; font-size: 17px;">
						<thead>
							<tr>
								<th width="50" class="ForumPageTableTitle">
									<!--状态/图标-->&nbsp;
								</th>
								<th>主题</th>
								<th>作者</th>
								<th>创建时间</th>
								<th>类型</th>
								<th>回复数</th>
								<th>点赞数</th>
								<th>进入主题</th>
							</tr>
						</thead>
						<tbody>

							<s:iterator value="recordList">
								<tr>
									<td><img src="<%=basePath%>img/topicType_${classify}.gif" /><a
										href="rumenPratice.html"></td>
									<td><s:a action="topicAction_toTopicShowUI?id=%{id}"
											target="_blank">${title }</s:a></td>
									<td><a href="#" target="_blank">${author.name }</td>
									<td><s:date name="postTime" format="yyyy-MM-dd" /></td>
									<td><s:if test="type==1">知识讨论								</s:if> <s:elseif
											test="type==0">新增题目 
										</s:elseif></td>
									<td>${replyCount }</td>
									<td>${laudCount }</td>
									<td><s:a action="topicAction_toTopicShowUI?id=%{id}"
											target="_blank">进入</s:a></td>
								</tr>
							</s:iterator>

						</tbody>
					</table>

					<!--  其他功能  已删除-->

					<s:form id="pageForm" action="userAction_toUserTopicListUI"
						role="search">
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
