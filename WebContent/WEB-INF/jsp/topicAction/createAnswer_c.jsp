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
	<%@ include file="/WEB-INF/jsp/public/nav.jspf"%>
	<!--   	导航条结束    -->

	<!-- 中间内容开始 -->
	<div class="container-fluid">
		<div class="row">

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-1 main">

				<!-- 路径 -->
				<ol class="breadcrumb">
					<li><a href="topicAction_toTopicListUI">交流中心 <span
							class="glyphicon glyphicon-chevron-right"></span></a>
					<li class="active">发表新主题<span
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
									</tr>
									<tr>
										<th class="col-md-12 col-lg-12 col-sm-12">输入值</th>
									</tr>
								</thead>

								<tbody id="mybody">
									<tr class="alert alert-dismissable">
										<td><input type="text"
											style="font-size: 18px; font-family: '微软雅黑';"
											answerlist" class="form-control required" value="int a=b;" /></td>
										<s:hidden name="fractionlist" value="100"></s:hidden>
										<td><button type="button" class="close"
												data-dismiss="alert" aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button></td>
									</tr>
								</tbody>
								<tr height="30" class="Tint">
									<td class="center" colspan="2" align="center">
										<button id="btn_submit" type="submit" class="btn btn-success">提交</button>
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
								var newRow = "<tr class='alert alert-dismissable'><td><input type='text' style=\"font-size: 18px;font-family: '微软雅黑';width: 100%;\" name='answerlist' class='form-control required' value='int a=b;'/></td><td><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></td></tr>";
								//为表格追加新行 
								$("#mybody").append(newRow);
							});
		});
	</script>
</body>

</html>