<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="题库">
    	<meta name="author" content="黄跃然 huangyueran">
		<title>练习系统</title>
		<link href="css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/bootstrap-theme.min.css" rel="stylesheet" />
		<link href="css/sticky-footer.css" rel="stylesheet"/>
		<link href="css/dashboard.css" rel="stylesheet"/>
		<link href="css/basic.css" rel="stylesheet"/>
		<!-- 禁用响应式布局 -->
	</head>
	<body>
		
	<!--   	导航条开始    -->
    <nav class="nav navbar-inverse navbar-fixed-top">
			<div class="container">
				
				<div class="navbar-header">
					<!-- 	描述：logo -->
					<a class="navbar-brand">
						<img src="img/ic_launcher.jpg" style="width: 75px;" />
					</a>
				</div>
				
				<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-btn center-block text-center">
					<li class="active navbar-btn">
						<a href="index.html">首页<span class="sr-only"></span></a>
					</li>
					<li class="navbar-btn">
						<a href="help.html">帮助文档<span class="sr-only"></span></a>
					</li>
					<li class="navbar-btn">
						<a href="#">系统公告<span class="sr-only"></span></a>
					</li>
					<li class="navbar-btn">
						<a href="practiceHome.html">进入系统<span class="sr-only"></span></a>
					</li>
					<li class="navbar-btn">
						<a href="topicHome.html">交流中心<span class="sr-only"></span></a>
					</li>
					<li class="navbar-btn">
						<a href="#">联系方式<span class="sr-only"></span></a>
					</li>
				</ul>
					
					<ul class="nav navbar-nav navbar-right">
					<li class="dropdown navbar-btn">
						<a class="dropdown-toggle" data-toggle="dropdown" role="button">
							<span class="glyphicon glyphicon-user"></span>
							admin<span class="caret"></span>&nbsp;<span class="badge" style="background-color: #FF5400;">12</span>
						</a>
						<ul class="dropdown-menu" role="menu">
								<li><a href="userCenter.html">个人信息</a></li>
							<li><a href="userAlerts.html">我的消息&nbsp;<span class="badge" style="background-color: #FF5400;">12</span></a></li>
							<li><a href="updatePassword.html">修改密码</a></li>
							<li class="divider"></li>
							<li><a href="login.html">退出登录</a></li>
						</ul>
					</li>
					</ul>
					
					<ul class="nav navbar-nav navbar-right">
					<a href="login.html" class="btn btn-default navbar-btn">登录</a>
					</ul>
				</div>
				
			</div>
		</nav>
<!--   	导航条结束    -->

<!-- 中间内容开始 -->
<div class="container-fluid">
	<div class="row">
		<!-- 侧边栏 -->
		<div class="col-sm-3 col-md-2 sidebar collapse" style="position: absolute;top: 70px;">

			<ul class="nav nav-sidebar">
				<li>
					<a href="userCenter.html">个人信息</a>
				</li>
				<li>
					<a href="updateUser.html">用户信息修改</a>
				</li>
				<li>
					<a href="updatePassword.html">修改密码</a>
				</li>
				<li>
					<a href="userAlerts.html">我的提醒&nbsp;<span class="badge" style="background-color: #FF5400;">12</span></a>
				</li>
				<li>
					<a href="userTopic.html">我的主题</a>
				</li>
				<li>
					<a href="userFavorite.html">我的收藏</a>
				</li>
				<li>
					<a href="visitor.html">访客记录&nbsp;<span class="badge">25</span></a>
				</li>
				<li class="alert-warning">
					<a href="userManagerList.html">用户管理</a>
				</li>
				<li class="active alert-warning">
					<a href="addUser.html">添加用户</a>
				</li>
				<li class="alert-warning">
					<a href="topicManagerList.html">主题管理</a>
				</li>
				<li class="alert-warning">
					<a href="createQuestion.html">试题管理</a>
				</li>
				<li class="alert-warning">
					<a href="#">暗箱操作</a>
				</li>
			</ul>
		</div>

		<!-- 内容 --> 
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h2 class="sub-header">试题管理</h2>
			<div class="table-responsive">
					<a href="questionAction_toCreateQuestionUI" class="btn btn-success">创建试题</a>
					<table class="table table-hover">
							<thead>
								<tr>
									<th>试题号</th>
									<th>试题名称</th>
									<th>试题说明</th>
									<th>语言</th>
									<th>试题类型</th>
									<th>创建时间</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>ALGO-1</td>
									<td><a href="question.html">幸运数</a></td>
									<td>幸运数</td>
									<td>JAVA</td>
									<td>程序设计</td>
									<td>2017-05-05</td>
									<td><a href="question.html">修改</a></td>
									<td><a href="#">删除</a></td>
									<td><a href="#">进入</a></td>
								</tr>
							</tbody>
					</table>
						
						
						<!-- 分页开始 -->
										<p></p>
										<div class="container-fluid">
											页次：1/13页 &nbsp; 每页显示：30条 &nbsp; 总记录数：385条 &nbsp;
										</div>
										<center class="center">
										<ul class="pagination">
											<li class="disabled">
												<a href="#" aria-label="previous">
													<span aria-hidden="true">&laquo;</span>
												</a>
											</li>
									
											<li class="active">
												<a href="#">1</a>
											</li>
											<li>
												<a href="#">2</a>
											</li>
											<li>
												<a href="#">3</a>
											</li>
											<li>
												<a href="#">4</a>
											</li>
											<li>
												<a href="#">5</a>
											</li>
											<li>
												<a href="#">6</a>
											</li>
											<li>
												<a href="#">7</a>
											</li>
											<li>
												<a href="#">8</a>
											</li>
											<li>
												<a href="#">9</a>
											</li>
											<li>
												<a href="#">10</a>
											</li>
									
											<li>
												<a href="#" aria-label="next">
													<span aria-hidden="true">&raquo;</span>
												</a>
											</li>
																						<!--转到： <select id="pn" onchange="gotoPage(this.value)">
														<s:iterator begin="1" end="%{pageCount }" var="num">
															<option value="${num }">${num }</option>
														</s:iterator>
												</select>-->
										&nbsp;&nbsp;
										转到： 	<select id="pn" class="btn btn-danger" onchange="gotoPage(this.value)">
														
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
		
		

<!--   	底部开始    -->
<div style="height: 50px;"></div>
	<footer class="navbar-inverse navbar-fixed-bottom navbar-collapse collapse">
    <!--采用container，使得页尾内容居中 --> 
        <div class="container" style="color: #FFFFFF;height: 70px;">
        	2017 &copy; 练习系统 <a
				href="http://user.qzone.qq.com/492736173/">技术支持:Hyr</a>
        </div>
     </footer>
<!-- 底部结束 -->

		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js">
		</script>
	</body>
</html>
