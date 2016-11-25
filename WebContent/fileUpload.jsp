<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>

<base href="<%=basePath%>">



<title>My JSP 'fileUpLoad.jsp' starting page</title>
<link href="js/LXXUploadNeeded/LXXUploadPic.css" rel="stylesheet"
	type="text/css">
<script src="js/LXXUploadNeeded/jquery-2.2.1.js"></script>
<script src="js/LXXUploadNeeded/LXXUploadPic.js"></script>


<meta http-equiv="pragma" content="no-cache">

<meta http-equiv="cache-control" content="no-cache">

<meta http-equiv="expires" content="0">

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

<meta http-equiv="description" content="This is my page">



</head>



<body>

	<center>
		<s:form action="fileUpload" method="POST"
			enctype="multipart/form-data">

			<s:fielderror />

			<div id="LXXUploadPic" LXXCol="5" LXXRow="1" LXXWidth="100"
				LXXHeight="100"></div> 

			<s:textfield name="caption" label="Caption" />

			<s:submit />

		</s:form>

	</center>

</body>

</html>


