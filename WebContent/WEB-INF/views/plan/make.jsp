<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>制定计划</title>
	<script type="text/javascript">
		$(function(){
			
			var saveMethod = function(){
				
				var id = $(this).attr("id");
				
				id = id.split("-")[1];
				var todo = $("#todo-" + id).val();
				
				var url = "${ctx}/plan/make-ajax";
				var args = {"id":id, "todo":todo, "time":new Date()};
				$.post(url, args, function(data){

					if(data == "1"){
						alert("修改成功!");
					}else{
						alert("修改失败!");
					}
				});
				return false;
			};
			
			var deleteMethod = function(){
				
				var id = $(this).attr("id");
				
				id = id.split("-")[1];
				
				var url = "${ctx}/plan/delete-ajax";
				var args = {"id":id};
				$.post(url, args, function(data){
					
					if(data == "1"){
						$("#plan-" + id).remove();
						alert("删除成功!");
					}else{
						alert("删除失败!");
					}
				});
				
				return false;	
			};
			
			$("button[id^='save']").click(saveMethod);		
			
			$("button[id^='delete']").click(deleteMethod);
				
			
			$("#execute").click(function(){
				var id = $(":hidden[name='chance.id']").val();
				
				window.location.href = "${ctx}/plan/edit?chanceId=" + id;
				return false;
			});

			
			$("#create").click(function(){
				
				var url = "${ctx}/plan";
				var date = $("#date").val();
				var todo = $("#todo").val();
				var chanceId = "${param.chanceId}";
				var args = {"date":date,"todo":todo,"chanceId":chanceId,"time":new Date()};
				
				$.post(url,args,function(data){
					
					if(parseInt(data) > 0){
						alert("新建成功");

						var $tr = $("<tr id='plan-" + data + "'></tr>");
						var $dateTd = $("<td class='list_data_text'>" + date + "&nbsp;</td>");
						
						var $todoTd = $("<td class='list_data_ltext'></td>");
						
						var $todoInput = $("<input type='text' size='50' value='" + todo + "' id='todo-" + data + "'/>");
						var $saveButton = $("<button class='common_button' id='save-" + data + "'>&nbsp;保存</button>");
						var $deleteButton = $("<button class='common_button' id='delete-" + data + "'>&nbsp;删除</button>");
						
						$todoTd.append($todoInput).append($saveButton).append($deleteButton);
						$tr.append($dateTd).append($todoTd);
						
						$("#planTBody").append($tr);
						
					}
					$("button[id^='save']").click(saveMethod);
					$("button[id^='delete']").click(deleteMethod);
					
				});
				
				return false;
			});
			
		})	
			
	</script>
</head>

<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute">
			执行计划
		</button>
		<button class="common_button" onclick="window.location.href='${ctx}/plan/chance/list'">
			返回
		</button>
	</div>
		<form:form action="${ctx}/plan/list" method="post">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>

				<td>
					${chance.id }
				</td>
				<th>
					机会来源
				</th>

				<td>
					${chance.source }
				</td>
			</tr>
			<tr>
				<th>
					客户名称
				</th>
				<td>
					${chance.custName }
				</td>
				<th>
					成功机率（%）
				</th>

				<td>
					${chance.rate }
				</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					${chance.title }
				</td>
			</tr>
			<tr>
				<th>
					联系人
				</th>

				<td>
					${chance.contact }
				</td>
				<th>
					联系人电话
				</th>

				<td>
					${chance.contactTel }
				</td>
			</tr>
			<tr>
				<th>
					机会描述
				</th>
				<td colspan="3">
					${chance.description }
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${chance.createBy.name }
				</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${chance.createDate }" pattern="yyyy-MM-dd"/> 
				</td>
			</tr>
			<tr>
				<th>
					指派给
				</th>
				<td>
					${chance.designee.name }
				</td>

			</tr>
		</table>

		<br />
		
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tbody id="planTBody">
				<tr>
					<th width="200px">
						日期
					</th>
					<th>
						计划项
					</th>
				</tr>
				<c:if test="${not empty chance.salesPlans }">
					<c:forEach items="${chance.salesPlans }" var="plan">
						<tr id="plan-${plan.id }">
							<td class="list_data_text">
								<fmt:formatDate value="${plan.date }" pattern="yyyy-MM-dd"/> 
								&nbsp;
							</td>
							<td class="list_data_ltext">
								<c:if test="${plan.result == null }">
								<input type="text" size="50"
									value="${plan.todo}" id="todo-${plan.id }"/>
							
								<button class="common_button" id="save-${plan.id }">
									保存
								</button>
								<button class="common_button" id="delete-${plan.id }">
									删除
								</button>
								</c:if>
								<c:if test="${plan.result != null }">
									<input type="text" size="50"
										value="${plan.todo}" readonly="readonly"/>
									<input type="text" size="50"
										value="${plan.result}" readonly="readonly"/>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<div class="button_bar">
			<button class="common_button" id="create">
				新建
			</button>
		</div>
		<input type="hidden" name="chance.id" value="${chance.id }" />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					日期
					<br />
					(格式: yyyy-mm-dd)
				</th>
				<td>
					<input type="text" name="date" id="date" />
					&nbsp;
				</td>
				<th>
					计划项
				</th>
				<td>
					<input type="text" name="todo" size="50" id="todo" />
					&nbsp;
				</td>
			</tr>
		</table>

	</form:form>
</body>
</html>
