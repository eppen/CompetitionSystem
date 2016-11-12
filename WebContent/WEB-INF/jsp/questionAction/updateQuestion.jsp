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
<!-- 1，引入js文件 -->
<script type="text/javascript" src="fckeditor/fckeditor.js"></script>

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

				<h2 class="sub-header">修改试题</h2>
				<div class="table-responsive">
					<form action="questionAction_updateQuestion"
						class="form-horizontal" role="form">
						<s:hidden name="id"></s:hidden>
						<table class="table table-striped">
							<tr height="50" class="Tint">
								<td width="50px" class="Deep"><b>试题名称</b></td>
								<td class="no_color_bg"><s:textfield type="text"
										cssStyle="font-size: 20px; font-family: '微软雅黑';width:100%"
										name="title" cssClass="text-center form-control required" /></td>
							</tr>
							<tr>
								<td width="100px"><b>题目类型</b></td>
								<td><s:select name="type" cssClass=" btn btn-default"
										list="#{1:'结果填空',2:'程序设计',3:'代码填空'}">
									</s:select></td>
							</tr>
							<tr>
								<td width="100px"><b>编程语言</b></td>
								<td><s:select name="language" cssClass=" btn btn-default"
										list="#{'JAVA':'JAVA'} ">
									</s:select></td>
							</tr>
							<tr>
								<td width="100px"><b>所属试题集</b></td>
								<td><s:select name="scope.id"
										cssClass="SelectStyle btn btn-default" list="questionSets"
										listKey="id" listValue="title" headerKey="">
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
								<td width="100px"><b>输入格式</b></td>
								<td><s:textarea name="inputFormat" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>输出格式</b></td>
								<td><s:textarea name="outputFormat" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>样例输入</b></td>
								<td><s:textarea name="sampleInput" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>样例输出</b></td>
								<td><s:textarea name="sampleOutput" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>注意</b></td>
								<td><s:textarea name="careful" cssClass="form-control"
										rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>提示</b></td>
								<td><s:textarea name="cue" cssClass="form-control" rows="3"></s:textarea></td>
							</tr>
							<tr>
								<td width="100px"><b>耗时要求</b></td>
								<td><s:textfield type="text"
										style="font-size: 18px; font-family: '微软雅黑'; width: 10%;"
										name="runtime" cssClass="form required" />ms</td>
							</tr>
							<tr>
								<td width="100px"><b>内存要求</b></td>
								<td><s:textfield type="text"
										style="font-size: 18px; font-family: '微软雅黑'; width: 10%;"
										name="memory" cssClass="form required" />MB</td>
							</tr>
						</table>
						<button class="btn btn-lg btn-primary btn-block" type="submit">修改</button>
					</form>
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
