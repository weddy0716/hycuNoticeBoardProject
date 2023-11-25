<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/cmm/inc_header.jsp" %>
<%@ page import="com.sinho.hycu.framework.session.SessionManager"%>

<%
	
	boolean loginFlag = SessionManager.getSession(request , "userinfo") == null ? false : true;
	
	System.out.println("###PSH layout loginFlag : " + loginFlag);
	System.out.println("###PSH layout request : " + request.getRequestURI());

%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
	<title><tiles:getAsString name="title" ignore="true"/></title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="/resource/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
<script type="text/javascript">
	var loginFlag = "<%=loginFlag%>";
	if(loginFlag == "false"){
		alert("로그인이 필요한 서비스 입니다. 로그인 페이지로 이동합니다.");
		location.href = "/";
	}
   </script>
<body>
	<tiles:insertAttribute name="header" ignore="true"/>
	<div id="layoutSidenav">
		<div id="layoutSidenav_nav">
			<tiles:insertAttribute name="menu" ignore="true"/>
		</div>
		<div id="layoutSidenav_content">
			<tiles:insertAttribute name="body" ignore="true"/>
			<tiles:insertAttribute name="footer" ignore="true"/>
		</div>
	</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script type="text/javascript">
		
    </script>
</body>
</html>