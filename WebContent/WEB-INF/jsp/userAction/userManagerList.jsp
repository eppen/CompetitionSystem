<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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

				<h2 class="sub-header">用户管理</h2>
				<div class="table-responsive">

					<!------- 搜索框 ------->
					<form id="pageForm" action="userAction_toUserManageListUI" class="navbar-form navbar-left"
						role="search" method="post"> 
						<div class="input-group" style="width: 50%"> 
							<s:textfield type="text" name="usernameSearch"
								cssClass="form-control input-md" placeholder="请输入用户名" />    
							<span onclick="javascript: gotoPage(1)" class="input-group-addon btn btn-primary">搜索</span>  
						</div> 
					</form>

					<table class="table table-bordered table-hover with-check  
						style="font-family: 微软雅黑; font-size: 16px;"> 
						<thead>
							<tr>
								<th style="text-align: center;"></th>
								<th style="text-align: center;">姓名</th>
								<th style="text-align: center;">用户名</th>
								<th style="text-align: center;">性别</th>
								<th style="text-align: center;">班级</th>
								<th style="text-align: center;">手机号码</th>
								<th style="text-align: center;">积分</th>
								<th style="text-align: center;">用户级别</th>
								<th style="text-align: center;">权限</th>
							</tr>
						</thead>
						<tbody>

							<s:iterator value="recordList">
								<tr>
									<td>${id }</td>
									<td><s:a action="userAction_toUserInfoUI?id=%{id}" target="_blank">${name }</s:a></td>
									<td><s:a action="userAction_toUserInfoUI?id=%{id}" target="_blank">${username }</s:a></td>
									<td>${gender }</td>  
									<td>${classes }</td>
									<td>${telephone }</td> 
									<td>${score }</td>
									<td>${role.name}</td> 
									<td>
									
									<!-- 如果是超级管理员 则没有不能对其进行修改权限 -->
									<s:if test="role.name != '超级管理员'">   
									
									<s:if test="isBan==0">
									<s:a action="userAction_updateUserIsBan?isBan=1&id=%{id}" cssClass="btn btn-default">禁言</s:a>
									 <s:a action="userAction_updateUserIsBan?isBan=0&id=%{id}" cssClass="btn btn-default disabled">解除</s:a> 
									</s:if>
									<s:else>
									<s:a action="userAction_updateUserIsBan?isBan=1&id=%{id}" cssClass="btn btn-default disabled">禁言</s:a> 
									<s:a action="userAction_updateUserIsBan?isBan=0&id=%{id}" cssClass="btn btn-default">解除</s:a>  
									</s:else>
									 <s:a	action="userAction_resetUserPassword?id=%{id}" onClick="return confirm('确定要重置密码吗？')"	cssClass="btn btn-default">重置密码</s:a> 
									  
									 <s:if test="id != currentUser.id && currentUser.role.name=='超级管理员'">   
											 <s:if test="role.name=='普通用户'"> 
											 <s:a action="userAction_updateUserRole?id=%{id}&roleId=2" onClick="return confirm('确定要设置%{username}为管理员吗？')"	 cssClass="btn btn-info">设为管理员</s:a>
											 </s:if> 
											 <s:elseif test="role.name=='管理员'">
											 <s:a action="userAction_updateUserRole?id=%{id}&roleId=3" onClick="return confirm('确定要取消%{username}的管理员权限吗？')"	 cssClass="btn btn-danger">取消管理员</s:a>
											 </s:elseif>    
									 </s:if>  
									 
									 </s:if>
							
									 </td>
								</tr>
							</s:iterator>
 
						</tbody>
					</table>

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
