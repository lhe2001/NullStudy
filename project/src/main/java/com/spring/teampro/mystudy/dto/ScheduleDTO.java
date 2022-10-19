package com.spring.teampro.mystudy.dto;

import java.util.Date;

public class ScheduleDTO {

	private int m_schedule_key;
	private int userkey;
	private String m_schedule_title;
	private String m_schedule_desc;
	private Date m_schedule_date;
	

	
	public int getM_schedule_key() {
		return m_schedule_key;
	}
	public void setM_schedule_key(int m_schedule_key) {
		this.m_schedule_key = m_schedule_key;
	}
	public int getUserkey() {
		return userkey;
	}
	public void setUserkey(int userkey) {
		this.userkey = userkey;
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
	public Date getM_schedule_date() {
		return m_schedule_date;
	}
	public void setM_schedule_date(Date m_schedule_date) {
		this.m_schedule_date = m_schedule_date;
	}
	
	@Override
	public String toString() {
		return "ScheduleDTO [m_schedule_key=" + m_schedule_key + ", userkey=" + userkey + ", m_schedule_title="
				+ m_schedule_title + ", m_schedule_desc=" + m_schedule_desc + ", m_schedule_date=" + m_schedule_date
				+ "]";
	}
	
}
