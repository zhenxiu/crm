<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="pagination" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户开发计划</title>
</head>

<body class="main">
	<form action="${ctx}/plan/chance/list" method="post">
		<div class="page_title">
			客户开发计划
		</div>
		<div class="button_bar">
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
		<br />
		
		<c:if test="${page.numberOfElements == 0}">
			当前没有用户相关数据
		</c:if>
		<c:if test="${page.numberOfElements != 0}">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					
					<th>
						编号
					</th>
					<th>
						客户名称
					</th>
					<th>
						概要
					</th>
					<th>
						联系人
					</th>
					<th>
						联系人电话
					</th>
					<th>
						创建时间
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
				
				<c:forEach items="${page.content}" var="chance">
					<tr>
						<td class="list_data_number">
							${chance.id}
						</td>
						<td class="list_data_text">
							${chance.custName}
						</td>
						<td class="list_data_text">
							${chance.title }
						</td>
						<td class="list_data_text">
							${chance.contact}
						</td>
						<td class="list_data_text">
							${chance.contactTel}
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${chance.createDate}" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_text">
							<script type="text/javascript">
								switch('${chance.status}')
								{
									case '2':
										document.write('开发中');
										break;
									case '3':
										document.write('开发成功');
										break;
									case '4':
									    document.write('开发失败');
									   	break;
								}
							</script>
						</td>
						
						
						
						<td class="list_data_op">
							<c:if test='${chance.status=="2"}'>
								<img
									onclick="window.location.href='${ctx}/plan/list?chanceId=${chance.id}'"
									title="制定计划" src="${ctx}/static/images/bt_plan.gif" class="op_button" />
								<img
									onclick="window.location.href='${ctx}/plan/edit?chanceId=${chance.id}'"
									title="执行计划" src="${ctx}/static/images/bt_feedback.gif" class="op_button" />
								<img 
									onclick="window.location.href='${ctx}/chance/finish?chanceId=${chance.id}'"
									title="开发成功" src="${ctx}/static/images/bt_yes.gif" class="op_button" />
							</c:if>
							<c:if test='${chance.status!="2"}'>
								<img
									onclick="window.location.href='${ctx}/plan/detail?chanceId=${chance.id}'"
									title="查看" src="${ctx}/static/images/bt_detail.gif" class="op_button" />
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>	
		
		<pagination:pagination paginationSize="5" page="${page }"></pagination:pagination>
		
		</form>
</body>
</html>