<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.vo.GuestBookVo"%>


<%
List<GuestBookVo> guestList = (List<GuestBookVo>) request.getAttribute("gList");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./gb2" method="post">
		<input type="hidden" name="action" value="add"><br>
		<table width="500" border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
			</tr>
			<tr>
				<td colspan="4 " align=right><button type="submit">확인</button></td>
			</tr>
		</table>
	</form>
	<br>

	<%
	for (int i = 0; i < guestList.size(); i++) {
	%>
	<table width=500 border="1">
		<tr>
			<td><%=guestList.get(i).getNo()%></td>
			<td><%=guestList.get(i).getName()%></td>
			<td><%=guestList.get(i).getRegDate()%></td>
			<td><a href="./gb2?action=deleteForm&no=<%=guestList.get(i).getNo()%>">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4"><%=guestList.get(i).getContent()%></td>
		</tr>
	</table>

	<%
	}
	%>
</body>
</html>