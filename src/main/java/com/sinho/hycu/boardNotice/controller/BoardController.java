package com.sinho.hycu.boardNotice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinho.hycu.boardNotice.vo.BoardMgt;
import com.sinho.hycu.framework.exception.NoticeBoardException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
	
	@RequestMapping("/board/boardInsertForm")
	public ModelAndView userLogin(HttpServletRequest request, BoardMgt boardMgt ,  Model model) {
		ModelAndView mv = new ModelAndView();
		log.info("###PSH getRemoteHost : {}" , request.getRemoteHost());
		log.info("###PSH getRemoteAddr : {}" , request.getRemoteAddr());
		mv.setViewName("/board/boardInsertForm.tiles");
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
