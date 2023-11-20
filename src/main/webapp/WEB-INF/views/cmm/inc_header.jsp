<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page import="java.util.Calendar"%>
<%
	Calendar calendar = Calendar.getInstance();
	//calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) / 10);
	//calendar.set(Calendar.SECOND, 0);
	//calendar.set(Calendar.MILLISECOND, 0);
	String RESOURCE_DATE_VERSION = calendar.getTimeInMillis()+"";
	System.out.println("###RESOURCE_DATE_VERSION : " + RESOURCE_DATE_VERSION);
%>

<script type="text/javascript" src="/resource/js/jquery/jquery-3.6.1.min.js?<%=RESOURCE_DATE_VERSION%>"></script>
<script type="text/javascript" src="/resource/js/jquery/jquery.cookie.min.js?<%=RESOURCE_DATE_VERSION%>"></script>
<script type="text/javascript" src="/resource/js/cmm/com.js?<%=RESOURCE_DATE_VERSION%>"></script>
<script type="text/javascript" src="/resource/js/cmm/util.js?<%=RESOURCE_DATE_VERSION%>"></script>
<script type="text/javascript">
	
	/* 로그인시 LoginController에서 저장한 세션데이터 */
	sessionStorage.setItem("loginId"	, "${sessionScope.loginId}");		// 아이디
	sessionStorage.setItem("loginName"	, "${sessionScope.loginName}");		// 이름
	sessionStorage.setItem("loginNo"	, "${sessionScope.loginNo}");		// 회원번호
	sessionStorage.setItem("loginGrade"	, "${sessionScope.loginGrade}");	// 등급
	sessionStorage.setItem("loginTeam"	, "${sessionScope.loginTeam}"); 	// 소속
	sessionStorage.setItem("loginYN"	, "${sessionScope.loginYN}");		// 로그인여부(Y.로그인/ N.비로그인)
	
	
	function isFirstInit() {
		if(!JUtilLogin.isLogin()) { // 로그인 여부 (Y.로그인 / N.비로그인)
			// console.log("비로그인");
			
			// 저장한 세션데이터 삭제
			sessionStorage.clear();
			
			// 비로그인일 때 뒤로가기 방지
			JUtilPage.noBack();
		}
	}
	
	isFirstInit();
	
</script>

<style type="text/css" >
.wrap-loading{ /*화면 전체를 어둡게 합니다.*/
    position: fixed;
    left:0;
    right:0;
    top:0;
    bottom:0;
    background: rgba(0,0,0,0.2); /*not in ie */
    filter: progid:DXImageTransform.Microsoft.Gradient(startColorstr='#20000000', endColorstr='#20000000');    /* ie */
}

.wrap-loading div{ /*로딩 이미지*/
    position: fixed;
    top:50%;
    left:50%;
    margin-left: -21px;
    margin-top: -21px;
}
.display-none{ /*감추기*/
    display:none;
}
</style>

<div id="loadingBar" class="wrap-loading" style = "display:none">
    <div>
    	<div class="spinner-border text-primary" style="width: 6rem; height: 6rem;" role="status">
	  <span class="visually-hidden">Loading...</span>
	</div>
    </div>
</div>    