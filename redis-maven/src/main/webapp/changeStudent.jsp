<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function names(){
		var name=document.getElementById("name").value;
		var name1=document.getElementById("name1");
		name0=/^[\u4E00-\u9FA5]{2,4}$/;
		if(name0.test(name)){
			name1.innerText="";
			return true;
		}else{
			name1.innerText="必须用中文姓名";
			return false;
		}
	}
	function birthss(){
		var birth=document.getElementById("birth").value;
		var birth1=document.getElementById("birth1");
		var reg =  /^(\d{4})(-|\/)(\d{1,2})(-|\/)(\d{1,2})$/,r,p;
		if(r = birth.match(reg)){   
	        var d = new Date(r[1],r[3]-1,r[5]);   
	        p=d.getFullYear() == r[1] && (d.getMonth()+1) == r[3] && d.getDate() == r[5];   
	    }
		if(p){
			birth1.innerText="";
			return true;
		}else{
			birth1.innerText="日期格式不对";
			return true;
		}
	}
	function grades(){
		var grade=document.getElementById("grade").value;
		var grade1=document.getElementById("grade1");
		grade0=/^\d{1,2}$/;
		if(grade0.test(grade)||grade==100){
			grade1.innerText="";
			return true;
		}else{
			grade1.innerText="平均分必须为整数，且大于等于0，小于等于100";
			return false;
		}
	}
	function formtest(){
		if(names()&&birthss()&&grades()){
			return true;
		}else{
			return false;
		}
	}
</script>
<style>
	div{
		color: red;
	}
	.td{
		color: black;
		height: 50px;
		width: 200px;
	}
</style>
<body>
	<div align="center">
		<h1 style="color: blue;">修改学生信息</h1>
		<form action="ChangeStudentServlet" method="post" onsubmit="return formtest()">
		<table style="width: 600px;">
				<tr>
					<td class="td" align="right">Id</td>
					<td align="center">
						<input type="text" id="id" name="id" value="${param.id}" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td class="td" align="right">姓名</td>
					<td align="center">
						<input type="text" id="name" name="name" value="${requestScope.name}" onchange="names()">
						<br><div id="name1"></div>
					</td>
				</tr>
				<tr>
					<td class="td" align="right">出生日期</td>
					<td align="center">
						<input type="text" id="birth" name="birth" value="${requestScope.birth}" onchange="birthss()">
						<br><div id="birth1"></div>
					</td>
				</tr>
				<tr>
					<td class="td" align="right">备注</td>
					<td align="center">
						<input type="text" id="mark" name="mark" value="${requestScope.mark}" onchange="marks()">
						<br><div id="mark1"></div>
					</td>
				</tr>
				<tr>
					<td class="td" align="right">平均分</td>
					<td align="center">
						<input type="text" id="grade" name="grade" value="${requestScope.grade}" onchange="grades()">
						<br><div id="grade1"></div>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="提交"><br>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>