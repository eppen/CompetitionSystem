<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/public/header.jspf"%>
</head>
<body>
	<s:debug></s:debug>
	
	<s:textfield name="username" placeholder="Email address" ></s:textfield>  
	<s:textfield name="password"></s:textfield>
	<input name="username" class="form-control" required autofocus> 
     <input  name="password" class="form-control" required>
</body>
</html>