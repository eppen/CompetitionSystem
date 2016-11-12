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
<link href="css/dashboard.css" rel="stylesheet" />
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

				<h2 class="sub-header">修改试题集</h2>
				<div class="table-responsive">
					<form action="questionSetAction_updateQuestion"
						class="form-horizontal" role="form">
						<s:hidden name="id"></s:hidden>
						<table class="table table-striped">
							<tr height="50" class="Tint">
								<td class="no_color_bg"><s:textfield type="text"
										cssStyle="font-size: 20px; font-family: '微软雅黑';width:100%"
										name="title" cssClass="InputStyle text-center form-control"
										placeholder="试题集名称" /></td>
							</tr>
							<tr>
								<td><s:textarea
										cssStyle="font-size: 20px; font-family: '微软雅黑';"
										name="description" cssClass="form-control" rows="6"
										placeholder="试题集描述"></s:textarea></td>
							</tr>
						</table>
						<button class="btn btn-lg btn-primary btn-block" type="submit">修改</button>
					</form>
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>
