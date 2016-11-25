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

<!------------   计算总分 判断是否为100分 100分才可以提交  ------------>
<script type="text/javascript">
	// Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
	function OnInput(event) {
		//score_hint
		//score_waring
		var array = document.getElementsByName("fractionlist");
		var sum = 0;
		for (var i = 0; i < array.length; i++) {
			sum = parseFloat(sum) + parseFloat(array[i].value);
		}
		$('#score_hint').html("当前总分值:" + sum);
		if (sum != NaN || sum != null) {
			if (sum == parseFloat(100)) {
				//btn_submit
				$('#btn_submit').removeAttr("disabled");
				$('#score_waring').html("");
				$('#score_hint').attr("class", "alert alert-success");
			} else if (sum > parseFloat(100)) {
				$('#btn_submit').attr("disabled", true);
				$('#score_hint')
						.html(
								"当前总分值:"
										+ sum
										+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分数超出范围！");
				$('#score_hint').attr("class", "alert alert-danger");
			} else if (sum < parseFloat(100)) {
				$('#btn_submit').attr("disabled", true);
				$('#score_hint')
						.html(
								"当前总分值:"
										+ sum
										+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分数未达到100分！");
				$('#score_hint').attr("class", "alert alert-warning");
			} else {
				$('#btn_submit').attr("disabled", true);
			}

		}
	}
	// Internet Explorer
	function OnPropChanged(event) {
		/*  if (event.propertyName.toLowerCase () == "value") {
		      alert ("The new content: " + event.srcElement.value);
		  }*/
		var array = document.getElementsByName("fractionlist");
		var sum = 0;
		for (var i = 0; i < array.length; i++) {
			sum = parseFloat(sum) + parseFloat(array[i].value);
		}
		$('#score_hint').html("当前总分值:" + sum);
		if (sum != NaN || sum != null) {
			if (sum == parseFloat(100)) {
				//btn_submit
				$('#btn_submit').removeAttr("disabled");
				$('#score_waring').html("");
				$('#score_hint').attr("class", "alert alert-success");
			} else if (sum > parseFloat(100)) {
				$('#btn_submit').attr("disabled", true);
				$('#score_hint')
						.html(
								"当前总分值:"
										+ sum
										+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分数超出范围！");
				$('#score_hint').attr("class", "alert alert-danger");
			} else if (sum < parseFloat(100)) {
				$('#btn_submit').attr("disabled", true);
				$('#score_hint')
						.html(
								"当前总分值:"
										+ sum
										+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分数未达到100分！");
				$('#score_hint').attr("class", "alert alert-warning");
			} else {
				$('#btn_submit').attr("disabled", true);
			}

		}

	}
</script>

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
					<li id="nav2" class="navbar-btn"><a href="homeAction_toHelpUI">帮助文档<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="homeAction_toSystemNoticeUI">系统公告<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a
						href="homeAction_toCompetitionSystemUI">进入系统<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a
						href="testAnswerAction_toAnswerListUI">评测状态<span
							class="sr-only"></span></a></li>
					<li class="active navbar-btn"><a href="homeAction_toExchangeCentreUI">交流中心<span
							class="sr-only"></span></a></li>
					<li class="navbar-btn"><a href="homeAction_toContactInfoUI">联系方式<span
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

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-1 main">

				<!-- 路径 -->
				<ol class="breadcrumb">
					<li><a href="topicAction_toTopicListUI">交流中心 <span
							class="glyphicon glyphicon-chevron-right"></span></a><li class="active">发表新主题<span
						class="glyphicon glyphicon-chevron-right"></li> 
					</li>
					<li class="active">添加问题答案<span
						class="glyphicon glyphicon-chevron-right"></li>
					</li>
				</ol>

				<!--发表新主题开始-->
				<div class="QuictReply">
					<form action="topicAction_createTopicAnswer" method="post">
						<div style="padding-left: 3px;">
							<table class="table table-responsive">
								<thead>
									<tr>
										<td>
											<button id="btnAdd" type="button" class="btn btn-danger">添加选项</button>
										</td>
										<td><b><div id="score_hint"
													class="alert alert-warning">当前总分值:0分</div></b></td>
									</tr>
									<tr>
										<th class="col-md-5 col-lg-5 col-sm-5">输入值</th>
										<th class="col-md-5 col-lg-5 col-sm-5">输出答案</th>
										<th class="col-md-2 col-lg-2 col-sm-2">此项分数</th>
									</tr>
								</thead>

								<tbody id="mybody">
								<tr class="alert alert-dismissable">
									<td><input type="text"
										style="font-size: 18px; font-family: '微软雅黑'; width: 100%;"
										name="inputlist" class="form required" value="5.545" /></td>
									<td><input type="text"
										style="font-size: 18px; font-family: '微软雅黑'; width: 100%;"
										name="answerlist" class="form required" value="5.545" /></td>
									<td><input type="text"
										style="font-size: 18px; font-family: '微软雅黑'; width: 100%;"
										name="fractionlist" class="form required digits" oninput="OnInput (event)"
										onpropertychange="OnPropChanged (event)" value="0" /></td>
									<td><button type="button" class="close"
											data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button></td>
								</tr>

							</tbody> 
								<tr height="30" class="Tint">
									<td class="center" colspan="2" align="center">
										<button disabled="false" id="btn_submit" type="submit"
											class="btn btn-success">提交</button>
									</td>
								</tr>
							</table>
						</div>
					</form>
					<div style="height: 130px;"></div>
				</div>
				<!--发表新主题结束-->


			</div>
		</div>
	</div>
	<!-- 中间内容结束 -->

	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>

	<script type="text/javascript">
		$(function() {
			//按钮点击事件
			$("#btnAdd")
					.click(
							function() {
								//接收用户输入的列数据
								//构建新行
								var newRow = "<tr class=\"alert alert-dismissable\">"
										+ "<td>"
										+ "<input type=\"text\" style=\"font-size: 18px;font-family: '微软雅黑';width: 100%;\" name=\"inputlist\" class=\"form required\" value=\"5.545\"/>"
										+ "</td>"
										+ "<td>"
										+ "<input type=\"text\" style=\"font-size: 18px;font-family: '微软雅黑';width: 100%;\" name=\"answerlist\" class=\"form required\" value=\"5.545\"/>"
										+ "</td>"
										+ "<td>"
										+ "<input type=\"text\" style=\"font-size: 18px;font-family: '微软雅黑';width: 100%;\" name=\"fractionlist\" class=\"form required digits\" oninput=\"OnInput (event)\" onpropertychange=\"OnPropChanged (event)\" value=\"0\"/>"
										+ "</td>"
										+ "<td><button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\">"
										+ "<span aria-hidden=\"true\">&times;</span>"
										+ "</button></td>" + "</tr>";
								//为表格追加新行
								$("#mybody").append(newRow);
							});
		});
	</script>
</body>

</html>