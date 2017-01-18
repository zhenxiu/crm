<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pagination" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>销售机会管理</title>
	<script type="text/javascript">
		$(function(){
			$("#new").click(function(){
				window.location.href="${ctx}" + "/chance/create";
				return false;
			});
			
			$("img[id^='delete-']").click(function(){
				
				var $form = $(this).next("form");
				$form.submit();
				return false;
			});
		})
	</script>
</head>

<body class="main">
	<form id="command" action="${ctx}/chance/list" method="post">
		<div class="page_title">
			销售机会管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">
				新建
			</button>
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
		<!-- 列表数据 -->
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
					操作
				</th>
			</tr>
			<c:forEach items="${page.content}" var="salesChance">
				<tr>
					<td class="list_data_number">${salesChance.id}</td>
					<td class="list_data_text">${salesChance.custName}</td>
					<td class="list_data_text">${salesChance.title}</td>
					<td class="list_data_text">${salesChance.contact}</td>
					<td class="list_data_text">${salesChance.contactTel}</td>
					<td class="list_data_text">
						<fmt:formatDate value="${salesChance.createDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctx}/chance/dispatch/${salesChance.id}'" 
							title="指派" src="${ctx}/static/images/bt_linkman.gif" class="op_button" />
						<img onclick="window.location.href='${ctx}/chance/${salesChance.id}'" 
							title="编辑" src="${ctx}/static/images/bt_edit.gif"
							class="op_button" />
						<img title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" 
							id="delete-${salesChance.id }"/>
						<form action="${ctx }/chance/${salesChance.id}" method="post">
							<input type="hidden" name="_method" value="DELETE"/>
						</form>
					</td>
				</tr>
			</c:forEach>	
		</table>
	</c:if>
		
	<pagination:pagination paginationSize="5" page="${page }"></pagination:pagination>	
		
	</form>
	
</body>
</html>
