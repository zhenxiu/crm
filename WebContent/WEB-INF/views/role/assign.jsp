<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>角色管理 - 分配权限</title>
	<script type="text/javascript">
		$(function(){
			$(":checkbox[class^=parent]").click(function(){
				var flag = $(this).is(":checked");
				var parentDisplayName = $(this).next(":hidden").val();
				$(".sub-" + parentDisplayName).prop("checked", flag);
			});
			
			$(":checkbox[class^=sub]").click(function(){
				var parentDisplayName = $(this).attr("class").split("-")[1];
				var flag = $(".sub-" + parentDisplayName + ":checked").length
					== $(".sub-" + parentDisplayName).length;
				
				$(":hidden[value='" + parentDisplayName + "']").prev(":checkbox").prop("checked", flag);
			});
			
			$(":checkbox[class^=sub]").each(function(){
				var parentDisplayName = $(this).attr("class").split("-")[1];
				var flag = $(".sub-" + parentDisplayName + ":checked").length
					== $(".sub-" + parentDisplayName).length;
				
				$(":hidden[value='" + parentDisplayName + "']").prev(":checkbox").prop("checked", flag);
			});
		})
	</script>
</head>

<body class="main">
 	
 	
 	<form id="role" action="${ctx}/role/assign" method="post">
 	
		<input type="hidden" name="id" value="3" />
		
		<div class="page_title">
			角色管理 &gt; 分配权限
		</div>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.back(-1);">
				返回
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				保存
			</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title" width="10%">
					角色名
				</th>
				<td class="input_content" width="20%">
					测试管理员
				</td>
				<th class="input_title" width="10%">
					角色描述
				</th>
				<td class="input_content" width="20%">
					测试时使用, 上线需删除
				</td>
				<th class="input_title" width="10%">
					状态
				</th>
				<td class="input_content" width="20%">
					有效
				</td>
			</tr>
			<tr>
				<th class="input_title">
					权限
				</th>
				<td class="input_content" colspan="5" valign="top">
					
						<input type="checkbox" class="parent"/>
						<input type="hidden" value="营销管理"/>
						营销管理:
						<br>
						
						&nbsp;&nbsp;&nbsp;
						<span><input id="authorities21" name="authorities2" class="sub-营销管理" type="checkbox" value="16" checked="checked"/><label for="authorities21">营销机会管理</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities22" name="authorities2" class="sub-营销管理" type="checkbox" value="17" checked="checked"/><label for="authorities22">客户开发计划</label></span><input type="hidden" name="_authorities2" value="on"/> 
						
						<br><br>	
					
						<input type="checkbox" class="parent"/>
						<input type="hidden" value="客户管理"/>
						客户管理:
						<br>
						
						&nbsp;&nbsp;&nbsp;
						<span><input id="authorities23" name="authorities2" class="sub-客户管理" type="checkbox" value="19" checked="checked"/><label for="authorities23">客户信息管理</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities24" name="authorities2" class="sub-客户管理" type="checkbox" value="20" checked="checked"/><label for="authorities24">客户流失管理</label></span><input type="hidden" name="_authorities2" value="on"/> 
						
						<br><br>	
					
						<input type="checkbox" class="parent"/>
						<input type="hidden" value="服务管理"/>
						服务管理:
						<br>
						
						&nbsp;&nbsp;&nbsp;
						<span><input id="authorities25" name="authorities2" class="sub-服务管理" type="checkbox" value="22" checked="checked"/><label for="authorities25">服务创建</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities26" name="authorities2" class="sub-服务管理" type="checkbox" value="23" checked="checked"/><label for="authorities26">服务分配</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities27" name="authorities2" class="sub-服务管理" type="checkbox" value="24" checked="checked"/><label for="authorities27">服务处理</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities28" name="authorities2" class="sub-服务管理" type="checkbox" value="25" checked="checked"/><label for="authorities28">服务反馈</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities29" name="authorities2" class="sub-服务管理" type="checkbox" value="26" checked="checked"/><label for="authorities29">服务归档</label></span><input type="hidden" name="_authorities2" value="on"/> 
						
						<br><br>	
					
						<input type="checkbox" class="parent"/>
						<input type="hidden" value="统计报表"/>
						统计报表:
						<br>
						
						&nbsp;&nbsp;&nbsp;
						<span><input id="authorities210" name="authorities2" class="sub-统计报表" type="checkbox" value="28" checked="checked"/><label for="authorities210">客户贡献分析</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities211" name="authorities2" class="sub-统计报表" type="checkbox" value="29" checked="checked"/><label for="authorities211">客户构成分析</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities212" name="authorities2" class="sub-统计报表" type="checkbox" value="30" checked="checked"/><label for="authorities212">客户服务分析</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities213" name="authorities2" class="sub-统计报表" type="checkbox" value="31" checked="checked"/><label for="authorities213">客户流失分析</label></span><input type="hidden" name="_authorities2" value="on"/> 
						
						<br><br>	
					
						<input type="checkbox" class="parent"/>
						<input type="hidden" value="基础数据"/>
						基础数据:
						<br>
						
						&nbsp;&nbsp;&nbsp;
						<span><input id="authorities214" name="authorities2" class="sub-基础数据" type="checkbox" value="33" checked="checked"/><label for="authorities214">数据字典管理</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities215" name="authorities2" class="sub-基础数据" type="checkbox" value="34" checked="checked"/><label for="authorities215">查询产品信息</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities216" name="authorities2" class="sub-基础数据" type="checkbox" value="35" checked="checked"/><label for="authorities216">查询库存信息</label></span><input type="hidden" name="_authorities2" value="on"/> 
						
						<br><br>	
					
						<input type="checkbox" class="parent"/>
						<input type="hidden" value="系统权限管理"/>
						系统权限管理:
						<br>
						
						&nbsp;&nbsp;&nbsp;
						<span><input id="authorities217" name="authorities2" class="sub-系统权限管理" type="checkbox" value="37" checked="checked"/><label for="authorities217">系统用户管理</label></span><span><br>&nbsp;&nbsp;&nbsp;&nbsp;<input id="authorities218" name="authorities2" class="sub-系统权限管理" type="checkbox" value="39" checked="checked"/><label for="authorities218">角色管理</label></span><input type="hidden" name="_authorities2" value="on"/> 
						
						<br><br>	
					
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>
    