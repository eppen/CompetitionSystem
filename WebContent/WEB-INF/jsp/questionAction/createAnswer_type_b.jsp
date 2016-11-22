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
<link href="<%=basePath%>css/dashboard.css" rel="stylesheet" />

<!-- 禁用响应式布局 -->

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
	<%@ include file="/WEB-INF/jsp/public/nav.jspf"%> 
	<!--   	导航条结束    -->

	<!-- 中间内容开始 -->
	<div class="container-fluid">
		<div class="row">
			<!-- 侧边栏 -->
			<%@ include file="/WEB-INF/jsp/public/leftMenu.jspf"%>

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<h2 class="sub-header">添加试题</h2>
				<div class="table-responsive">
					<form action="questionAction_createQuestionAnswer" class="form-horizontal"
						role="form" method="post">

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
						</table>
						<button disabled="false" id="btn_submit" type="submit" class="btn btn-lg btn-success btn-block">确认</button> 
					</form>
				</div>
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
