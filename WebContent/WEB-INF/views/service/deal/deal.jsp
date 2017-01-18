<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>处理客户服务</title>
	<script type="text/javascript">
		$(function(){
			$("#dealDate").val(new Date().format("yyyy-MM-dd"));
		})
	</script>
</head>

<body class="main">
	
	<span class="page_title">处理客户服务</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	
	<form:form action="${ctx}/service/deal" method="post" modelAttribute="customerService">
		<input type="hidden" name="id" value="${customerService.id }"/>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<td>${customerService.id }</td>
				<th>
					服务类型
				</th>
				<td>${customerService.serviceType }</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">${customerService.serviceTitle }</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>${customerService.customer.name }</td>
				<th>
					状态
				</th>
				<td>
					${customerService.serviceState }
				</td>
			</tr>
			<tr>
				<th>
					服务请求
				</th>
				<td colspan="3">${customerService.serviceRequest }</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>${customerService.createdby.name }</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${customerService.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</table>
		
		<br />
		<table class="query_form_table">
			<tr>

				<th>
					分配给
				</th>
				<td>${customerService.allotTo.name }</td>
				<th>
					分配时间
				</th>
				<td>
					<fmt:formatDate value="${customerService.allotDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</table>
		
		<br />
		<table class="query_form_table">
			<tr>
				<th>
					服务处理
				</th>
				<td colspan="3">
					<textarea name="serviceDeal" rows="6" cols="50"></textarea>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					处理人
				</th>
				<td>
					${user.name }(${user.role.name })
					<span class="red_star">*</span>
				</td>
				<th>
					处理时间
				</th>
				<td>
					<input name="dealDate" readonly="readonly" id="dealDate" />
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
    