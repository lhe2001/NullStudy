package com.spring.teampro.mystudy.dto;

import java.sql.Date;

public class ScheduleDTO {

	private int userkey;
	private String m_schedule_key;
	private String m_schedule_title;
	private String m_schedule_desc;
	private String m_schedule_date;	
	private Date m_schedule_write;
	
	//날짜 정보 파라미터 받을 용도
	private String year;
	private String month;
	private String date;
	
	public ScheduleDTO(int userkey, String m_schedule_key, String m_schedule_title, String m_schedule_desc,
			String m_schedule_date, Date m_schedule_write, String year, String month, String date) {
		super();
		this.userkey = userkey;
		this.m_schedule_key = m_schedule_key;
		this.m_schedule_title = m_schedule_title;
		this.m_schedule_desc = m_schedule_desc;
		this.m_schedule_date = m_schedule_date;
		this.m_schedule_write = m_schedule_write;
		this.year = year;
		this.month = month;
		this.date = date;
	}

	public ScheduleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getUserkey() {
		return userkey;
	}

	public void setUserkey(int userkey) {
		this.userkey = userkey;
	}

	public String getM_schedule_key() {
		return m_schedule_key;
	}

	public void setM_schedule_key(String m_schedule_key) {
		this.m_schedule_key = m_schedule_key;
	}

	public String getM_schedule_title() {
		return m_schedule_title;
	}

	public void setM_schedule_title(String m_schedule_title) {
		this.m_schedule_title = m_schedule_title;
	}

	public String getM_schedule_desc() {
		return m_schedule_desc;
	}

	public void setM_schedule_desc(String m_schedule_desc) {
		this.m_schedule_desc = m_schedule_desc;
	}

	public String getM_schedule_date() {
		return m_schedule_date;
	}

	public void setM_schedule_date(String m_schedule_date) {
		this.m_schedule_date = m_schedule_date;
	}

	public Date getM_schedule_write() {
		return m_schedule_write;
	}

	public void setM_schedule_write(Date m_schedule_write) {
		this.m_schedule_write = m_schedule_write;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ScheduleDTO [userkey=" + userkey + ", m_schedule_key=" + m_schedule_key + ", m_schedule_title="
				+ m_schedule_title + ", m_schedule_desc=" + m_schedule_desc + ", m_schedule_date=" + m_schedule_date
				+ ", m_schedule_write=" + m_schedule_write + ", year=" + year + ", month=" + month + ", date=" + date
				+ "]";
	}
	
	
}
