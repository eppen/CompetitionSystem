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
	<div class="container">
		<div class="row">

			<!-- 内容 -->
			<div class="col-sm-9 col-sm-offset-1 col-md-10 col-md-offset-1 main">

				<!-- 路径 -->
				<ol class="breadcrumb">
					<li>
					<li class="active">交流中心<span
						class="glyphicon glyphicon-chevron-right"></li>
					</li>
				</ol>

				<div class="container">
					<a href="topicAction_toCreateTopicUI" class="btn btn-success">发表新主题</a>
				</div>

				<div class="table-responsive">

					<!------- 搜索框 ------->
					<s:form id="pageForm" action="topicAction_toAnswerListUI"
						role="search">
						<div class="form-group navbar-form navbar-left">
							<s:textfield type="text" name="titleSearch"
								cssClass="form-control" placeholder="请输入主题名称" />
							<button type="submit" class="btn btn-danger">搜索</button>
						</div>

						<table class="table table-hover table-condensed">
							<thead>
								<tr>
									<th width="50" class="ForumPageTableTitle">
										<!--状态/图标-->&nbsp;
									</th>
									<th>主题</th>
									<th>作者</th>
									<th>回复数</th>
									<th>最后回复</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="recordList">
									<tr>
										<td><img src="<%=basePath%>img/topicType_${classify}.gif" /><a
											href="rumenPratice.html"></td>
										<td><s:a action="topicAction_toTopicShowUI?id=%{id}">${title }</s:a></td>
										<td style="font-size: 1px;">
											<ul>
												<li class="list-unstyled Author">${author.name }</li>
												<li class="list-unstyled CreateTime"><s:date
														name="postTime" format="yyyy年MM月dd日 hh:mm:ss" /></li>
											</ul>
										</td>
										<td class="center">${replyCount }</td>
										<td style="font-size: 1px;">
											<ul>
												<li class="list-unstyled Author"><s:if
														test="lastReply.author.name!=null"> 
											${lastReply.author.name }
											</s:if> <s:else>
											${author.name }
											</s:else></li>
												<li class="list-unstyled CreateTime">
												<li class="list-unstyled CreateTime"><s:date
														name="lastUpdateTime" format="yyyy年MM月dd日 hh:mm:ss" /></li>
											</ul>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>

						<!--  其他功能  -->
						<table>
							<tr valign=bottom>
								<td></td>
								<td>
									<%-- 使用自定义标签，以便于回显数据 --%> <s:select cssClass="btn btn-default"
										name="viewType" list="%{  #{0:'全部主题', 1:'全部精华贴',2:'全部置顶贴'}  }" />
									<s:select cssClass="btn btn-default" name="orderBy"
										onchange="onSortByChange(this.value)"
										list="%{ #{0:'默认排序(所有置顶帖在前面，并按最后更新时间降序排列)', 1:'只按最后更新时间排序', 2:'只按主题发表时间排序', 3:'只按回复数量排序'} }" />
									<s:select cssClass="btn btn-default" name="asc"
										list="%{ #{false:'降序', true:'升序'} }" />
									<button type="submit" class="btn btn-warning">提交</button>
								</td>
							</tr>
						</table>
					</s:form>

					<!-- 分页开始 -->
					<%@include file="/WEB-INF/jsp/public/pageView.jspf"%>
					<!-- 分页结束 -->

				</div>
				<div class="container alert-warning">
					说明：<br /> 1，普通用户只能进行基本操作<br /> 2，管理员可以进行“编辑”、“删除”、“精华”、“置顶”的操作。<br />
					3，贴子作者可以进行“删除“或”编辑“操作<br />
				</div>
				<div style="height: 40px"></div>
			</div>

		</div>

	</div>
	<!-- 中间内容结束 -->


	<%@ include file="/WEB-INF/jsp/public/footer.jspf"%>
</body>

</html>