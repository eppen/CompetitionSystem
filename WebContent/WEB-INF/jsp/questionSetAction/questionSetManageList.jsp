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
	<nav class="nav navbar-inverse navbar-fixed-top">
		<div class="container">

			<div class="navbar-header">
				<!-- 	描述：logo -->
				<a class="navbar-brand"> <img src="img/ic_launcher.jpg"
					style="width: 75px;" />
				</a>
			</div>

			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-btn center-block text-center">
					<li class="active navbar-btn"><a href="index.html">首页<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="help.html">帮助文档<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="#">系统公告<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="practiceHome.html">进入系统<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="topicHome.html">交流中心<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="#">联系方式<span
							class="sr-only"></span></a></li>
				</ul>

				<%@ include file="/WEB-INF/jsp/public/userMenu.jspf"%>
			</div>

		</div>
	</nav>
	<!--   	导航条结束    -->

	<!-- 中间内容开始 -->
	<div class="container-fluid">
		<div class="row">
			<!-- 侧边栏 -->
			<%@ include file="/WEB-INF/jsp/public/leftMenu.jspf"%>

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">试题管理</h2>
				<div class="table-responsive">
					<a href="questionSetAction_toCreateQuestionSetUI" class="btn btn-success">创建试题集</a>
					<table class="table table-hover"> 
						<thead>
							<tr>
								<th>试题集名称</th>
								<th>描述</th>
								<th>试题总数</th>
								<th>更新时间</th>
								<th></th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody> 
						<!-- 求总题数 --> 
						<s:set var="count" value="0" />
							<s:iterator value="recordList">
								<tr>
									<td><s:a href="questionAction_toQuestionListUI?questionSetId=%{id}">${title}</s:a></td>
									<td>${description}</td>  
									<td>${questionCount}</td> 
									<td><s:date name="updateTime" format="yyyy年MM月dd日" /></td>
									<td><a href="rumenPratice.html">修改</a></td>
									<td><a href="rumenPratice.html">删除</a></td>
									<td><a href="rumenPratice.html">进入</a></td>
								</tr>
								 <s:set var="count" value="#count+questionCount" />
							</s:iterator>

						</tbody>
					</table>
					<tr>
						<td>试题总数：<s:property value="#count"/></td>
					</tr>


					<!-- 分页开始 -->
					<p></p>
					<div class="container-fluid">页次：1/13页 &nbsp; 每页显示：30条 &nbsp;
						总记录数：385条 &nbsp;</div>
					<center class="center">
						<ul class="pagination">
							<li class="disabled"><a href="#" aria-label="previous">
									<span aria-hidden="true">&laquo;</span>
							</a></li>

							<li class="active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">6</a></li>
							<li><a href="#">7</a></li>
							<li><a href="#">8</a></li>
							<li><a href="#">9</a></li>
							<li><a href="#">10</a></li>

							<li><a href="#" aria-label="next"> <span
									aria-hidden="true">&raquo;</span>
							</a></li>
							<!--转到： <select id="pn" onchange="gotoPage(this.value)">
														<s:iterator begin="1" end="%{pageCount }" var="num">
															<option value="${num }">${num }</option>
														</s:iterator>
												</select>-->
							&nbsp;&nbsp; 转到：
							<select id="pn" class="btn btn-danger"
								onchange="gotoPage(this.value)">

								<option value="${num }">1</option>
								<option value="${num }">2</option>
								<option value="${num }">3</option>
								<option value="${num }">4</option>
								<option value="${num }">5</option>

							</select>
						</ul>

					</center>
					<!-- 分页结束 -->

				</div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>
