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
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>>

<!-- 代码编辑器样式 -->
<link href="<%=basePath%>codemirror/codemirror.css" type="text/css"
	rel="stylesheet" />
<link href="<%=basePath%>codemirror/eclipse.css" type="text/css"
	rel="stylesheet" />
<link href="<%=basePath%>codemirror/neat.css" type="text/css"
	rel="stylesheet" />

</head>

<body>

	<!--   	导航条开始    -->
	<%@ include file="/WEB-INF/jsp/public/nav.jspf"%>
	<!--   	导航条结束    -->

	<!-- 中间内容开始 -->
	<div class="container">
		<div class="row">

			<!-- 内容 -->
			<div class="panel panel-default">

				<!-- 路径 -->
				<ol class="breadcrumb panel-heading">
					<li><a href="testAnswerAction_toAnswerListUI">评测状态 <span
							class="glyphicon glyphicon-chevron-right"></span></a>
					<li class="active">${id } ${question.title }<span
						class="glyphicon glyphicon-chevron-right"></li>
					</li>
				</ol>

				<div class="table-responsive">
					<table class="table table-hover">
						<tbody>

							<tr>
								<td><b>提交序号</b></td>
								<td>${id }</td>
							</tr>
							<tr>
								<td><b>用户</b></td>
								<td>${student.name }</td>
							</tr>
							<tr>
								<td><b>提交时间</b></td>
								<td><s:date name="submitTime" format="yyyy-MM-dd hh:mm" /></td>
							</tr>
							<tr>
								<td><b>评测结果</b></td>
								<td>${result }</td>
							</tr>
							<tr>
								<td><b>得分</b></td>
								<td>${scores }</td>
							</tr>
							<tr>
								<td><b>CPU使用</b></td>
								<td><s:if
										test="runtime == -1 || result == '运行超时' || result =='编译异常' ">
								${result }
								</s:if> <s:else>
								${runtime }ms
								</s:else></td>
							</tr>
							<tr>
								<td><b>内存 使用</b></td>
								<td><s:if test="memory == -1 || result =='编译异常' ">
								${result }
								</s:if> <s:else>
								${memory }MB
								</s:else></td>
							</tr>
							<tr>
								<td><b>试题名称</b></td>
								<td><s:a
										action="questionAction_toQuestionShowUI?questionSetId=%{question.scope.id}&id=%{question.id}" target="_blank">${question.title }</s:a></td>
							</tr>
							<tr>
								<td><b>语言</b></td>
								<td>${language }</td>
							</tr>
							<tr>
								<td><b>源代码</b></td>
								<s:hidden name="answer" id="answer"></s:hidden>
								<td><s:textarea id="ucode"></s:textarea></td>
							</tr>
							<tr>
								<td><b>编译信息</b></td>
								<td>${compileInfo }</td>
							</tr>
							<tr>
								<!-------------------------------- 评测详情开始 -------------------------------->
								<div id="detailcases" style="display: none;">
									<table class="table table-hover table-striped">
										<tr>
											<th class="thodd">评测点序号</th>
											<th class="theven">评测结果</th>
											<th class="thodd">得分</th>
											<th class="theven">CPU使用</th>
											<th class="thodd">内存使用</th>
										</tr>

										<s:iterator value="scoringPoints" status="status">
											<tr>
												<td>${status.index+1 }</td>
												<td><span class="Color_AC">${result }</span></td>
												<td>${score }</td>
												<td><s:if
														test="runtime == -1 || result == '运行超时' || result =='编译异常' ">${result } </s:if>
													<s:else>${runtime }ms</s:else></td>
												<td><s:if test="memory == -1 || result =='编译异常' ">${result }</s:if>
													<s:else>${memory }MB</s:else></td>
											</tr>
										</s:iterator>

									</table>
								</div>
							</tr>

							<!-------------------------------- 评测详情结束 -------------------------------->

						</tbody>
					</table>
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>

	<!------------------------- 代码编辑器 ------------------------->
	<script type="text/javascript"
		src="<%=basePath%>codemirror/codemirror.min.js"></script>
	<script src="<%=basePath%>codemirror/clike.js"></script>
	<script src="<%=basePath%>codemirror/active-line.js"></script>
	<script src="<%=basePath%>codemirror/matchbrackets.js"></script>
	<script type="text/javascript">
		function setCode() {
			var answerstr = document.getElementById('answer');
			var codeV = answerstr.value;
			$("#ucode").val(codeV);
			if ("JAVA" == "JAVA") {
				var cmJava = CodeMirror.fromTextArea($("#ucode").get(0), {
					lineNumbers : true,
					matchBrackets : true,
					mode : "text/x-java",
					theme : "eclipse",
					indentWithTabs : true,
					styleActiveLine : true,
					indentUnit : 2,
					tabSize : 2,
					readOnly : true
				});
			} else if ("JAVA" == "C") {
				var cmC = CodeMirror.fromTextArea($("#ucode").get(0), {
					lineNumbers : true,
					matchBrackets : true,
					mode : "text/x-csrc",
					theme : "neat",
					indentWithTabs : true,
					styleActiveLine : true,
					indentUnit : 2,
					tabSize : 2,
					readOnly : true
				});
			} else {
				var cmCpp = CodeMirror.fromTextArea($("#ucode").get(0), {
					lineNumbers : true,
					matchBrackets : true,
					mode : "text/x-c++src",
					theme : "neat",
					indentWithTabs : true,
					styleActiveLine : true,
					indentUnit : 2,
					tabSize : 2,
					readOnly : true
				});
			}
		}
		setCode();
	</script>

</body>

</html>