<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
		$(function(){
			$("#new").click(function(){
				window.location.href="${ctx}" + "/user/create";
				return false;
			});
		})
	</script>
</head>

<body class="main">
	<form action="${ctx}/user/list">
		<div class="page_title">
			用户管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">新建</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					用户名
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_name" />
				</td>
				<th class="input_title">
					状态
				</th>
				<td class="input_content">
					<select name="search_EQ_enabled">
						<option value="">
							全部
						</option>
						<option value="1">
							正常
						</option>
						<option value="0">
							已删除
						</option>
					</select>
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th class="data_title" style="width: 40px;">
						编号
					</th>
					<th class="data_title" style="width: 50%;">
						用户名
					</th>
					<th class="data_title" style="width: 20%;">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
				
					<tr>
						<td class="data_cell" style="text-align: right; padding: 0 10px;">
						25
						</td>
						<td class="data_cell" style="text-align: center;">
						z
						</td>
						<td class="data_cell">
						有效
						</td>
						<td class="data_cell">
							<img onclick="window.location.href='delete?id=25'" 
								title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />
							<img onclick="window.location.href='create?id=25'" 
								class="op_button" src="${ctx}/static/images/bt_edit.gif" title="编辑" />
						</td>
					</tr>
				
					<tr>
						<td class="data_cell" style="text-align: right; padding: 0 10px;">
						21
						</td>
						<td class="data_cell" style="text-align: center;">
						bcde
						</td>
						<td class="data_cell">
						有效
						</td>
						<td class="data_cell">
							<img onclick="window.location.href='delete?id=21'" 
								title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />
							<img onclick="window.location.href='create?id=21'" 
								class="op_button" src="${ctx}/static/images/bt_edit.gif" title="编辑" />
						</td>
					</tr>
				
					<tr>
						<td class="data_cell" style="text-align: right; padding: 0 10px;">
						22
						</td>
						<td class="data_cell" style="text-align: center;">
						abcd
						</td>
						<td class="data_cell">
						有效
						</td>
						<td class="data_cell">
							<img onclick="window.location.href='delete?id=22'" 
								title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />
							<img onclick="window.location.href='create?id=22'" 
								class="op_button" src="${ctx}/static/images/bt_edit.gif" title="编辑" />
						</td>
					</tr>
				
					<tr>
						<td class="data_cell" style="text-align: right; padding: 0 10px;">
						24
						</td>
						<td class="data_cell" style="text-align: center;">
						a
						</td>
						<td class="data_cell">
						有效
						</td>
						<td class="data_cell">
							<img onclick="window.location.href='delete?id=24'" 
								title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />
							<img onclick="window.location.href='create?id=24'" 
								class="op_button" src="${ctx}/static/images/bt_edit.gif" title="编辑" />
						</td>
					</tr>
				
			</table>
			








<div style="text-align:right; padding:6px 6px 0 0;">

	

	共 4 条记录 
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
