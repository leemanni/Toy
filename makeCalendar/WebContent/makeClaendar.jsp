<%@page import="com.leemanni.myCalendar.SolorToLunar"%>
<%@page import="com.leemanni.myCalendar.LunarDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.leemanni.myCalendar.MyCalandar"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="calendar.css">
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
	ArrayList<LunarDate> lunarDate = SolorToLunar.solaToLunar(year, month);
%>

<table width="700" align = "center" border="4" cellpadding ="2" cellspacing="0" s>
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
		// 빈칸에 전달의 날들을 넣어주기
		int week = MyCalandar.weekDay(year, month, 1);
		// 출력할 전달의 시작날 계산
		int start = 0;
		if(month ==1 ){
			start = 31 - week;
		}else{
			start = MyCalandar.lastDay(year, month-1) - week;
		}
		//전달 날짜들 출력
		for(int i = 0 ; i < week ; i++){
			if(i == 0){
				out.println("<td class='beforeSun'><span>"+(month == 1? 12 : month-1)+"/"+ ++start+"</span></td>");
			}else{
				out.println("<td class='before'><span>"+(month == 1? 12 : month-1)+"/"+ ++start+"</span></td>");
			}
		}
		// 그달의 날짜 출력
		for(int i = 1 ; i <= MyCalandar.lastDay(year, month)  ; i++){
			if(lunarDate.get(i-1).getLunar().length()==0){
				switch(MyCalandar.weekDay(year, month, i)){
					case 0 : // 일요일
						out.println("<td class ='nowSun'><span>" + String.format("%02d", i) + "</span></td>");
						break;	
					case 6 : // 토요일
						out.println("<td class ='nowSat'><span>" + String.format("%02d", i) + "</span></td>");
						break;	
					default:
						out.println("<td class ='now'><span>" + String.format("%02d", i) + "</span></td>");
						break;	
				}
			}else{
				out.println("<td class='holiday'>" + i + lunarDate.get(i-1).getLunar()+"</td>");
			}
			if(MyCalandar.weekDay(year, month, i) == 6 && i != MyCalandar.lastDay(year, month)){
				out.println("</tr><tr>");
			}
		}
		// 빈칸에 다음달 날짜를 출력
		// 다음달 1일의 날짜 계산
		if(month == 12){
			week = MyCalandar.weekDay(year+1, 1, 1);
		}else{
			week = MyCalandar.weekDay(year, month+1, 1);
		}
		if(week != 0){// 다음달의 처음 시작하는 날이 일요일만 아니면 다 출력
			start = 0;
			for(int i = week ; i < 7 ; i ++){
				if(i == 6){ // 토요일
					out.println("<td class ='nextSat'>" +(month == 12 ? 1 : month+1)+ "/"+ ++start+ "</td>");
				}else{
					out.println("<td class ='next'>" +(month == 12 ? 1 : month+1)+ "/"+ ++start+"</td>");
				}
			}
		}
	%>
	</tr>
	<tr>
		<td colspan="7" class="selectDate">
			<div>
				<form action="?" method="post">
					<select class ="select" name="year">
					<%
						for(int i = 1900; i < 3001 ; i++){
							if(year == i){
								out.println("<option selected='selected'>" +i +"</selected>");
							}else{
								out.println("<option>" +i +"</selected>");
							}
						}
					%>
					</select> 년 &nbsp;
					<select class ="select" name="month">
					<%
						for(int i = 1; i < 13 ; i++){
							if(month == i){
								out.println("<option selected='selected'>" +i +"</selected>");
							}else{
								out.println("<option>" +i +"</selected>");
							}
						}
					%>
					</select> 월&nbsp;
					<input type="submit" value="달력보러가기"">
				</form>
			</div>
		</td>
	</tr>
</table>
</body>
</html>