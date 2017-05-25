<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="/admin/layout/header_sub.jsp"></c:import>
<!-- allwrap -->
<div class="allwrap bg01">
	<div id="skip_navi">
		<a href="#container">본문으로 바로가기</a>
	</div>
	<!-- header -->
	<div class="header_wrap">
		<header id="header" class="header">
			<h1 class="skip">dashboard</h1>
			<!-- nav_top_quick_area -->
			<nav class="nav_top_quick_area">
				<h2 class="skip">퀵메뉴</h2>
				<ul class="clearfix clear">
					<li class="bg_black30"><a href="${PATH }/adm/calendar/list.do" class="<c:if test="${ fn:indexOf(current_path, '/adm/calendar/') > -1 }">on</c:if> icon_clock"><span class="skip">시간</span></a></li>
					<li class="bg_black30"><a href="" class="icon_doc"><span class="skip">새문서</span></a></li>
					<li class="bg_black30"><a href="mail.write.html" class="icon_mail"><span class="skip">메일</span></a></li>
				</ul>
			</nav>
			<!-- //nav_top_quick_area -->
			<!-- info_login_area -->
			<section class="info_login_area bg_black30">
				<h2 class="skip">로그인정보</h2>
				<p> ${adminVo.mb_name }(${adminVo.mb_id })님, 로그인하였습니다.</p>
			</section>
			<!-- //info_login_area -->
			<!-- setting_area -->
			<nav class="setting_area">
				<h2 class="skip">셋팅</h2>
				<a href="#" class="btn_setting icon_setting" id="btn_setting"><span class="skip">Setting</span></a>
				<ul class="clear">
					<li><a href="#">Edit Profile</a></li>
					<li><a href="${PATH }/adm/login/logout.do">Logout</a></li>
				</ul>
			</nav>
			<!-- //setting_area -->
		</header>
	</div>
	<!-- header -->
	<!-- container_wrap -->
	<div class="container_wrap">
		<!-- container -->
		<div class="container" id="container">
		<c:import url="/admin/layout/nav.jsp"></c:import>
		<!-- content -->
		<div class="content_wrap">

	