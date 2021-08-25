package com.leemanni.myCalendar;

// 달력 작업에 필요한 메소드를 모아놓은 클래스
public class MyCalandar {
// 년을 인수로 념겨받아 윤년 평년 true ,false 로 리턴하는 메소드
	public static boolean isLeapYear(int year) {
		return year %4 ==0 && year % 100 != 0 || year % 400 == 0 ;
	}
//	년 월을 넘겨받아 그 달의 마지막 날짜를 리턴하는 메소드,
	public static int lastDay(int year , int month) {
		
		int [] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
			days[1] = isLeapYear(year)? 29 :28;
			return days[month-1];
	}
	
//	년, 월, 일 을 인수로 넘겨받아 1년 1월 1일 부터 지나온 날짜의 합계를 리턴하는 메소드
	public static int totalDays(int year, int month, int day) {
		int total = 0;
		total = 365 * (year-1) + (year-1) / 4 - (year-1) / 100 + (year-1) / 400;
		for (int i = 1; i < month; i++) {
			total += lastDay(year, i);
		}
		return total + day;
	}
//	년, 월, 일을 인수로 넘겨받아 요일을 숫자로 리턴하는 메소드
//	일요일(0) 월요일(1) ==> 
	public static int weekDay(int year, int month, int day) {
		return totalDays(year, month, day) % 7;
	}
}
