<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="/">게시판</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto md-0 me-3 my-md-0">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="/users/updateUserInfoForm">회원정보 수정</a></li>
                <li><hr class="dropdown-divider" /></li>
                <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#looutModal">로그아웃</a></li>
            </ul>
        </li>
    </ul>
</nav>
<!-- ============================== 로그아웃 팝업 ============================== -->
<div class="modal fade" id="looutModal" tabindex="-1" aria-labelledby="looutModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="looutModalLabel">로그아웃</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	<span>로그아웃을 하시겠습니까?</span>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" class="btn btn-primary" id="logout">로그아웃</button>
      </div>
    </div>
  </div>
</div>
<!-- ============================== 로그아웃 팝업 ============================== -->

<script type="text/javascript">
	$("#logout").on("click", function() {
		location.href = "/login/logout.act";
	});
</script>