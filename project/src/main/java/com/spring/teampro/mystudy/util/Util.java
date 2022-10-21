package com.spring.teampro.mystudy.util;

public class Util {
	
	//요일 폰트컬러
	public static String fontColor(int dayOfWeek, int i){
		String color="";
		if((dayOfWeek-1+i)%7==0){//토요일인 경우
				color="blue";
			} else if((dayOfWeek-1+i)%7==1){//일요일인 경우
					color="red";
			} else {
				color="#353866";
			}
		
		return color;
	
	}
	
	//날짜 1자리 2자리로 변환
	public static String isTwo(String msg) {
		
		return msg.length()<2?"0"+msg:msg;
		
	}
}
