package com.spring.teampro.board.dto;

import java.sql.Date;

public class CommentDTO {
	//댓글 테이블 필드값
	private int b_c_key; // 댓글 기본키
	private String b_c_comment; // 댓글 내용
	private Date b_c_date; // 작성 일자
	private int b_key; // 게시판 테이블 기본키(여기선 외래키)
	private int userKey; // 회원 정보 테이블 기본키(여기선 외래키)
	private String nickName; // 작성자에 쓸 변수
	private int comment_cnt;  // 댓글 갯수
	
	public int getB_c_key() {
		return b_c_key;
	}
	public void setB_c_key(int b_c_key) {
		this.b_c_key = b_c_key;
	}
	public String getB_c_comment() {
		return b_c_comment;
	}
	public void setB_c_comment(String b_c_comment) {
		this.b_c_comment = b_c_comment;
	}
	public Date getB_c_date() {
		return b_c_date;
	}
	public void setB_c_date(Date b_c_date) {
		this.b_c_date = b_c_date;
	}
	public int getB_key() {
		return b_key;
	}
	public void setB_key(int b_key) {
		this.b_key = b_key;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getCount_com() {
		return comment_cnt;
	}
	public void setCount_com(int count_com) {
		this.comment_cnt = count_com;
	}
	public int getComment_cnt() {
		return comment_cnt;
	}
	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}
}
