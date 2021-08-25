package com.leemanni.myCalendar;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * 
 * @author leemanni
 * 월별 양력과 음력을 크롤링하고 양력, 음력 공휴일을 리턴하는 클래스, 메소드
 * 
 * 
 */
public class SolorToLunar {
	/**
	 * @author leemanni
	 * 
	 * 월별 양력과 음력을 크롤링 하고 양력, 음력 공휴일을 계산해서 리턴
	 * 
	 * @return ArrayList
	 */
	public static ArrayList<LunarDate> solaToLunar(int year, int month) {
		// 1 ~ 12 월 양력 음력 데이터를 저장하는 리스트
		ArrayList<LunarDate> lunarList = new ArrayList<>();
		// 인수로 넘겨받은 월의 양력에 대응되는 음력을 저장해 리턴
		ArrayList<LunarDate> list = new ArrayList<>();
		String targetSite= "";
		
//		인수로 넘겨받은 year에 해당하는 1년치 음력 크롤링
		for (int i = 1; i <= 12; i++) {
			targetSite = String.format("https://astro.kasi.re.kr/life/pageView/5?search_year=%04d&search_month=%02d",
					year, i);
			
//			크롤링 데이터를 가공할 jsoup Documts 를 선언
			Document document = null;
			
			try {
				document = Jsoup.connect(targetSite).get();
				Elements elements = document.select("tbody > tr");
				
				for (Element element : elements) {
					Elements ele = element.select("td");
					//System.out.println(ele);
					String solor = ele.get(0).text();  // 양력날짜 저장
					String lunar = ele.get(1).text();	// 음력날짜 저장
					
					// 양력 음력 분류 하고 LunarDate 객체 생성후 날짜데이터 저장
					LunarDate lunarDate= new LunarDate();
					lunarDate.setYear(Integer.parseInt(solor.split("-")[0]));
					lunarDate.setMonth(Integer.parseInt(solor.split("-")[1]));
					lunarDate.setDay(Integer.parseInt(solor.split("-")[2].substring(0,2)));
					
					lunarDate.setLunarFlag(lunar.length()>10 ? true : false);
					
					lunarDate.setYearLunar(Integer.parseInt(lunar.split("-")[0]));
					lunarDate.setMonthLunar(Integer.parseInt(lunar.split("-")[1]));
					lunarDate.setDayLunar(Integer.parseInt(lunar.split("-")[2].substring(0, 2)));
					
					//System.out.println(lunarDate.toString());
					
					// 1년치 날짜 저장 후 1~12 월 날짜 저장하는 리스트에 LunarDate 객체 저장
					lunarList.add(lunarDate);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// 공휴일 처리
		for (int i = 0; i < lunarList.size(); i++) {
			
			boolean check = false; // 대체 공휴일을 지정해야하는 지 여부
			
//			양력 공휴일
			if (lunarList.get(i).getMonth() == 1 && lunarList.get(i).getDay() == 1) {
				lunarList.get(i).setLunar("<br><span>신정</span>");
			} else if (lunarList.get(i).getMonth() == 3 && lunarList.get(i).getDay() == 1) {
				check = true;
				lunarList.get(i).setLunar("<br><span>삼일절</span>");
			} else if (lunarList.get(i).getMonth() == 5 && lunarList.get(i).getDay() == 1) {
				lunarList.get(i).setLunar("<br><span>근로자의 날</span");
			} else if (lunarList.get(i).getMonth() == 5 && lunarList.get(i).getDay() == 5) {
				check = true;
				lunarList.get(i).setLunar("<br><span>어린이 날</span>");
			} else if (lunarList.get(i).getMonth() == 6 && lunarList.get(i).getDay() == 6) {
				check = true;
				lunarList.get(i).setLunar("<br><span>현충일</span>");
			} else if (lunarList.get(i).getMonth() == 6 && lunarList.get(i).getDay() == 17) {
				lunarList.get(i).setLunar("<br><span>내 생일</span>");
			} else if (lunarList.get(i).getMonth() == 8 && lunarList.get(i).getDay() == 15) {
				lunarList.get(i).setLunar("<br><span>광복절</span>");
				check = true;
			} else if (lunarList.get(i).getMonth() == 10 && lunarList.get(i).getDay() == 3) {
				check = true;
				lunarList.get(i).setLunar("<br><span>개천절</span>");
			} else if (lunarList.get(i).getMonth() == 10 && lunarList.get(i).getDay() == 9) {
				check = true;
				lunarList.get(i).setLunar("<br><span>한글날</span>");
			} else if (lunarList.get(i).getMonth() == 10 && lunarList.get(i).getDay() == 26) {
				lunarList.get(i).setLunar("<br><span>옥이 생일</span>");
			} else if (lunarList.get(i).getMonth() == 12 && lunarList.get(i).getDay() == 25) {
				lunarList.get(i).setLunar("<br><span>크리스마스</span>");
			}

//			음력 공휴일 지정(추석, 석가 탄신일, 설날)
			if (!lunarList.get(i).isLunarFlag() && lunarList.get(i).getMonthLunar() == 1
					&& lunarList.get(i).getDayLunar() == 1) {
				lunarList.get(i - 1).setLunar("<br><span>설날연휴</span>");
				lunarList.get(i).setLunar("<br><span>설날</span>");
				lunarList.get(i + 1).setLunar("<br><span>설날연휴</span>");
			} else if (!lunarList.get(i).isLunarFlag() && lunarList.get(i).getMonthLunar() == 4
					&& lunarList.get(i).getDayLunar() == 8) {
				lunarList.get(i).setLunar("<br><span>석가 탄신일</span>");
			} else if (!lunarList.get(i).isLunarFlag() && lunarList.get(i).getMonthLunar() == 8
					&& lunarList.get(i).getDayLunar() == 15) {
				lunarList.get(i - 1).setLunar("<br><span>추석 연휴</span>");
				lunarList.get(i).setLunar("<br><span>추석</span>");
				lunarList.get(i + 1).setLunar("<br><span>추석 연휴</span>");
			}
			
//			대체공휴일 => 설, 삼일절, 어린이날, 광복절, 추석, 개천절, 한글날, 주말(토, 일)이나 다른 공휴일과 겹치면
//					그 다음 첫번째 비 공휴일을 대체 공휴일로 한다
			int week = MyCalandar.weekDay(lunarList.get(i).getYear(), lunarList.get(i).getMonth(),
					lunarList.get(i).getDay());
			int index = i;
			if (check) {
				if (week == 6) {
					index = i + 2;
					lunarList.get(index).setLunar("<br><span>대체공휴일</span>");
				} else if (week == 0) {
					index = i + 1;
					lunarList.get(index).setLunar("<br><span>대체공휴일</span>");
				}
			}
			
//			어린이 날과 석가탄신일이 겹쳤을 때 , 대체공휴일
			if (!lunarList.get(i).isLunarFlag() && lunarList.get(i).getMonthLunar() == 4
					&& lunarList.get(i).getDayLunar() == 8 && lunarList.get(i).getMonth() == 5
					&& lunarList.get(i).getDay() == 5) {
				lunarList.get(i).setLunar("<br><span>어린이날</span><br><span>석가탄신일</span>");
				index = i;
				if (week == 6) {
					index = i + 2;
				} else if (week == 0) {
					index = i + 1;
				}
				lunarList.get(index).setLunar("<br><span>대체공휴일</span>");
			}

//			설 , 추석 대체 공휴일
			if (!lunarList.get(i).isLunarFlag()
					&& (lunarList.get(i).getMonthLunar() == 8 && lunarList.get(i).getDayLunar() == 15)
					|| (lunarList.get(i).getMonthLunar() == 1 && lunarList.get(i).getDayLunar() == 1)) {
				if (week == 6 || week == 0) {
					lunarList.get(i + 2).setLunar("<br><span>대체공휴일</span>");
				}
			}

//			추석과 개천절이 겹칩때
			if (lunarList.get(i).getMonth() == 10 && lunarList.get(i).getDay() == 3
					&& lunarList.get(i).getMonthLunar() == 8 && lunarList.get(i).getDayLunar() == 15) {
				lunarList.get(i + 2).setLunar("<br><span>대체공휴일</span>");
			}

		}
//			1년치 데이터에서 각 달의 날짜만을 담아줌
		for (int j = 0; j < lunarList.size(); j++) {
			//System.out.println(lunarList.get(j));
			if(lunarList.get(j).getMonth() == month){
				list.add(lunarList.get(j));
			}
		}
		
		return list;
	}
}
