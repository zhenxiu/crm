<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>编辑库存</title>
</head>

<body class="main">

	<span class="page_title">编辑库存</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	
	<form id="storage" action="${ctx}/storage/create" method="POST">
		<input id="id" name="id" type="hidden" value=""/>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					产品 -false-
				</th>
				<td>
					
						<select id="product.id" name="product.id"><option value="1">ThinkPad T430 笔记本</option><option value="3">Nexus 手机</option><option value="4">ipad</option><option value="5">小米手机</option><option value="6">iphone</option><option value="7">三星NOTE</option></select>
					
					
				</td>
				<th>
					仓库
				</th>
				<td>
					
						<input id="wareHouse" name="wareHouse" type="text" value=""/>
					
					
				</td>
			</tr>
			<tr>
				<th>
					货位
				</th>
				<td>
					
						<input id="stockWare" name="stockWare" type="text" value=""/>
					
					
				</td>
				<th>
					数量
					
				</th>
				<td>
					
						<input id="stockCount" name="stockCount" type="text" value="0"/>
					
					
				</td>
			</tr>
			<tr>
				<th>
					备注
				</th>
				<td>
					
						<input id="memo" name="memo" type="text" value=""/>
					
					
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
    