package com.spring.teampro.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.teampro.board.dao.CommentDAO;
import com.spring.teampro.board.dto.CommentDTO;
import com.spring.teampro.board.service.BoardService;
import com.spring.teampro.board.service.CommentService;

@Controller("commentController")
public class CommentController {
	@Autowired
	private CommentService CommentService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "board/addComment.do" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<CommentDTO> ajaxComment(HttpServletRequest requset, HttpServletResponse response,
			@RequestBody CommentDTO commentDTO){
		// 댓글을 아작스로 처리할껀데,,
		// 댓글 파라미터를 받아서 리스트에 담아 다시 던져줄거야..
		System.out.println("댓글 추가 아작스 처리");
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		return list;
	}
}
