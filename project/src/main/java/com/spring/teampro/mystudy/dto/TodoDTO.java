package com.spring.teampro.mystudy.dto;

import java.sql.Date;

public class TodoDTO {
	
	private int userkey;
	private int m_td_key;	
	private String m_td_desc;
	private Date m_td_date;
	
	public TodoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TodoDTO(int userkey, int m_td_key, String m_td_desc, Date m_td_date) {
		super();
		this.userkey = userkey;
		this.m_td_key = m_td_key;
		this.m_td_desc = m_td_desc;
		this.m_td_date = m_td_date;
	}

	public int getUserkey() {
		return userkey;
	}

	public void setUserkey(int userkey) {
		this.userkey = userkey;
	}

	public int getM_td_key() {
		return m_td_key;
	}

	public void setM_td_key(int m_td_key) {
		this.m_td_key = m_td_key;
	}

	public String getM_td_desc() {
		return m_td_desc;
	}

	public void setM_td_desc(String m_td_desc) {
		this.m_td_desc = m_td_desc;
	}

	public Date getM_td_date() {
		return m_td_date;
	}

	public void setM_td_date(Date m_td_date) {
		this.m_td_date = m_td_date;
	}

	@Override
	public String toString() {
		return "TodoDTO [userkey=" + userkey + ", m_td_key=" + m_td_key + ", m_td_desc=" + m_td_desc + ", m_td_date="
				+ m_td_date + "]";
	}
	
	
	
}
