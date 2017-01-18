<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户基本信息管理</title>
</head>
<body>

	<div class="page_title">客户基本信息管理</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">查询</button>
	</div>
	
	<form action="/crm_/customer/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>客户名称</th>
				<td>
					<input type="text" name="search_LIKE_name"/>
				</td>
				<th>地区</th>
				<td>
					<form:select path="search_EQ_region" items=""
									itemLabel="" itemValue="${regions }">
					</form:select>
					
					<!-- <select name="search_EQ_region">
						<option value="">全部</option>
						
							
							<option value="北京">北京</option>
						
							<option value="上海">上海</option>
						
							<option value="广州">广州</option>
						
							<option value="深圳">深圳</option>
						
							<option value="香港">香港</option>
						
							<option value="辽宁">辽宁</option>
						
					</select> -->
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>客户经理</th>
				<td><input type="text" name="search_LIKE_manager.name" /></td>
				
				<th>客户等级</th>
				<td>
					<select name="search_EQ_level">
						<option value="">全部</option>
						
							<option value="普通客户">普通客户</option>
						
							<option value="大客户">大客户</option>
						
							<option value="重点开发客户">重点开发客户</option>
						
							<option value="合作伙伴">合作伙伴</option>
						
							<option value="战略合作伙伴">战略合作伙伴</option>
						
					</select>
				</td>
				
				<th>状态</th>
				<td>
					<select name="search_EQ_state">
						<option value="">全部</option>
						<option value="正常">正常</option>
						<option value="流失">流失</option>
						<option value="删除">删除</option>					
					</select>
				</td>
			</tr>
		</table>
		
		<!-- 列表数据 -->
		<br />
		
		<c:if test="${empty chances}">
			当前没有客户的相关信息
		</c:if>
		<c:if test="${not empty chances}">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>客户编号</th>
					<th>客户名称</th>
					<th>地区</th>
					<th>客户经理</th>
					<th>客户等级</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				
				<c:forEach items="${chances}" var="customer">
					<tr>
						<td class="list_data_text">${customer.no }&nbsp;</td>
						<td class="list_data_ltext">${customer.name }&nbsp;</td>
						<td class="list_data_text">${customer.region }&nbsp;</td>
						<td class="list_data_text">${customer.manager.name }&nbsp;</td>
						<td class="list_data_text">${customer.level }&nbsp;</td>
						<td class="list_data_text">${customer.state }&nbsp;</td>
						<td class="list_data_op">
							<img onclick="window.location.href='/crm_/customer/create?id=141'"
								title="编辑" src="/crm_/static/images/bt_edit.gif" class="op_button" alt="" /> 
							<img onclick="window.location.href='/crm_/contact/list?customerid=141'"
								title="联系人" src="/crm_/static/images/bt_linkman.gif" class="op_button" alt="联系人信息" /> 
							<img onclick="window.location.href='/crm_/activity/list?customerid=141'"
								title="交往记录" src="/crm_/static/images/bt_acti.gif" class="op_button" alt="交往记录" /> 
							<img onclick="window.location.href='/crm_/order/list?customerid=141'"
								title="历史订单" src="/crm_/static/images/bt_orders.gif" class="op_button" alt="历史订单" /> 
								
									<img onclick="window.location.href='/crm_/customer/delete?id=141'" 
									title="删除" src="/crm_/static/images/bt_del.gif" class="op_button" alt="删除" />
								
							</td>					
					</tr>
				</c:forEach>
			</table>
		</c:if>
			








<div style="text-align:right; padding:6px 6px 0 0;">

	

	共 8 条记录 
	&nbsp;&nbsp;
	
	当前第 1 页/共 2 页
	&nbsp;&nbsp;
	
	
	 
	
		<a href='?page=2&sortType=&&'>下一页</a>
		&nbsp;&nbsp;
		<a href='?page=2&sortType=&&'>末页</a>
		&nbsp;&nbsp;
	
	
	转到 <input id="pageNo" size='1'/> 页
	&nbsp;&nbsp;

</div>

<script type="text/javascript" src="/crm_/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		$("#pageNo").change(function(){
			
			var pageNo = $(this).val();
			var reg = /^\d+$/;
			if(!reg.test(pageNo)){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			var pageNo2 = parseInt(pageNo);
			if(pageNo2 < 1 || pageNo2 > parseInt("2")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname
				+ "?page=" + pageNo2 + "&sortType=&&";
			
		});
	})
</script>
		
		
	</form>
	
</body>
</html>
