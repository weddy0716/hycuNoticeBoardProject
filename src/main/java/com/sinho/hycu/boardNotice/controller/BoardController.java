package com.sinho.hycu.boardNotice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinho.hycu.boardNotice.service.BoardListService;
import com.sinho.hycu.boardNotice.vo.BoardListMgt;
import com.sinho.hycu.framework.exception.NoticeBoardException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	private BoardListService boardListService;
	
	public BoardController( BoardListService boardListService )
	{
		this.boardListService = boardListService;
	}
	
	@RequestMapping("/board/boardInsertForm")
	public ModelAndView boardInsertForm(HttpServletRequest request, BoardListMgt boardMgt ,  Model model) {
		ModelAndView mv = new ModelAndView();
		log.info("###PSH getRemoteHost : {}" , request.getRemoteHost());
		log.info("###PSH getRemoteAddr : {}" , request.getRemoteAddr());
		mv.setViewName("/board/boardInsertForm.tiles");
		return mv;
	}
	
	@RequestMapping("/board/boardDetailForm")
	public ModelAndView boardDetailForm(HttpServletRequest request, BoardListMgt boardMgt ,  Model model) {
		ModelAndView mv = new ModelAndView();
		log.info("###PSH getRemoteHost : {}" , request.getRemoteHost());
		log.info("###PSH getRemoteAddr : {}" , request.getRemoteAddr());
		mv.setViewName("/board/boardDetailForm.tiles");
		BoardListMgt boardListDetail = boardListService.selectNoticeBoardDetail(boardMgt);
		mv.addObject("detail", boardListDetail);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/board/boardInsert.act")
	public String boardInsert(HttpServletRequest request , BoardListMgt boardListMgt , Model model) {
		log.info("###PSH boardListMgt : {}" , boardListMgt);
		int result = boardListService.insertNoticeBoardList(request, boardListMgt);
		return String.valueOf(result);
	}
	
	@ResponseBody
	@RequestMapping("/board/boardDetailUpdate.act")
	public String boardDetailUpdate(HttpServletRequest request , BoardListMgt boardListMgt , Model model) {
		log.info("###PSH boardListMgt : {}" , boardListMgt);
		int result = boardListService.UpdateNoticeBoardDetail(boardListMgt);
		return String.valueOf(result);
	}
	
	@RequestMapping("/board/boardlist")
	public ModelAndView boardlistForm(HttpServletRequest request, BoardListMgt boardMgt ,  Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/boardlistForm.tiles");
		List<BoardListMgt> list = boardListService.selectNoticeBoardList();
		log.info("###PSH boardlistForm : {}" , list );
		mv.addObject("boardlist" , list);
		return mv;
	}
	
	@RequestMapping("/board/boardModifyForm")
	public ModelAndView boardModifyForm(HttpServletRequest request, BoardListMgt boardMgt ,  Model model) {
		ModelAndView mv = new ModelAndView();
		log.info("###PSH getRemoteHost : {}" , request.getRemoteHost());
		log.info("###PSH getRemoteAddr : {}" , request.getRemoteAddr());
		BoardListMgt boardListDetail = boardListService.selectNoticeBoardDetail(boardMgt);
		mv.setViewName("/board/boardModifyForm.tiles");
		mv.addObject("detail", boardListDetail);
		return mv;
	}
	
	/**
	 * 에러처리진행
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(NoticeBoardException.class)
    @ResponseBody
    public ResponseEntity<String> handleCustomException(NoticeBoardException ex) {
        // CustomException이 발생했을 때 처리하는 메서드
		if(!ex.getErrorCode().equals("")) {
			return ResponseEntity.status(ex.getStatus()).body(ex.getMessage() + "||" +ex.getErrorCode());
		}else {
			return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
		}
        
    }
}
