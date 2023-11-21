<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container-fluid px-4">
	<h1 class="mt-4">게시판</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item">자유 게시판 입니다.</li>
	</ol>
	<div class="card mb-4">
		<input type="hidden" id="useVac" value=${useVac}>
		<input type="hidden" id="totalVac" value=${totalVac}>
	    <div class="card-body">
	    	게시판제목
	    </div>
	</div>
	<div class="card mb-4">
		<div class="card-header">
			<i class="fas fa-table me-1"></i>
			<button type="button" class="btn btn-primary btn-sm float-end" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">게시글등록</button>
		</div>
		<div class="card-body">
			<table id="datatablesSimple">
				<thead>
					<tr>
						<th>No</th>
						<th>제목</th>
						<th>요청일</th>
						<th>승인여부</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>No</th>
						<th>제목</th>
						<th>요청일</th>
						<th>승인여부</th>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
   
<script type="text/javascript">
	documentReady("#layoutAuthentication" , function(dom){
		
	});
</script>
