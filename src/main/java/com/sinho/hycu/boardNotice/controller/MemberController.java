package com.sinho.hycu.boardNotice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sinho.hycu.boardNotice.service.MemberService;
import com.sinho.hycu.boardNotice.vo.Member;
import com.sinho.hycu.framework.exception.NoticeBoardException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {
	
	private MemberService memberService;
	
	/**
	 * 의존성 생성자 주입
	 * 의존관계가 어플리케이션 실행중에는 변동되는일이 거의 없으므로 해당 방식을 이용하자.
	 * Spring Config에서 주입한 객체를 리턴받는다.
	 * @param memberService
	 */
	public MemberController( MemberService memberService )
	{
		this.memberService = memberService;
		log.info("###ModelAndView JspViewController memberService : {} " , memberService.hashCode());
	}
	
	@RequestMapping(value={"/member/insertMemberForm"})
	public ModelAndView memberInsertForm(Model model) {
		ModelAndView mv = new ModelAndView();
		log.info("##PSH join/memberJoin Call");
		mv.setViewName("/member/insertMemberForm.view");
		return mv;
	}
	@RequestMapping(value={"/" , "/index"})
    private ModelAndView main(HttpServletRequest request, Model model) {
		ModelAndView mv = new ModelAndView();
		// 세션에 저장된 id가 없으므로 로그인 화면으로 이동
		mv.setViewName("/login/loginForm.view");
		return mv;
    }
	
	//파라미터를 객체에 맵핑시킬때 선언해준다.
	//객체로 받지않는경우에는 HttpServletRequest 객체에서 추출하여 사용한다.
	@ResponseBody
	@RequestMapping("/members/createMember.act")
	public String createMember(Member member , Model model) {
		log.info("###ModelAndView createMember model : " + model);
		log.info("###ModelAndView createMember : " + member.toString());
		
		memberService.memberJoin(member);
		log.info("###ModelAndView getID : " + member.getSeq());
		return member.getSeq()+"";
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
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}
