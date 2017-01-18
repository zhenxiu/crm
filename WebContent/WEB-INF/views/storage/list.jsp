<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>库存查询</title>
</head>
<body>
	<div class="page_title">
		库存管理
	</div>
	<div class="button_bar">
		<button class="common_button"
			onclick="window.location.href='${ctx}/storage/create'">
			库存添加
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctx}/storage/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					产品
				</th>
				<td>
					<input type="text" name="search_LIKE_product.name" />
				</td>
				<th>
					仓库
				</th>
				<td>
					<input type="text" name="search_LIKE_wareHouse" />
				</td>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
			
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<th>
					产品
				</th>
				<th>
					仓库
				</th>
				<th>
					货位
				</th>
				<th>
					件数
				</th>
				<th>
					备注
				</th>
				<th>
					操作
				</th>
			</tr>
			
				<tr>
					<td class="list_data_number">
						1
					</td>
					<td class="list_data_ltext">
						Nexus 手机
					</td>
					<td class="list_data_ltext">
						北京五棵松
					</td>
					<td class="list_data_text">
						1
					</td>

					<td class="list_data_number">
						100
					</td>
					<td class="list_data_ltext">
						Nexus 手机比较抢手
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctx}/storage/create?id=1'" 
							title="修改" src="${ctx}/static/images/bt_edit.gif" class="op_button" />
						<img onclick="window.location.href='${ctx}/storage/delete?id=1'" 
							title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />

					</td>
				</tr>
			
				<tr>
					<td class="list_data_number">
						2
					</td>
					<td class="list_data_ltext">
						ThinkPad T430 笔记本
					</td>
					<td class="list_data_ltext">
						中关村海龙
					</td>
					<td class="list_data_text">
						2
					</td>

					<td class="list_data_number">
						2000
					</td>
					<td class="list_data_ltext">
						
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctx}/storage/create?id=2'" 
							title="修改" src="${ctx}/static/images/bt_edit.gif" class="op_button" />
						<img onclick="window.location.href='${ctx}/storage/delete?id=2'" 
							title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />

					</td>
				</tr>
			
				<tr>
					<td class="list_data_number">
						3
					</td>
					<td class="list_data_ltext">
						Nexus 手机
					</td>
					<td class="list_data_ltext">
						京东一号
					</td>
					<td class="list_data_text">
						3
					</td>

					<td class="list_data_number">
						200
					</td>
					<td class="list_data_ltext">
						
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctx}/storage/create?id=3'" 
							title="修改" src="${ctx}/static/images/bt_edit.gif" class="op_button" />
						<img onclick="window.location.href='${ctx}/storage/delete?id=3'" 
							title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />

					</td>
				</tr>
			
				<tr>
					<td class="list_data_number">
						4
					</td>
					<td class="list_data_ltext">
						ipad
					</td>
					<td class="list_data_ltext">
						e世界
					</td>
					<td class="list_data_text">
						4
					</td>

					<td class="list_data_number">
						100
					</td>
					<td class="list_data_ltext">
						
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctx}/storage/create?id=4'" 
							title="修改" src="${ctx}/static/images/bt_edit.gif" class="op_button" />
						<img onclick="window.location.href='${ctx}/storage/delete?id=4'" 
							title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />

					</td>
				</tr>
			
				<tr>
					<td class="list_data_number">
						5
					</td>
					<td class="list_data_ltext">
						ThinkPad T430 笔记本
					</td>
					<td class="list_data_ltext">
						北京五棵松
					</td>
					<td class="list_data_text">
						10
					</td>

					<td class="list_data_number">
						200
					</td>
					<td class="list_data_ltext">
						
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctx}/storage/create?id=5'" 
							title="修改" src="${ctx}/static/images/bt_edit.gif" class="op_button" />
						<img onclick="window.location.href='${ctx}/storage/delete?id=5'" 
							title="删除" src="${ctx}/static/images/bt_del.gif" class="op_button" />

					</td>
				</tr>
			
		</table>
			








<div style="text-align:right; padding:6px 6px 0 0;">

	

	共 10 条记录 
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