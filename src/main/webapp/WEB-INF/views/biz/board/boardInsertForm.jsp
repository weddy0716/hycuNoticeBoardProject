<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container-fluid px-4">
	<h1 class="mt-4">게시판</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item">자유 게시판 입니다.</li>
	</ol>
	<div class="card mb-4">
		<div class="card-body">
			<table class="table02">
			    <colgroup>
			        <col width="20%">
			        <col width="*">
			    </colgroup>
			    <tbody id="tbody">
					<tr>
						<th>제목<span class="t_red">*</span></th>
						<td><input id="board_subject" name="board_subject" value="" class="tbox01"/></td>
					</tr>
					<tr>
						<th>비밀번호<span class="t_red">*</span></th>
						<td><input id="board_subject" name="board_subject" value="" class="tbox01"/></td>
					</tr>
					<tr>
						<th>내용<span class="t_red">*</span></th>
						<td><textarea id="board_content" name="board_content" cols="10" rows="5" class="textarea01"></textarea></td>
					</tr>
			    </tbody>
			</table>
			<div class="btn_right mt15">
				<button type="button" class="btn black mr5" onclick="javascript:goBoardList();">목록으로</button>
				<button type="button" class="btn black" onclick="javascript:insertBoard();">등록하기</button>
			</div>
		</div>
	</div>
</div>
   
<script type="text/javascript">
	documentReady("#layoutAuthentication" , function(dom){
		
	});
</script>
