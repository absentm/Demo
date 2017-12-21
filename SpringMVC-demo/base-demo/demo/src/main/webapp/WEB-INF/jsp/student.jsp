<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC表单处理</title>
</head>
<body>

	<h2>Student Information</h2>
	<form:form method="POST" action="/demo/addStudent">
		<table>
			<tr>
				<td><form:label path="name">名字：</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="age">年龄：</form:label></td>
				<td><form:input path="age" /></td>
			</tr>
			<tr>
				<td><form:label path="id">编号：</form:label></td>
				<td><form:input path="id" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="提交表单" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>