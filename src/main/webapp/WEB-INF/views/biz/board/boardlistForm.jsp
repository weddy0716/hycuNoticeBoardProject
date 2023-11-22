<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.list-group-item {
         cursor: pointer;
         transition: border-bottom 0.3s ease-in-out;
     }

     .list-group-item:hover {
         border-bottom: 2px solid #007bff;
     }
</style>

<div class="container-fluid px-4" id="boardListForm">
	<div class="container mt-5">
        <h1 class="text-center mb-4">게시판 목록</h1>
        <ul class="list-group">
        	<c:forEach var="item" items="${boardlist}">
        		<li class="list-group-item" data-seq="${item.seq}" >
	                <h5 class="mb-1" id="detailContents">${item.subject}</h5>
	                <small>작성자: ${item.userid} | 작성일: ${item.createDt}</small>
	            </li>
        	</c:forEach>
        </ul>
        <div class="text-center mt-4">
            <a href="#" class="btn btn-primary">새로운 게시물 작성</a>
        </div>
    </div>
</div>


<script type="text/javascript">
	documentReady("#boardListForm" , function(dom){
		$(dom).find(".list-group-item").bind("click" , function(){
			//자세히보기
			var param = {};
			param.seq = $(this).data("seq");
			location.href = "/board/boardDetailForm?seq="+$(this).data("seq");	
		});
	});
</script>
