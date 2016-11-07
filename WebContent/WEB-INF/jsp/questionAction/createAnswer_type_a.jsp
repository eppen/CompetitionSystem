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
					<a href="#">暗箱操作</a>
				</li>
				<li class="alert-warning">
					<a href="createQuestion.html">创建试题集</a>
				</li>
				<li class="alert-warning">
					<a href="createQuestionSet.html">创建试题</a>
				</li>
			</ul>
		</div>

		<!-- 内容 --> 
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

			<h2 class="sub-header">添加试题</h2>
			<div class="table-responsive">
				<form action="topicManagerList.html" class="form-horizontal" role="form">

					<table class="table table-responsive">
					<thead>
						<tr>
							<th class="col-md-9 col-lg-9 col-sm-9">输出答案</th>
							<th class="col-md-3 col-lg-3 col-sm-3">此项分数</th>
						</tr>
					</thead>

					<body>
					<th>
					</th>
					<tr class="alert alert-dismissable">
						<td>
								<input type="text" style="font-size: 18px;font-family: '微软雅黑';width: 100%;" name="title" class="form" value="5050"/>
						</td>
						<td>
								<input disabled type="text" style="font-size: 18px;font-family: '微软雅黑';width: 100%;" name="title" class="form" value="100"/>
						</td>
					</tr>
				
					</body>
				</table>
					<button class="btn btn-lg btn-primary btn-block" type="submit">确认</button>
				</form>
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
