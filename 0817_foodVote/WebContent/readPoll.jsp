<%@page import="com.leemanni.Ex.ReadPoll"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 가장 좋아하는 음식은?</title>
</head>
<body>
<%
	String filePath =application.getRealPath("/") + "poll.txt";
	ArrayList<String> pollList = ReadPoll.readPoll(filePath);
	int itemCount = (pollList.size()-1) /2 ;
%>
<form action="writePoll.jsp" method="get">
	<table width ="400" align="center" border="1" cellpadding ="6" cellspacing="0">
		<tr>
			<th><%=pollList.get(0) %>		
		</tr>
	<%
		for(int i = 1 ; i <= itemCount ; i++){
	%>
		<tr height="50">
			<td>
				<input type="radio"  name ="poll" value=<%=i%>> <%=pollList.get(i)%>
			</td>
		</tr>
	<%		
		}
	%>
		<tr>
			<td align="center">
				<input type="submit" value="투표하기">
				<input type="button" value="결과보기" onclick="location.href='resultPoll.jsp'"> 
			</td>
		</tr>
	</table>
</form>
</body>
</html>