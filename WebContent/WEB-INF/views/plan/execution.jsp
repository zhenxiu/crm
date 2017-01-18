<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>执行计划</title>
    <script type="text/javascript">
    	$(function(){
    		$(":text[id^='result-']").each(function(){
    			var val = $(this).val();
    			if(val != null && $.trim(val) != ""){
    				$(this).attr("disabled", "disabled");
    			}
    		});
    		
    		$("button[id^='saveresult']").click(function(){
    			var $save = $(this);
    			var id = $save.attr("id");
				id = id.split("-")[1];
				var $result = $save.prev(":text");
				var result = $result.val();
				
				if(result != null && $.trim(result) != ""){
					/* var url = "${ctx}/plan/execute?id=" + id + "&result=" + result; */
					
					var url = "${ctx}/plan/execute";
					var args = {"id":id,"result":result,"time":new Date()};
					
					$.get(url,args,function(data){
						
						if(data == "1"){
							alert("保存成功");
							$result.attr("disabled", "disabled");
							$save.attr("style", "display:none");
						}
						
					});
					
					/* window.location.href = url; */
				}
				
				return false;
    		});
    		
    		$("#stop").click(function(){
    			var url = $(this).next("input")[0].value;
    			
    			$("#stopForm").attr("action",url).submit();
    			
    			return false;
    		});
    	})
    </script>
  </head>

  <body class="main">
  	<form id="stopForm" action="" method="post">
  		<input type="hidden" name="_method" value="PUT"/>
  	</form>
	<span class="page_title">执行计划</span>
	<div class="button_bar">
		<button id="stop" class="common_button">终止开发</button>
		<input type="hidden" value="${ctx}/chance/stop?chanceId=${chance.id }"/>
		<button class="common_button" onclick="window.location.href='${ctx}/plan/list?chanceId=${chance.id }'">制定计划</button>
		<button class="common_button" onclick="window.location.href='${ctx}/plan/list?chanceId=${chance.id }'">返回</button>			
		<button class="common_button" onclick="window.location.href='${ctx}/chance/finish?chanceId=${chance.id }'">开发成功</button>
	</div>
	<form:form action="${ctx}/plan/execute" method="post">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>${chance.id }&nbsp;</td>
				
				<th>机会来源</th>
				<td>${chance.source }&nbsp;</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>${chance.custName }&nbsp;</td>
				
				<th>成功机率（%）</th>
				<td>${chance.rate }&nbsp;</td>
			</tr>
			
			<tr><th>概要</th>
				<td colspan="3">${chance.title }&nbsp;</td>
			</tr>
			
			<tr>
				<th>联系人</th>
				<td>${chance.contact }&nbsp;</td>
				<th>联系人电话</th>
				<td>${chance.contactTel }&nbsp;</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">${chance.description }&nbsp;</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>${chance.createBy.name }&nbsp;</td>
				<th>创建时间</th>
				<td><fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/>&nbsp;</td>
			</tr>		
			<tr>					
				<th>指派给</th>
				<td>${chance.designee.name }&nbsp;</td>
			</tr>
		</table>
	
	<br />
	
		<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<c:if test="${not empty chance.salesPlans }">
				<c:forEach items="${chance.salesPlans }" var="plan">
					<tr>
						<th width="200px">日期</th>
						<th>计划</th>
						<th>执行效果</th>
					</tr>
					
					<tr>
						<td class="list_data_text">
							<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd"/>&nbsp;
						</td>
						<td class="list_data_ltext">${plan.todo }&nbsp;</td>
						<td>
							<input class="result" id="result-1017" type="text" size="50" value="${plan.result }" />
							<c:if test="${empty plan.result }">
								<button class="common_button" id="saveresult-${plan.id }">保存</button>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>	
  </form:form>
  </body>
</html>
