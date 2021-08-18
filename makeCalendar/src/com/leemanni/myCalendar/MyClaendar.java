package com.leemanni.myCalendar;

public class MyClaendar {
	//# 1 윤년평년 판별
	public static boolean isLeapYear(int year) {
		return year % 4 == 0 && year % 100 != 0 || year % 400 ==0;
	}
	//# 2 해당 달의 총 날을 구해주는 메소드
	public static int lastday(int year, int month) {
		int [] m = {31,28,31,30,31,30,31,31,30,31,30,31};
		if(isLeapYear(year)) {
			m[1] = 29;
		}
		return m[month-1];
	}
	//# 1 지금 까지의 날을 계산해주는 메소드
	public static int totalDay(int year, int month, int day) {
		int total = 365 * (year -1) + (year-1) / 4 - (year -1)/100 + (year-1)/400;
		for (int i = 1; i < month; i++) {
			total += lastday(year, i);
		}
		return total + day;
	}
//	# 4 요일을 계산해주는 메소드
	public static int weekDay(int year, int month, int day) {
		return totalDay(year, month, day) % 7;
	}
	
	public static void main(String[] args) {
		System.out.println(totalDay(2021, 8, 18) % 7);
	}
}
