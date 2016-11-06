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
					<li class="active navbar-btn"><a href="homeAction_toIndex">首页<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="homeAction_toHelpUI">帮助文档<span
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

	<div class="container jumbotron">
		<h1>
			欢迎进入练习系统<small>软件和信息技术人才大赛练习系统</small>
		</h1>
		<p>本系统面向参加各类全国软件和信息技术专业人才大赛的老师和同学。</p>

		<p>本系统将帮助参赛的同学熟悉比赛试题的形式和解题方式。通过使用本系统，同学可以学习到一些重要的编程方法，提高自己的编程水平，在大赛中取得更好的名次，提升自己的竞争力。</p>

		<p>本系统的特点：</p>

		<p>1. 入门引导：设置入门引导试题，帮助同学了解比赛的命题形式和解题方法。</p>

		<p>2. 试题分组：具有相同难度和特点的试题形成一组，同组的试题具有相关性，帮助同学学习与提高程序设计、算法、数据结构的知识。</p>

		<p>3. 不断更新的试题：系统不定期更新试题，保证同学的训练量。</p>

		<p>4. 锦囊：每个试题配备两个锦囊，从做题思路、解题方法两种维度帮助同学学习新的知识，解决所面临的困难。</p>

		<p>5. 查看评测数据：VIP用户允许查看评测数据，帮助你更有效的练习和提高。</p>

		<p>6. 比赛环境：使用和软件大赛相同的测试环境进行测试，有效的模拟大赛的评测。</p>

		<p>7. 即时评测：提交答案后马上进行评测并给出评测结果，方便同学了解自己程序的不足，对自己的程序进行改进。</p>

	</div>



	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>

</body>
</html>
