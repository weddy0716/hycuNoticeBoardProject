<%@ page pageEncoding="UTF-8"%>
<div id="hycuBoardLogin">
    <div id="hycuBoardLogin_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">게시판 로그인</h3></div>
                            <div class="card-body">
                                <form>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="userid" type="text" placeholder="ID입력" required="required"/>
                                        <label for="id">로그인ID</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="password" type="password" placeholder="Password" />
                                        <label for="password">Password</label>
                                    </div>
                                    <div class="form-check mb-3">
                                        <input class="form-check-input" id="rememberId" type="checkbox" value="" />
                                        <label class="form-check-label" for="rememberId">아이디 저장</label>
                                    </div>
                                    <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <a class="btn btn-primary w-100" href="#" id="login">Login</a>
                                    </div>
                                    <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <a class="btn btn-primary w-100" href="#" id="memberJoin">회원가입</a>
                                    </div>
                                    <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                        <a class="btn btn-primary w-100" href="#" id="forgetPassword">비밀번호찾기</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<script type="text/javascript">
	documentReady("#hycuBoardLogin" , function(dom){
		$(dom).find("#login").bind("click",function(){
			var userid		= $(dom).find("#userid").val(); // 아이디
			var password	= $(dom).find("#password").val(); // 비밀번호
			
			if(userid == ""){
				alert("사용자ID를 입력하여 주십시오.");
				return;
			}
			if(password == ""){
				alert("비밀번호를 입력하여 주십시오.");
				return;
			}
			
			var param = {};			
			param.userid = userid;
			param.password = password;
			
			ajaxAction(param, "text", "/login/userlogin.act", function(result){
				if(result == "1") {
					alert("로그인이완료되었습니다.");
					document.location.href = "/";
					$(dom).find("form")[0].reset();
				} else {
					alert(result);
				}
			},function(errorCode , errorMsg){
				var customErrorObj = errorMsg.split("||");
				alert("errorCode:" + customErrorObj[1] + " || " + customErrorObj[0]);
			});
		});
		
		$(dom).find("#memberJoin").bind("click",function(){
			alert("회원가입이동")
			location.href = "/member/insertMemberForm";
		});
		
		$(dom).find("#forgetPassword").bind("click",function(){
			
		});
		
	});
</script>