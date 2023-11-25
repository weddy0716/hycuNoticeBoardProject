<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>

    .container {
        background-color: white;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        margin-top: 50px;
        border-radius: 8px;
    }

    .btn-container {
        display: flex;
        justify-content: space-between;
    }

    .btn-container .btn {
        width: 48%; /* 48%를 주어 두 버튼이 오른쪽에 위치하도록 함 */
    }
</style>
<div class="container-fluid px-4" id="memberModifyForm" >
	<div class="container">
        <h1 class="text-center mb-4">회원정보 수정</h1>
        <form>
            <div class="form-group">
                <label for="postTitle">사용자ID</label>
                <input type="text" class="form-control" id="userid" name="userid" value="${userinfo.userid}" required readonly>
            </div>

            <div class="form-group">
                <label for="postContent">이름</label>
                <input type="text" class="form-control" id="fullName" name="fullName" value="${userinfo.fullName}" required></input>
            </div>

            <div class="form-group">
                <label for="author">이메일</label>
                <input type="text" class="form-control" id="email" name="email" value="${userinfo.email}" required>
            </div>

	        <div class="btn-container">
	        	<button type="button" class="btn btn-info">게시글목록</button>
	        	<button type="button" class="btn btn-primary">회원정보수정하기</button>
	        </div>
        </form>
    </div>
</div>
<script type="text/javascript">
	documentReady("#memberModifyForm" , function(dom){
		console.log("##userInfo : " + "${userinfo}")
		
		$(dom).find(".btn-info").bind("click" , function(){
			location.href = "/board/boardlist";
		});
		
		$(dom).find(".btn-primary").bind("click" , function(){
			var param = {};
			param.userid = $(dom).find("#userid").val();
			param.email = $(dom).find("#email").val();
			param.fullName = $(dom).find("#fullName").val();
			console.log("##param : " + JSON.stringify(param));
		});
		
	});
</script>
