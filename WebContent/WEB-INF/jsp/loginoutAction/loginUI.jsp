<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="题库">
<meta name="author" content="黄跃然 huangyueran">
<title>练习系统</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>

</head>

<body>

	<div class="container">

		<s:form action="loginoutAction_login" cssClass="form-signin"
			role="form" method="post">
			<h2 class="form-signin-heading">请登录</h2>
			<h3  style="color: red"><s:fielderror cssStyle=" list-style:none;"><s:param>loginerror</s:param></s:fielderror></h3>    
			<s:textfield cssClass="form-control required" name="username"
				placeholder="请输入用户名" />
			<s:password type="password" cssClass="form-control required"
				name="password" placeholder="请输入密码" />

			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</s:form>

	</div>

</body>

</html>
