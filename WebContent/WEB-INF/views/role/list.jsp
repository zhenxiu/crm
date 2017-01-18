<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>角色管理</title>
</head>

<body class="main">

	<div class="page_title">
		角色管理
	</div>
	
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctx}/role/input'">
			新建
		</button>
	</div>
	
	<form action="role-list">

		<!-- 列表数据 -->
		<br />
		
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th class="data_title" >
						编号
					</th>
					<th class="data_title" >
						角色名
					</th>
					<th class="data_title" >
						角色描述
					</th>
					<th class="data_title">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
				
				
					<tr>
						<td class="data_cell" style="text-align:right;padding:0 10px;">3</td>
						<td class="data_cell" style="text-align:center;">测试管理员</td>
						<td class="data_cell" style="text-align:left;">测试时使用, 上线需删除</td>
						<td class="data_cell" style="text-align:center;">有效</td>
						<td class="data_cell">
							<img onclick="window.location.href='assign/3'" title="分配权限" src="${ctx}/static/images/bt_linkman.gif" class="op_button" />
							<img onclick="window.location.href='delete/3'" title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				
					<tr>
						<td class="data_cell" style="text-align:right;padding:0 10px;">1</td>
						<td class="data_cell" style="text-align:center;">管理员</td>
						<td class="data_cell" style="text-align:left;"></td>
						<td class="data_cell" style="text-align:center;">有效</td>
						<td class="data_cell">
							<img onclick="window.location.href='assign/1'" title="分配权限" src="${ctx}/static/images/bt_linkman.gif" class="op_button" />
							<img onclick="window.location.href='delete/1'" title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				
					<tr>
						<td class="data_cell" style="text-align:right;padding:0 10px;">2</td>
						<td class="data_cell" style="text-align:center;">测试</td>
						<td class="data_cell" style="text-align:left;"></td>
						<td class="data_cell" style="text-align:center;">有效</td>
						<td class="data_cell">
							<img onclick="window.location.href='assign/2'" title="分配权限" src="${ctx}/static/images/bt_linkman.gif" class="op_button" />
							<img onclick="window.location.href='delete/2'" title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				
				
			</table>
			








<div style="text-align:right; padding:6px 6px 0 0;">

	

	共 3 条记录 
	&nbsp;&nbsp;
	
	当前第 1 页/共 1 页
	&nbsp;&nbsp;
	
	
	 
	
	
	转到 <input id="pageNo" size='1'/> 页
	&nbsp;&nbsp;

</div>

<script type="text/javascript" src="${ctx}/static/jquery/jquery-1.9.1.min.js"></script>
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
			if(pageNo2 < 1 || pageNo2 > parseInt("1")){
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
