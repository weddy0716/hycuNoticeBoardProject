package com.sinho.hycu.boardNotice.service;

import javax.servlet.http.HttpServletRequest;

import com.sinho.hycu.boardNotice.repository.BoardListRepository;
import com.sinho.hycu.boardNotice.vo.BoardListMgt;
import com.sinho.hycu.common.util.PasswordCrypto;
import com.sinho.hycu.framework.session.SessionManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardListService {
	private final BoardListRepository boardListRepository;
	
	public BoardListService(BoardListRepository boardListRepository) {
		log.info("###LoginService");
		this.boardListRepository = boardListRepository;
	}
	
	/**
	 * 게시글 등록
	 * @param request
	 * @param boardListMgt
	 * @return
	 */
	public int insertNoticeBoardList(HttpServletRequest request ,BoardListMgt boardListMgt) {
		String userid = String.valueOf(SessionManager.getSession(request, "userid"));
		String password = boardListMgt.getPassword();
		boardListMgt.setPassword(PasswordCrypto.SHA256(password));
		boardListMgt.setUserid(userid);
		int result = this.boardListRepository.insertNoticeBoardList(boardListMgt);
		log.info("###PSH insertNoticeBoardList result : {}" , result);
		return result;
	}
}
