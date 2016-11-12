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

				<h2 class="sub-header">用户信息修改</h2>
				<div class="table-responsive">
					<form action="userAction_updateUser" class="form-horizontal"
						role="form">
						<s:hidden name="id"></s:hidden>
						<table class="table table-striped">
							<tbody>
								<tr>
									<td>用户名</td>
									<td>${username }</td>
								</tr>
								<tr>
									<td>姓名</td>
									<td><s:textfield type="text"
											cssClass="focus form-control required" name="name"></s:textfield></td>
								</tr>
								<tr>
									<td>性别</td>
									<td><s:radio list="%{#{'男':'男','女':'女'}}" name="gender"
											cssClass="required"></s:radio></td>
								</tr>
								<tr>
									<td>班级</td>
									<td><s:textfield type="text"
											cssClass="focus form-control required" name="classes"></s:textfield></td>
								</tr>
								<tr>
									<td>手机号码</td>
									<td><s:textfield type="text" cssClass="focus form-control"
											name="telephone"></s:textfield></td>
								</tr>
								<tr>
									<td>电子邮箱</td>
									<td><s:textfield type="text" cssClass="focus form-control"
											name="email"></s:textfield></td>
								</tr>
								<tr>
									<td>QQ</td>
									<td><s:textfield type="text" cssClass="focus form-control"
											name="qq"></s:textfield></td>
								</tr>
								<tr>
									<td>用户级别</td>
									<td><s:select name="role.id"
											cssClass="SelectStyle required" list="roleList" listKey="id"
											listValue="name" headerKey="" headerValue="==请选择级别==">
										</s:select></td>
								</tr>
							</tbody>
						</table>
						<button class="btn btn-lg btn-primary btn-block" type="submit">保存</button>
					</form>
				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>
