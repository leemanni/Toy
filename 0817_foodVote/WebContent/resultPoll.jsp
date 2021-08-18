<%@page import="java.text.DecimalFormat"%>
<%@page import="com.leemanni.Ex.ReadPoll"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표결과</title>
</head>
<body>
<%
	String filePath = application.getRealPath("/") + "poll.txt";
	ArrayList<String> poll = ReadPoll.readPoll(filePath);
	int itemCount = (poll.size() -1) / 2;
	
	int total = 0;	// 득표율
	for(int i = itemCount + 1 ; i< poll.size(); i++){
		total +=   Integer.parseInt(poll.get(i));
	}
	DecimalFormat df1 = new DecimalFormat("#,##0표");
	DecimalFormat df2 = new DecimalFormat("0.0%");
%>
<table width ="500" align="center"  cellspacing="0" cellpadding="2" border="1">
	<tr >
		<th colspan="2">
			전체 득표 수
		</th>
	</tr>
	<tr>
		<td align="right" colspan="2">
			<%=df1.format(total) %>
		</td>
	</tr>
		<%
			for(int i = 1 ; i< itemCount +1 ; i++){
				int vote = Integer.parseInt(poll.get(i + itemCount));
				double per = (double) vote / total;
			
		%>
	<tr>
		<td width="130">
			<%=poll.get(i)%>( <%=df1.format(vote) %> )
		</td>
		<td>
			<hr color="black" size="20" width="<%= 370 * per%>" align="left">		
		<%
			}
		%>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="button" value="나도 투표하기" onclick="location.href='readPoll.jsp'">
		</td>
	</tr>




</table>
</body>
</html>