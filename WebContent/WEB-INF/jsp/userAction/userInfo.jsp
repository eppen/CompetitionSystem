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

	<!-- 中间部分开始 -->
	<div class="container">
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h2 class="sub-header">${name }的个人主页</h2> 
			<div class="table-responsive">
				<table class="table table-striped">
					<tbody>
						<tr>
							<td>姓名</td>
							<td>${name }</td>
						</tr>
						<tr>
							<td>性别</td>
							<td>${gender }</td>
						</tr>
						<tr>
							<td>班级</td>
							<td>${classes }</td>
						</tr>
						<tr>
							<td>手机号码</td>
							<td>${telephone }</td>
						</tr>
						<tr>
							<td>电子邮箱</td>
							<td>${email }</td>
						</tr>
						<tr>
							<td>QQ</td>
							<td>${qq }</td>      
						</tr> 
						<tr>
							<td>当前积分</td>
							<td>${score }</td>
						</tr>
						<tr>
							<td>用户级别</td>
							<td>${role.name }</td> 
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 中间部分结束 -->


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>

</html>