<%@page import="com.leemanni.Ex.PollWrite"%>
<%@page import="com.leemanni.Ex.ReadPoll"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String temp = request.getParameter("poll");
		// 오류 체크 확인
		//1. 넘어온 데이터 index 가 투표 문항인지 확인
		//2. 데이터가 넘어오는지 확인
		//3. 넘어온 데이터가 숫자가 아니경우
	if(temp != null && temp.length() !=0 ){ // 2번 오류
		try{
			int result = Integer.parseInt(temp);
			String filePath = application.getRealPath("/") + "poll.txt";
			ArrayList<String> pollList = ReadPoll.readPoll(filePath);
			int itemCount = (pollList.size() -1) /2;
			if(result <= itemCount || result >0){
				int index = result + itemCount;
				result = Integer.parseInt(pollList.get(index)) + 1;
				pollList.set(index, result+"");
				PollWrite.pollWrite(filePath, pollList);
				response.sendRedirect("resultPoll.jsp");
			}else{
				out.println("<script>");
				out.println("alert('데이터범위를 초과했습니다')");
				out.println("location.href='readPoll.jsp'");	// 메세지 보여주고 다른 페이지로 넘길때 자바 스크립트를 이용한다.
				out.println("</script>");
			}
		}catch(NumberFormatException e){ // 3번 오류 체크
			out.println("<script>");
			out.println("alert('데이터가 숫자가 아닙니다')");
			out.println("location.href='readPoll.jsp'");	// 메세지 보여주고 다른 페이지로 넘길때 자바 스크립트를 이용한다.
			out.println("</script>");
		}
	}else{
		out.println("<script>");
		out.println("alert('데이터가 넘어오지 않았습니다.')");
		out.println("location.href='readPoll.jsp'");	// 메세지 보여주고 다른 페이지로 넘길때 자바 스크립트를 이용한다.
		out.println("</script>");
	}
%>
</body>
</html>