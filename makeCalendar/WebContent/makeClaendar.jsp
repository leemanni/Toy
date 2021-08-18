<%@page import="com.leemanni.myCalendar.MyClaendar"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
		background-color: gray;
	}
	tr{
		height: 70px;
	}
	th{
		align-content: center;
		width: 100px;
	}
	th#title{
		color: greenyellow;
		font-size: 25pt;
		font-weight: bold;
	}
	td{
		text-align: center;
		font-size: 20pt;
	}
	td.sunday{
		color: red;
	}
	td.saturday{
		color: blue
	}
	a{
		color: black;
		text-decoration: none;
	}
	a:hover {
		text-decoration: underline;
	}
	a:active {
		color: tomato;
		text-decoration: none;
	}
	


</style>
</head>
<body>
<%
	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	
	try{
		year = Integer.parseInt(request.getParameter("year"));
		month = Integer.parseInt(request.getParameter("month"));
		if(month <= 0){
			year -= 1;
			month = 12;
		}else if(month > 12){
			year += 1;
			month = 1;
		}
	}catch(NumberFormatException e){
		
	}
%>

<table width="700px" align = "center" border="4" cellpadding ="2" cellspacing="0" s>
	<tr>
		<th>
			<a href="?year=<%=year%>&month=<%=month-1%>">이전달</a>
		</th>
		<th colspan="5" id="title">
			<%=year %> 년 <%=month %> 월
		</th>
		<th>
			<a href="?year=<%=year%>&month=<%=month+1%>">다음달</a>
		</th>
	</tr>
	<tr>
		<td class="sunday">일</td>
		<td>월</td>
		<td>화</td>
		<td>수</td>
		<td>목</td>
		<td>금</td>
		<td class="saturday">토</td>
	</tr>
	<tr>
	<%	
		for(int i = 1 ; i <= MyClaendar.weekDay(year, month, 1); i++){
			out.println("<td></td>");
		}
		for(int i = 1 ; i <= MyClaendar.lastday(year, month)  ; i++){
		out.println("<td>" + i + "</td>");
			if(MyClaendar.weekDay(year, month, i) == 6 && i != 31){
			out.println("</tr><tr>");
			}
		}
	%>
	</tr>
</table>
</body>
</html>