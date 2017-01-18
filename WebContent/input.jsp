<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>编辑数据字典项</title>
</head>

<body class="main">
	
		<% 
			Map<Boolean, String> map = new HashMap<Boolean, String>();
			map.put(true, "是");
			map.put(false, "否");
			request.setAttribute("editable", map);
		%>
		<form:select path="editable" items="${editable }">
		</form:select>
					
</body>
</html>