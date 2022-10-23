package com.spring.teampro.board.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("boardDTO")
public class BoardDTO extends PageDTO {
	// Board 테이블의 필드값
	private int level; // 요녀석은 계층형으로 만들기 때문에 필요하다...ㅠ
	private int b_key; // 아마도 ?시퀀스로 때울 게시판 키
	private int b_articleNo; // foreach에서 varstatus.count로 사용
	private int b_parentNo; // 디폴트값 0
	private String b_title; // 글 제목 String타입
	private String b_writer; // 글 작성자 String타입
	private String b_content; // 글 내용 String타입
	private String b_fieldName; // 변수로만 사용할 필드(int)값을 (String)으로 변환하여 사용할 값
	private int list_sel; // 변수로만 사용할 필드(int)값을 (String)으로 변환하여 사용할 값
	private int b_field; // 분야인데 10,20,30,40일 경우 select박스로 value값 가져올듯?
	private Date b_writeDate; // 디폴트값 sysdate
	private int b_view; // 조회수...
	private int userkey; // 회원정보 테이블에 있는 녀석을 사용할 변수
	private String b_imageFile; // 이미지 파일 이름
	private String search_bar; // 검색창에 쓸 변수
	private String nickName; // 작성자에 쓸 변수
	private int search_field; // 검색 내용을 담아서 전달할 변수
	private int b_articlePwd; // 비밀글 비밀번호

	public int getB_articlePwd() {
		return b_articlePwd;
	}

	public void setB_articlePwd(int b_articlePwd) {
		this.b_articlePwd = b_articlePwd;
	}

	public int getSearch_field() {
		return search_field;
	}

	public void setSearch_field(int search_field) {
		this.search_field = search_field;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSearch_bar() {
		return search_bar;
	}

	public void setSearch_bar(String search) {
		this.search_bar = search;
	}

	// 그냥 생성자로 쓸때
	public BoardDTO() {

	}

	// 생성자를 변수에 넣어서 쓰고싶을때
	public BoardDTO(int level, int b_key, int b_articleNo, int b_parentNo, String b_writer, String b_title,
			String b_content, int b_field, Date b_writeDate, int b_view, int userkey, String b_imageFile) {
		super();
		this.level = level;
		this.b_key = b_key;
		this.b_articleNo = b_articleNo;
		this.b_parentNo = b_parentNo;
		this.b_title = b_title;
		this.b_writer = b_writer;
		this.b_content = b_content;
		this.b_field = b_field;
		this.b_writeDate = b_writeDate;
		this.b_view = b_view;
		this.userkey = userkey;
		this.b_imageFile = b_imageFile;
	}

	// getter 와 setter 생성

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the b_key
	 */
	public int getB_key() {
		return b_key;
	}

	/**
	 * @param b_key the b_key to set
	 */
	public void setB_key(int b_key) {
		this.b_key = b_key;
	}

	/**
	 * @return the b_articleNo
	 */
	public int getB_articleNo() {
		return b_articleNo;
	}

	/**
	 * @param b_articleNo the b_articleNo to set
	 */
	public void setB_articleNo(int b_articleNo) {
		this.b_articleNo = b_articleNo;
	}

	/**
	 * @return the b_parentNo
	 */
	public int getB_parentNo() {
		return b_parentNo;
	}

	/**
	 * @param b_parentNo the b_parentNo to set
	 */
	public void setB_parentNo(int b_parentNo) {
		this.b_parentNo = b_parentNo;
	}

	/**
	 * @return the b_title
	 */
	public String getB_title() {
		return b_title;
	}

	/**
	 * @param b_title the b_title to set
	 */
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	/**
	 * @return the b_content
	 */
	public String getB_content() {
		return b_content;
	}

	/**
	 * @param b_content the b_content to set
	 */
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	/**
	 * @return the b_field
	 */
	public int getB_field() {
		return b_field;
	}

	/**
	 * @param b_field the b_field to set
	 */
	public void setB_field(int b_field) {
		this.b_field = b_field;
	}

	/**
	 * @return the b_writeDate
	 */
	public Date getB_writeDate() {
		return b_writeDate;
	}

	/**
	 * @param b_writeDate the b_writeDate to set
	 */
	public void setB_writeDate(Date b_writeDate) {
		this.b_writeDate = b_writeDate;
	}

	/**
	 * @return the b_view
	 */
	public int getB_view() {
		return b_view;
	}

	/**
	 * @param b_view the b_view to set
	 */
	public void setB_view(int b_view) {
		this.b_view = b_view;
	}

	/**
	 * @return the userkey
	 */
	public int getUserkey() {
		return userkey;
	}

	/**
	 * @param userkey the userkey to set
	 */
	public void setUserkey(int userkey) {
		this.userkey = userkey;
	}

	/**
	 * @return the b_imageFile
	 */
	public String getB_imageFile() {
		return b_imageFile;
	}

	/**
	 * @param b_imageFile the b_imageFile to set
	 */
	public void setB_imageFile(String b_imageFile) {
		this.b_imageFile = b_imageFile;
	}

	/**
	 * @return the b_writer
	 */
	public String getB_writer() {
		return b_writer;
	}

	/**
	 * @param b_writer the b_writer to set
	 */
	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	/**
	 * @return the b_fieldName
	 */
	public String getB_fieldName() {
		return b_fieldName;
	}

	/**
	 * @param b_fieldName the b_fieldName to set
	 */
	public void setB_fieldName(String b_fieldName) {
		this.b_fieldName = b_fieldName;
	}

	public int getList_sel() {
		return list_sel;
	}

	public void setList_sel(int list_sel) {
		this.list_sel = list_sel;
	}

}
