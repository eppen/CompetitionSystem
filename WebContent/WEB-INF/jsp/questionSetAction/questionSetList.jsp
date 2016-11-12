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
<!-- 禁用响应式布局 -->
</head>

<body>

	<!--   	导航条开始    -->
	<%@ include file="/WEB-INF/jsp/public/nav.jspf"%>
	<!--   	导航条结束    -->

	<!-- 中间内容开始 -->
	<div class="container">
		<div class="row">

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-1 main">

				<!-- 路径 -->
				<ol class="breadcrumb">
					<li>
					<li class="active">试题集<span
						class="glyphicon glyphicon-chevron-right"></li>
					</li>
				</ol>

				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>试题集名称</th>
								<th>试题总数</th>
								<th>更新时间</th>
								<th>进入试题</th>
							</tr>
						</thead>
						<tbody>

							<!-- 求总题数 -->
							<s:set var="count" value="0" />
							<s:iterator value="recordList">
								<tr>
									<td><s:a
											action="questionAction_toQuestionListUI?questionSetId=%{id}">${title}</s:a></td>  
									<td>${questionCount}</td>
									<td><s:date name="updateTime" format="yyyy年MM月dd日" /></td>
									<td><s:a
											action="questionAction_toQuestionListUI?questionSetId=%{id}">进入</s:a></td>
								</tr>
								<s:set var="count" value="#count+questionCount" /> 
							</s:iterator>

						</tbody>
					</table>
					<tr>
						<td>试题总数：<s:property value="#count" /></td>
					</tr>
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>

</html>