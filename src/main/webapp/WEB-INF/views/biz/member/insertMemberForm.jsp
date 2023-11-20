<%@ page pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="insertMemberGroup">
    <div id="insertMemberGroup_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">회원등록</h3></div>
                            <div class="card-body">
                                <form>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="userid" type="text" placeholder="userID를 입력하세요"/>
                                        <label for="userid">사용자ID</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="fullName" type="text" placeholder="홍길동" data-filter=":kor"/>
                                        <label for="fullName">이름</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="password" type="password" placeholder="" maxlength="20"/>
                                        <label for="password">비밀번호</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="email" type="email" placeholder="test@test.com"/>
                                        <label for="email">이메일</label>
                                    </div>
                                    
                                    <a class="btn btn-primary w-100" href="#" id="join">가입하기</a>
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
	documentReady("#insertMemberGroup" , function(dom){
		$(dom).find("#join").bind("click", function(){
			var userid		= $(dom).find("#userid").val(); // 아이디
			var password	= $(dom).find("#password").val(); // 비밀번호
			var fullName	= $(dom).find("#fullName").val(); // 이름
			var email		= $(dom).find("#email").val(); // 입사일
			
			var param = {};			
			param.userid = userid;
			param.password = password;
			param.fullName = fullName;
			param.email = email;
			console.log("###PSH param : " + JSON.stringify(param));
			
			ajaxAction(param, "text", "/members/createMember.act", function(result){
				if(result == "") {
					alert("회원 등록이 완료되었습니다.");
					document.location.href = document.location.href;
					$(dom).find("form")[0].reset();
				} else {
					alert(result);
				}
			},function(errorCode , errorMsg){
				alert(errorMsg);
			});
			
		});
	});
</script>