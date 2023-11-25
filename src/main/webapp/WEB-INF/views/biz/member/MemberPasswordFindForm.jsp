<%@ page pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="MemberPasswordFindGroup">
    <div id="MemberPasswordFindGroup_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header">
                            	<h3 class="text-center font-weight-light my-4">비밀번호찾기</h3>
                            </div>
                            <div class="card-body" id="emailSendGroup">
                            		<p>회원가입한 ID와 이메일을 입력하여주십시오.</p>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="userid" type="text" placeholder="userID를 입력하세요"/>
                                        <label for="userid">사용자ID</label>
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="email" type="email" placeholder="test@test.com"/>
                                        <label for="email">이메일</label>
                                    </div>
                                    
                                    <div class="form-floating mb-3" id = "verifyCodeGroup" style="display:none">
                                        <input class="form-control" id="verifyCode" type="text" placeholder="인증코드입력"/>
                                        <label for="verifyCode">인증코드입력</label>
                                    </div>
                                    <a class="btn btn-primary w-111" href="#" id="emailSendBtn">인증코드이메일발송</a>
                                    <a class="btn btn-primary w-222" href="#" id="VerifyCodeCheck" style="display:none">인증코드확인</a>
                            </div>
                            
                            
                            <div class="card-body" id="passwordResetGroup" style="display:none">
                            		<p>비밀번호를 재설정 하십시오.</p>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="password" type="password" placeholder="비밀번호를입력하십시오."/>
                                        <label for="userid">비밀번호입력</label>
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="repassword" type="password" placeholder="비밀번호를 다시 입력하십시오."/>
                                        <label for="userid">비밀번호다시입력</label>
                                    </div>
                                    
                                    <a class="btn btn-primary w-100" href="#" id="passwordReset">비밀번호재설정</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/emailjs-com@2.4.1/dist/email.min.js"> </script> 
<script type="text/javascript"> 
	(function(){ 
    	emailjs.init("9C0-YeLj8ngucDvlJ"); 
     })(); 
</script>  
<script type="text/javascript">
	documentReady("#MemberPasswordFindGroup" , function(dom){
		
		$(dom).find("#emailSendBtn").bind("click", function(){
			//1.이메일발송을 위한 ID와 이메일주소 유효성검증
			//2.ID와 이메일이 맞을경우 인증코드를 생성한다.(DB에 넣어야한다.)
			var param = {};
			param.userid = $(dom).find("#userid").val();
			param.email  = $(dom).find("#email").val();
			
			ajaxAction(param, "text", "/member/memberFindByUserVerifyInfo.act", function(result){
				if(result == "1"){
					//인증코드발송진행
					param.sendEmail = $(dom).find("#email").val();
					ajaxAction(param, "text", "/member/passwordVerifyCode.act", function(result){
						
						var emailTemplateParam = { 
							to_name : $(dom).find("#userid").val() ,
							verify_code : result ,
							to_email: $(dom).find("#email").val(),
							reply_to: $(dom).find("#email").val() 
						};
						
						emailjs.send('service_6b3sm3d', 'template_79hu9to' , emailTemplateParam)
							   .then(function(response) {
							   		console.log('##박신호 EMAIL SUCCESS!', response.status, response.text);
							   		$(dom).find("#verifyCodeGroup").show();
							   		$(dom).find("#VerifyCodeCheck").show();
							   		alert("이메일발송에 성공하였습니다. \n 이메일에서 인증코드를 확인후에 인증코드를 입력하여 주십시오.");
							   }, function(error) {
							   	console.log('##박신호 EMAIL FAILED...', error);
							   });
					});
					
				}else{
					alert("입력된 정보와 일치한 데이터가 없습니다.");
				}
			},function(errorCode , errorMsg){
				var customErrorObj = errorMsg.split("||");
				alert("errorCode:" + customErrorObj[1] + " || " + customErrorObj[0]);
			});
		});
		
		$(dom).find("#VerifyCodeCheck").bind("click" , function(){
			var param = {};
			param.userid 	 = $(dom).find("#userid").val();
			param.sendEmail  = $(dom).find("#email").val();
			param.verifyCode = $(dom).find("#verifyCode").val();
			
			ajaxAction(param, "text", "/member/verifyCodeConfirm.act", function(result){
				if(result == "1"){
					alert("인증코드검증에 성공하였습니다. 비밀번호 재설정 화면으로 이동합니다.");
					$(dom).find("#emailSendGroup").hide();
					$(dom).find("#passwordResetGroup").show();
				}else{
					alert("인증번호가 일치하지 않습니다.");
				}
			});
		});
		
		$(dom).find("#passwordReset").bind("click" , function(){
			var password   = $(dom).find("#password").val();
			var repassword = $(dom).find("#repassword").val();
			
			if(password == ""){
				alert("비밀번호를 입력하여 주십시오.");
				return;
			}
			
			if(repassword == ""){
				alert("비밀번호 입력하여 주십시오.");
				return;
			}
			
			if(password != repassword){
				alert("입력된 비밀번호 값이 다릅니다.");
				return;
			}
			
			var param = {};
			param.userid   = $(dom).find("#userid").val();
			param.email    = $(dom).find("#email").val();
			param.password = $(dom).find("#password").val();
			
			ajaxAction(param, "text", "/member/memberResetPassword.act", function(result){
				if(result == "1"){
					alert("비밀번호 재설정이 완료 되었습니다. 로그인 화면으로 이동합니다.");
					location.href = "/";
				}else{
					alert("비밀번호 재설정중 오류가 발생하였습니다.");
				}
			});
		});
	});
</script>