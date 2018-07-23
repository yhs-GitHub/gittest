<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function page(page) {
		window.location.href = "SelectStuServlet?page=" + page;
	}
</script>
<body>
	<div align="center">
		<h1 style="color: blue;">欢迎您查看学生信息 </h1>
		<table border="1" cellspacing="0" style="text-align: center;align-self: auto;width: 800px;border-color: blue;">
			<tr>
				<th>ID</th>
				<th>姓名</th>
				<th>出生日期</th>
				<th>平均分</th>
				<th>备注</th>
				<th></th>
			</tr>
			<c:forEach items="${requestScope.list}" var="o" begin="${param.page*10-10}" end="${param.page*10-1}" step="1">
				<tr>
					<td>${o.get(0)}</td>
					<td>${o.get(1)}</td>
					<td>${o.get(2)}</td>
					<td>${o.get(4)}</td>
					<td>${o.get(3)}</td>
					<td>
						<a href="SelectOneServlet?id=${o.get(0)}">修改</a>
						<a href="DelStudentServlet?id=${o.get(0)}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		<a href="addStudent.jsp">录入学生</a>
		<table>
			<tr>
				<td>
					<input type="button" value="上一页" onclick="page(${param.page-1})">
				</td>
				<td>
					第${param.page}页
				</td>
				<td>
					<form action="SelectStuServlet">
						<input type="text" id="page" name="page">
						<input type="submit" value="跳转">
					</form>
				</td>
				<td>
					<input type="button" value="下一页" onclick="page(${param.page+1})">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>