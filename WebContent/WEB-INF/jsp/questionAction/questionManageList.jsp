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

				<h2 class="sub-header">试题管理</h2>
				<div class="table-responsive">
					<div class="container">
						<s:a action="questionAction_toCreateQuestionUI?scopeId=%{questionSetId}"  
							cssClass="btn btn-success">创建试题</s:a>
					</div>
					<!------- 搜索框 ------->
					<s:form id="pageForm"
						action="questionAction_toQuestionManageListUI?questionSetId=%{questionSetId}"
						cssClass="navbar-form navbar-left" role="search">
						<div class="form-group">
							<s:textfield type="text" name="titleSearch"
								cssClass="form-control" placeholder="请输入试题名称" />
						</div>
						<button type="submit" class="btn btn-danger">搜索</button>
					</s:form>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>试题号</th>
								<th>试题名称</th>
								<th>试题说明</th>
								<th>语言</th>
								<th>试题类型</th>
								<th>创建时间</th>
							</tr>
						</thead>
						<tbody>

							<!-- 求试题集中试题个数 -->
							<s:set var="count" value="0" />
							<s:iterator value="recordList">
								<tr>
									<td>SF-${id}</td>
									<td><s:a
											action="questionAction_toQuestionShowUI?questionSetId=%{questionSetId}&id=%{id}"
											target="_Blank">${title}</s:a></td>
									<td>${description}</td>
									<td>${language}</td>
									<td>${scope.title}</td>
									<td><s:date name="createTime" format="yyyy年MM月dd日" /></td>
									<td><s:a
											action="questionAction_toUpdateQuestionUI?id=%{id}">修改</s:a></td>
									<td><s:a action="questionAction_deleteQuestion?id=%{id}"
											onClick="return confirm('确定要删除本试题吗？')">删除</s:a></td> 
									<td><s:a
											action="questionAction_toQuestionShowUI?questionSetId=%{questionSetId}&id=%{id}"
											target="_Blank">进入</s:a></td>
								</tr>
								<s:set var="count" value="scope.questionCount" />
							</s:iterator>

						</tbody>
					</table>
					<tr>
						<td>试题总数：<s:property value="#count" /></td>
					</tr>

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
