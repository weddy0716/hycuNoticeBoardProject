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
<div class="container-fluid px-4" id="boardDetailForm" >
	<div class="container">
        <h1 class="text-center mb-4">게시물 확인</h1>
        <form>
            <div class="form-group">
                <label for="postTitle">제목</label>
                <p class="mb-1">${detail.subject}</p>
            </div>
			<hr>
            <div class="form-group">
                <label for="postContent">내용</label>
                <p class="post-content">${detail.contents}</p>
            </div>

	        <div class="btn-container">
	        	<button type="submit" class="btn btn-primary">수정하기</button>
	            <button type="button" class="btn btn-danger">삭제하기</button>
	        </div>
        </form>
    </div>
</div>
   
<script type="text/javascript">
	documentReady("#boardDetailForm" , function(dom){
		$(dom).find("#insertBoardForm").bind("click" , function(){
			
		});
	});
</script>
