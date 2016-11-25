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
					<li class="navbar-btn"><a href="homeAction_toIndex">首页<span
							class="sr-only"></span></a></li>
					<li id="nav2" class="active navbar-btn"><a
						href="homeAction_toHelpUI">帮助文档<span class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="homeAction_toSystemNoticeUI">系统公告<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a
						href="homeAction_toCompetitionSystemUI">进入系统<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a
						href="testAnswerAction_toAnswerListUI">评测状态<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="homeAction_toExchangeCentreUI">交流中心<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="homeAction_toContactInfoUI">联系方式<span
							class="sr-only"></span></a></li>
				</ul>

				<%@ include file="/WEB-INF/jsp/public/userMenu.jspf"%>
			</div>

		</div>
	</nav>
	<!--   	导航条结束    -->

	<div class="container jumbotron">
		<div class="sec_header">系统适用范围</div>
		<div class="sec_cont">
			<p>练习系统只是针对软件类报名院校和学生使用（目前还未推出电子类的模拟试题）。</p>
			<p>账号的登陆：登陆的账号和密码即大赛官网的学院账号和学生账号。</p>
		</div>

		<div class="sec_header">系统介绍</div>
		<div class="sec_cont">
			<p>本系统面向参加"蓝桥杯"全国软件和信息技术专业人才大赛的老师和同学。</p>
			<p>本系统将帮助参赛的同学熟悉比赛试题的形式和解题方式。通过使用本系统，同学可以学习到一些重要的编程方法，提高自己的编程水平，在大赛中取得更好的名次，提升自己的竞争力。</p>
			<p>本系统的特点：</p>
			<p>
				1. <b>入门引导</b>：设置入门引导试题，帮助同学了解比赛的命题形式和解题方法。
			</p>
			<p>
				2. <b>试题分组</b>：具有相同难度和特点的试题形成一组，同组的试题具有相关性，帮助同学学习与提高程序设计、算法、数据结构的知识。
			</p>
			<p>
				3. <b>不断更新的试题</b>：系统不定期更新试题，保证同学的训练量。
			</p>
			<p>
				4. <b>锦囊</b>：每个试题配备两个锦囊，从做题思路、解题方法两种维度帮助同学学习新的知识，解决所面临的困难。
			</p>
			<p>
				5. <b>查看评测数据</b>：VIP用户允许查看评测数据，帮助你更有效的练习和提高。
			</p>
			<p>
				6. <b>比赛环境</b>：使用和软件大赛相同的测试环境进行测试，有效的模拟大赛的评测。
			</p>
			<p>
				7. <b>即时评测</b>：提交答案后马上进行评测并给出评测结果，方便同学了解自己程序的不足，对自己的程序进行改进。
			</p>
		</div>

		<div style="display: none">
			<div class="sec_header">院校使用指南</div>
			<div class="sec_cont">
				<p>
					1. <b>登录</b>：院校使用蓝桥杯官网注册的账号，点击"院校登录"进入首页。
				</p>
				<p>
					2. <b>授权学生</b>：点击用户管理，在未授权学生名单下选择授权学生，获得授权的学生方可答题，一旦授权将无法修改授权学生，已授权学生可在已授权名单查看；已授权学生答题状态及答题排名情况将在用户管理状态中体现。
				</p>
				<p>
					3. <b>查看答题情况</b>：点击用户管理，在已授权的学生管理中，可以查看每位学生的做题情况，点击做题数量的"查看详情"查看学生每道题的完成情况，点击在"提交记录"中查看学生的交题记录。
				</p>
				<p>
					4. <b>增加授权或升级为VIP</b>：点击个人中心，可以增加授权或者升级为VIP。
				</p>
				<p>
					5. <b>授权数量</b>：默认学生授权数量为20名，额外增加授权权限数量有2种方式：(1)根据额外授权用户数量缴纳相对应的费用。
					(2)根据缴纳报名费情况确定报名人数，给予相应的额外授权权限数量。
				</p>
			</div>
		</div>

		<div>
			<div class="sec_header">学生使用指南</div>
			<div class="sec_cont">
				<p>
					1. <b>登录</b>：学生使用蓝桥杯官网注册的账号，点击"学生登录"进入首页。
				</p>
				<p>
					2. <b>查看试题</b>：点击菜单中的试题集查看试题列表。若点击后显示无法查看试题的提示，你需要联系本校负责软件大赛老师登陆学院账号给予授权，授权成功后，方可看题。
				</p>
				<p>
					3. <b>答题</b>：在试题集中点击相应的试题进入看题和答题界面。答题时一般使用你自己熟悉的编辑器（如Dev-CPP或Eclipse）先行编写代码，调试通过后点击试题页面的"提交此题"将代码粘贴到代码框中提交答案。
				</p>
				<p>
					4. <b>答案正确性</b>：提交代码后将转向"评测状态"页面，你也可以直接点击菜单中的"评测状态"进入。系统一般会在几秒内将评测你提交的代码，并给出得分。点击"查看详情"可查看每个测试点的得分。请注意，试题中的样例数据只是一个简单的例子，通过样例数据不代表你的程序是正确的，有可能你的程序因为在其他评测数据上是错的而得分较低，请认真调试和验证你自己的程序。
				</p>
				<p>
					5. <b>获取评测数据</b>：VIP用户可以获取每道题25%的评测数据。点击提交的"查看详情"页，在对应测试点中点击下载对应评测点的输入和输出数据。
				</p>
				<p>
					6. <b>成为VIP用户</b>：VIP用户可以查看更多的试题，也可以查看试题的部分评测数据。本系统以院校为单位管理VIP用户，若要升级为VIP用户需要联系院校的负责人进行付费。
				</p>
			</div>
		</div>

		<div id="vip" class="sec_header">VIP特权有哪些？</div>
		<div class="sec_cont">
			<p>VIP用户的特权主要包括两方面：</p>
			<p>1. 部分试题是VIP用户的专享试题，只有VIP用户可以查看。</p>
			<p>2. 对于每道试题，可以在提交代码后查看所有评测数据，而其他用户只能查看第一个。</p>
		</div>

		<div class="sec_header">怎样成为VIP用户？</div>
		<div class="sec_cont">
			本系统以院校为单位管理VIP用户，若要升级为VIP用户需要联系院校的负责人进行付费。院校负责人在个人中心可以进行此项操作。</div>

		<div class="sec_header">编辑环境</div>
		<div class="sec_cont">
			<p>我们建议您使用大赛指定的编辑环境来编写你的代码，以保证评测时和我们的编译环境一致，同时和比赛时使用的环境也一致。</p>
			<p>推荐的编辑环境如下：</p>

			<table class="table" style="text-indent: 0;">
				<thead>
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				</thead>
				<tr>
					<th class="thodd">语言</th>
					<th class="theven">编辑环境</th>
					<th class="thodd">下载地址</th>
				</tr>
				<tr>
					<td>C++</td>
					<td>Dev-CPP</td>
					<td><a href='http://sourceforge.net/projects/orwelldevcpp/'
						target="_blank">SourceForge上的Dev-CPP</a></td>
				</tr>
				<tr>
					<td>C</td>
					<td>Dev-CPP</td>
					<td><a href='http://sourceforge.net/projects/orwelldevcpp/'
						target="_blank">SourceForge上的Dev-CPP</a></td>
				</tr>
				<tr>
					<td>JAVA</td>
					<td>Eclipse</td>
					<td><a href='http://www.eclipse.org/downloads/'
						target="_blank">Eclipse</a></td>
				</tr>
			</table>
		</div>

		<div class="sec_header">编译环境</div>
		<div class="sec_cont">
			对于不同的语言，系统的编译环境如下：

			<table class="table" style="text-indent: 0;">
				<thead>
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				</thead>
				<tr>
					<th class="thodd">语言</th>
					<th class="theven">编译环境</th>
					<th class="thodd">编译选项</th>
				</tr>
				<tr>
					<td>C++</td>
					<td>g++ (GCC) 4.7.2</td>
					<td>g++ code.cpp -O2 -Wl,--stack=268435456 -DONLINE_JUDGE</td>
				</tr>
				<tr>
					<td>C</td>
					<td>gcc (GCC) 4.7.2</td>
					<td>gcc code.c -O2 -Wl,--stack=268435456 -DONLINE_JUDGE</td>
				</tr>
				<tr>
					<td>JAVA</td>
					<td>Javac 1.6.0_07</td>
					<td>javac Main.java</td>
				</tr>
			</table>
		</div>

		<div class="sec_header">JAVA主类名称</div>
		<div class="sec_cont">
			如果你使用Java语言，对于所有的试题，你的主类名称都必须是<b>Main</b>。
		</div>

		<div class="sec_header">C/C++语言返回值</div>
		<div class="sec_cont">
			C/C++语言中，主函数main的返回值类型必须是<b>int</b>，返回值必须是<b>0</b>，否则评测会认为程序运行错误。
		</div>

		<div class="sec_header">C/C++中怎样使用64位整数？</div>
		<div class="sec_cont">
			<p>64位整数的类型为：long long</p>
			<p>使用cin读的操作为：cin &gt;&gt; x;</p>
			<p>使用cout写的操作为：cout &lt;&lt; x;</p>
			<p>使用scanf读的操作为：scanf("%I64d", &x);</p>
			<p>使用printf写的操作为：printf("%I64d", x);</p>
		</div>

		<div class="sec_header">为什么我的程序过了样例却没有得100分？</div>
		<div class="sec_cont">
			在试题中，样例只是满足问题描述的一组可能的输入和与其对应的输出。程序对于样例运行正确只表示在这一种情况下是正确的，可能在更复杂的情况下是错误的。在评测的时候，评测系统会使用多种不同的输入对程序进行评测，只有通过了所有的数据才会认为程序是正确的。
		</div>

		<div style="display: none">
			<div id="pay" class="sec_header">缴费方式</div>
			<div class="sec_cont" style="text-indent: 0;">
				您可以使用下面的方式缴费：<br /> （1）<b>在线缴费</b>：选择"支付宝"，按提示缴费即可，实时到账，无手续费。<br />
				<br /> （2）<b>邮局汇款</b>：通过邮局汇款，到账时间根据各地情况不同，为一至两周，邮局需收取相应汇款手续费。汇款后请登陆系统提交支付凭证。<br />
				汇款信息：<span style="text-decoration: underline;">北京市海淀区万寿路27号院18#信箱，刘佳，100846</span>。<br />
				请在汇款单附言中标注学校全称，以便核对查询。<br /> <br /> （3）<b>银行转账</b>：只接受学校公对公转账，到账时间根据银行不同，为1至7天，银行需收取相应转账手续费。汇款后请登陆系统提交支付凭证。<br />
				银行账号：<span style="text-decoration: underline;">国信世纪人才服务（北京）有限公司</span><br />
				开户银行：<span style="text-decoration: underline;">交通银行北京翠微路支行</span><br />
				账户：<span style="text-decoration: underline;">110060836018150038530</span><br />
				<br /> 请各校在付款之前务必确认本校的财务制度，如规定只允许公对公转账，则勿使用在线缴费和邮局汇款的方式。
			</div>
		</div>

		<div class="sec_header">联系方式</div>
		<div class="sec_cont" style="text-indent: 0;">
			全国组委会联系方式如下：

			<table class="table">
				<thead>
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				<col class="odd" />
				<col class="even" />
				</thead>
				<tr>
					<th class="thodd">联系人</th>
					<th class="theven">电话</th>
					<th class="thodd">电子邮箱</th>
				</tr>
				<tr>
					<td>单老师</td>
					<td>4006-588-662 按1 转1</td>
					<td>shbj@lanqiao.org</td>
				</tr>
				<tr>
					<td>刘老师</td>
					<td>4006-588-662 按1 转2</td>
					<td>lcc@lanqiao.org</td>
				</tr>
				<tr>
					<td>潘老师</td>
					<td>4006-588-662 按1 转3</td>
					<td>panch@lanqiao.org</td>
				</tr>
				<tr>
					<td>郑老师</td>
					<td>4006-588-662 按1 转4</td>
					<td>zhengj@lanqiao.org</td>
				</tr>
				<tr>
					<td>李老师</td>
					<td>4006-588-662 按1 转5</td>
					<td>ljx@lanqiao.org</td>
				</tr>
				<tr>
					<td>投诉建议</td>
					<td>4006-588-662 按1 转6</td>
					<td>advice@lanqiao.org</td>
				</tr>
			</table>

			<b>工作时间</b>：周一至周五，9:00—11:30,13:30—17:30<br /> <b>地 址</b>：北京市海淀区万寿路27号工业和信息化部机关8号楼18#信箱
			<br /> <b>邮 编</b>：100846<br /> <b>传 真</b>：010-68208666<br /> <b>官方QQ群</b>：103435050
			(2000人，只限参赛院校教师加入)
		</div>

	</div>
	</div>


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>

</body>
</html>
