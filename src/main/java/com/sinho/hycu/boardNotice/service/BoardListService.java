package com.sinho.hycu.boardNotice.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sinho.hycu.boardNotice.repository.BoardListRepository;
import com.sinho.hycu.boardNotice.vo.BoardListMgt;
import com.sinho.hycu.common.util.BoardContentsCrypto;
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
	 * @throws Exception 
	 */
	public int insertNoticeBoardList(HttpServletRequest request ,BoardListMgt boardListMgt) throws Exception {
		String userid   = String.valueOf(SessionManager.getSession(request, "userid"));
		String fullName = String.valueOf(SessionManager.getSession(request, "fullName"));
		String password = boardListMgt.getPassword();
		String subject  = boardListMgt.getSubject();
		String contents  = boardListMgt.getContents();
		boardListMgt.setPassword(PasswordCrypto.SHA256(password))
					.setUserid(userid)
					.setFullName(fullName)
					.setSubject(BoardContentsCrypto.encrypt(subject))
					.setContents(BoardContentsCrypto.encrypt(contents));
		int result = this.boardListRepository.insertNoticeBoardList(boardListMgt);
		log.info("###PSH insertNoticeBoardList result : {}" , result);
		return result;
	}
	
	/**
	 * 게시판목록조회
	 * @return
	 * @throws Exception 
	 */
	public List<BoardListMgt> selectNoticeBoardList() throws Exception{
		List<BoardListMgt> list = this.boardListRepository.selectNoticeBoardList();
		for(BoardListMgt b : list) {
			String subject  = b.getSubject();
			String contents  = b.getContents();
			b.setSubject(BoardContentsCrypto.decrypt(subject))
			 .setContents(BoardContentsCrypto.decrypt(contents));
		}
		
		log.info("###PSH list : {}" , list);
		return list;
	}
	
	/**
	 * 게시판상세조회
	 * @return
	 * @throws Exception 
	 */
	public BoardListMgt selectNoticeBoardDetail(BoardListMgt boardListMgt) throws Exception{
		BoardListMgt boardListDetail = this.boardListRepository.selectNoticeBoardDetail(boardListMgt);
		String subject  = boardListDetail.getSubject();
		String contents  = boardListDetail.getContents();
		boardListDetail.setSubject(BoardContentsCrypto.decrypt(subject)).setContents(BoardContentsCrypto.decrypt(contents));

		log.info("###PSH selectNoticeBoardDetail : {}" , boardListDetail);
		return boardListDetail;
	}
	
	/**
	 * 게시글 수정
	 * @param boardListMgt
	 * @return
	 * @throws Exception 
	 */
	public int UpdateNoticeBoardDetail(BoardListMgt boardListMgt) throws Exception{
		String subject  = boardListMgt.getSubject();
		String contents  = boardListMgt.getContents();
		boardListMgt.setSubject(BoardContentsCrypto.encrypt(subject)).setContents(BoardContentsCrypto.encrypt(contents));
		int result = this.boardListRepository.UpdateNoticeBoardDetail(boardListMgt);
		return result;
	}
	
	/**
	 * 게시글 삭제(화면에만 비노출) 
	 * @param boardListMgt
	 * @return
	 */
	public int DeleteNoticeBoardList(BoardListMgt boardListMgt){
		int result = this.boardListRepository.DeleteNoticeBoardList(boardListMgt);
		return result;
	}
}
