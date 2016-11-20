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
	<div class="container-fluid">
		<div class="row">

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-1 main">

				<!-- 路径 -->
				<ol class="breadcrumb">
					<li><s:a action="homeAction_toCompetitionSystemUI">试题集 <span
								class="glyphicon glyphicon-chevron-right"></span>
						</s:a>
					<li class="active">${title } <span  
						class="glyphicon glyphicon-chevron-right"></li>
					</li>
				</ol>
				<s:form id="pageForm"
					action="questionAction_toQuestionListUI?questionSetId=%{id}"
					cssClass="navbar-form navbar-left" role="search">
				</s:form>

				<table class="table table-hover">
					<thead>
						<tr>
							<th>试题号</th>
							<th>试题名称</th>
							<th>试题说明</th>
							<th>语言</th>
							<th>创建时间</th>
							<th>进入试题</th>
							<th>提交记录</th>
						</tr>
					</thead> 
					<tbody>
						<!-- 求试题集中试题个数 -->
						<s:set var="count" value="0" />
						<s:iterator value="recordList">
							<tr>
								<td>SF-${id}</td>
								<td><s:a
										action="questionAction_toQuestionShowUI?questionSetId=%{questionSetId}&id=%{id}">${title}</s:a></td>
								<td>${description}</td>
								<td>${language}</td>
								<td><s:date name="createTime" format="yyyy年MM月dd日" /></td>
								<td><s:a
										action="questionAction_toQuestionShowUI?questionSetId=%{questionSetId}&id=%{id}">进入</s:a></td>
								<td><s:a action="#">提交记录</s:a></td>
							</tr>
							<s:set var="count" value="scope.questionCount" />
						</s:iterator>

					</tbody>
				</table>



				<!-- 分页开始 -->
				<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
				<!-- 分页结束 -->

			</div>
		</div>

	</div>

	<!-- 中间内容结束 -->

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>

</html>