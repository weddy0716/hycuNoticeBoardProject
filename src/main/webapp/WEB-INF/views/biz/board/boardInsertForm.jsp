<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container-fluid px-4" id="boardInsertForm" >
	<div class="container mt-5">
        <h1 class="text-center mb-4">게시물 작성</h1>
        <div class="form-group">
            <label for="subject">제목</label>
            <input type="text" class="form-control" id="subject" name="subject" required>
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="contents">내용</label>
            <textarea class="form-control" id="contents" name="contents" rows="7" required></textarea>
        </div>
        <button type="submit" class="btn btn-primary" id="boardNoticeList" >목록으로</button>
        <button type="submit" class="btn btn-primary" id="insertBoardForm" >등록하기</button>
    </div>
</div>
   
<script type="text/javascript">
	documentReady("#boardInsertForm" , function(dom){
		$(dom).find("#insertBoardForm").bind("click" , function(){
			
			var subject	 = $(dom).find("#subject").val();  // 제목
			var password = $(dom).find("#password").val(); // 비밀번호 
			var contents = $(dom).find("#contents").val(); // 컨텐츠
			
			var param = {};			
			param.subject = subject;
			param.password = password;
			param.contents = contents;
			
			ajaxAction(param, "text", "/board/boardInsert.act", function(result){
				if(result == "1") {
					alert("등록되었습니다.");
				} else {
					alert(result);
				}
			},function(errorCode , errorMsg){
				var customErrorObj = errorMsg.split("||");
				alert("errorCode:" + customErrorObj[1] + " || " + customErrorObj[0]);
			});
			
		});
		
		$(dom).find("#boardNoticeList").bind("click" , function(){
			alert("boardNoticeList")
		});
	});
</script>
