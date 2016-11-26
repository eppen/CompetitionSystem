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
<!-- 表单验证 -->
<%@ include file="/WEB-INF/jsp/public/formvalidate.jspf"%>
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

				<h2 class="sub-header">添加试题</h2>

				<div class="table-responsive">
					<form action="questionAction_createQuestionAnswer"
						class="form-horizontal" role="form" method="post">

						<table class="table table-responsive">
							<thead>
								<tr>
									<th class="col-md-9 col-lg-9 col-sm-9">输出答案</th>
									<th class="col-md-3 col-lg-3 col-sm-3">此项分数</th>
								</tr>
							</thead>
							<tbody>
								<tr class="alert alert-dismissable">
									<td><input type="text"
										style="font-size: 18px; font-family: '微软雅黑'; width: 100%;"
										name="answerlist" class="form" value="5050" /></td>
									<td><input type="text"
										style="font-size: 18px; font-family: '微软雅黑'; width: 100%;"
										name="fractionlist" class="form required" readonly="true" value="100" /></td>    
										   
							</tbody> 
						</table>

						<button id="btn_submit" type="submit" class="btn btn-lg btn-success btn-block">确认</button>
					</form>
				</div>

			</div>
		</div>

	</div>
	<!-- 中间内容结束 -->


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>

</body>
</html>
