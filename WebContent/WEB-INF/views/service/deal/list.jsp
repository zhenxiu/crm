<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户服务管理</title>
<body>

	<div class="page_title">
		客户服务管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctx}/service/create'">
			新建
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctx}/service/deal/list" method="GET">
	
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<input type="text" name="search_LIKE_serviceType" />
				</td>
				<th>
					概要
				</th>
				<td>
					<input type="text" name="search_LIKE_serviceTitle" />
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<input type="text" name="search_EQ_custName" />
				</td>
				<th>
					创建时间
				</th>
				<td>
					<input type="text" name="search_GTE_createDate1" size="10" />
					-
					<input type="text" name="search_LTE_createDate2" size="10" />
				</td>
			</tr>
		</table>
		
		<!-- 列表数据 -->
		<br />
		<c:if test="${page.numberOfElements == 0 }">
			当前没有客户相关数据
		</c:if>
		<c:if test="${page.numberOfElements != 0}">
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						服务类型
					</th>
					<th>
						概要
					</th>
					<th>
						客户
					</th>
					<th>
						创建人
					</th>
					<th>
						创建时间
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach items="${page.content }" var="customerService">
					<tr>
						<td class="list_data_number">
							${customerService.id }
						</td>
						<td class="list_data_text">
							${customerService.serviceType }
						</td>
						<td class="list_data_ltext">
							${customerService.serviceTitle }
						</td>
	
						<td class="list_data_text">
							${customerService.customer.name }
						</td>
						<td class="list_data_text">
							${customerService.createdby.name }
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${customerService.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctx}/service/deal?id=${customerService.id }'" 
								title="处理" src="${ctx}/static/images/bt_deal.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>	

		


<div style="text-align:right; padding:6px 6px 0 0;">


	共 ${page.totalElements} 条记录 
	&nbsp;&nbsp;
	
	当前第 ${page.number} 页/共 ${page.totalPages} 页
	&nbsp;&nbsp;
	
	<c:if test="${page.number != 1}">
		<a href='?page=1&${queryString}'>首页</a>
		&nbsp;&nbsp;
		<a href='?page=${page.number - 1}&${queryString}'>上一页</a>
		&nbsp;&nbsp;
	</c:if>
	 
	<c:if test="${page.number != page.totalPages}">
		<a href='?page=${page.number + 1}&${queryString}'>下一页</a>
		&nbsp;&nbsp;
		<a href='?page=${page.totalPages }&${queryString}'>末页</a>
		&nbsp;&nbsp;
	</c:if>

	
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
