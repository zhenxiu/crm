<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<html>
  <head>
    <title>确认流失</title>
  </head>
  <body class="main">

  <span class="page_title">确认流失</span>
  <div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
		<button class="common_button" onclick="document.forms[0].submit();">保存</button>
  </div>
  
  <form action="/crm_/drain/confirm" method="post">
  	<input type="hidden" name="id" value="204"/>
	<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
		<tr>
			<th>编号</th>
			<td>204</td>
			<th>客户</th>
			<td>阿拉灯</td>
		</tr>
		<tr>
			<th>客户经理</th>
			<td>杨再兴</td>
			<th>最后一次下单时间</th>
			<td>2014-01-28</td>
		</tr>
		
			
				<tr>
					<th>暂缓措施-1</th>
					<td colspan="3">AA</td>
				</tr>
			
		
			
				<tr>
					<th>暂缓措施-2</th>
					<td colspan="3">BB</td>
				</tr>
			
		
			
				<tr>
					<th>暂缓措施-3</th>
					<td colspan="3">CC</td>
				</tr>
			
		
		<tr>
			<th>流失原因</th>
			<td colspan="3"><textarea name="reason" cols="50" rows="6"></textarea>&nbsp;</td>
		</tr>
	</table>
	</form>	
  </body>
</html>
