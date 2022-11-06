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
	private CommentService commentService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	HttpSession session;
	// 댓글 추가
	@RequestMapping(value = "board/addComment.do" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody List<CommentDTO> addComment(HttpServletRequest requset, HttpServletResponse response,
			@RequestBody CommentDTO commentDTO){
		// 댓글을 아작스로 처리할껀데,,
		// 댓글 파라미터를 받아서 리스트에 담아 다시 던져줄거야..
		// 댓글 쓸때 b_key에 따른 댓글 갯수 증가시켜서 
		
		
		System.out.println("댓글 추가 아작스 처리");
		int result = commentService.getAddComment(commentDTO);
		System.out.println("commentController result = " + result);
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		list = commentService.getSelectComment(commentDTO);
		System.out.println("commentController list.size() = " + list.size());
		return list;
		// 전달받아야 될 정보 : b_c_comment(내용), b_key(board dto의 b_key), userkey
		
	}
	// 댓글 삭제
	@RequestMapping(value = "board/deleteComment.do" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody void deleteComment(HttpServletRequest requset, HttpServletResponse response,
			@RequestBody CommentDTO commentDTO){
		System.out.println("댓글 삭제 아작스 처리");
		System.out.println("deleteComment commentDTO = " + commentDTO);
		commentService.getDeleteComment(commentDTO);
		return;
	}
	
	// 댓글 수정
	@RequestMapping(value = "board/updateComment.do" , method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody void updateComment(HttpServletRequest requset, HttpServletResponse response,
			@RequestBody CommentDTO commentDTO){
		System.out.println("댓글 수정 아작스 처리");
		System.out.println("deleteComment commentDTO = " + commentDTO);
		commentService.getUpdateComment(commentDTO);
		return;
	}
}
