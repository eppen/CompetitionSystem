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
			<h2 class="sub-header">访客信息</h2>  
				<div class="row">
					<div class="row"> 

						<!-- 游客内容开始	-->
						<s:iterator value="visitors">
							<div class="col-md-2" style="margin-top: 20px">  
								<p>
									<s:a cssClass="thumbnail"
										action="userAction_toUserInfoUI?id=%{visitors.id}"
										target="_blank">
										<img src="img/userpic.jpg" />
										<span>${visitors.name }<br /> <s:date
												name="visitorTime" format="yyyy-MM-dd HH:mm:ss" />
										</span>
									</s:a>
								</p>
							</div>
						</s:iterator>
						<!-- 游客内容结束 -->

					</div>
				</div>

			</div>

		</div>
		<!-- 中间内容结束 -->

		<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>
</html>
