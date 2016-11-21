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
	<div class="container ">
		<div class="row">

			<!-- 内容 -->
			<div class="panel panel-default">

				<ol class="breadcrumb panel-heading">
					<li>
					<li class="active">评测状态<span
						class="glyphicon glyphicon-chevron-right"></li>
					</li>
				</ol>

				<!-- 搜索 -->
				<div class="panel-body table-responsive">
					<s:form id="pageForm" action="testAnswerAction_toAnswerListUI"
						role="search">
						<div class="form-group navbar-form navbar-left">
							<s:textfield type="text" name="titleNameSearch"
								cssClass="form-control" placeholder="请输入试题名称" />
							<button type="submit" class="btn btn-danger">搜索</button>
						</div>
				</div>

				<table cellpadding="0px" cellspacing="0px"
					class="table table-hover table-striped">
					<thead>
						<tr>
							<th>提交序号</th>
							<th>试题名称</th>
							<th>提交时间</th>
							<th>代码长度</th>
							<th>语言</th>
							<th><s:select id="retsel" name="resultSearch"
									cssStyle="border: 0px; padding: 8px; font-weight: bold;"
									onchange="gotoPage(1)"
									list="%{  #{'评测结果':'评测结果','正确':'正确','错误':'错误','编译异常':'编译异常','运行错误':'运行错误','运行超时':'运行超时'}  }">
								</s:select></th> 
							<th>得分</th>
							<th>CPU使用</th>
							<th>内存使用</th>
							<th>评测详情</th>
						</tr>
					</thead>
					<tbody>

						<s:iterator value="recordList">
							<tr>
								<td>${id }</td>
								<td><s:a
										action="questionAction_toQuestionShowUI?questionSetId=%{question.scope.id}&id=%{question.id}"
										target="_blank">${question.title }</s:a></td>
								<td><s:date name="submitTime" format="yyyy-MM-dd hh:mm" /></td>
								<td>${codeLength }B</td>
								<td>${language }</td>
								<td>${result }</td>
								<td><s:if test="result == '等待评测' ">
								${result }
								</s:if> <s:else>
								${scores }
								</s:else></td>
								<td><s:if
										test="runtime == -1 || result == '运行超时' || result =='编译异常' ">
								${result }
								</s:if> <s:else>
								${runtime }ms
								</s:else></td>
								<td><s:if test="memory == -1 || result =='编译异常' ">
								${result }
								</s:if> <s:else>
								${memory }MB
								</s:else></td>
								<td><s:a action="testAnswerAction_toAnswerInfoUI?id=%{id}">评测详情</s:a></td>
							</tr>
						</s:iterator>

					</tbody>
				</table>
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