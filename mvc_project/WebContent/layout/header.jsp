<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="${URL }/layout/header_sub.jsp"></c:import>
<div class="header_m">
	<div class="header_bar">
		<a href="#" class="btn_nav_menu">메뉴</a>
		<a href="${PATH }/pf/index.do" class="m_logo">${configVo.cf_title }</a>
		<a href="#" class="btn_nav_info">정보</a>
	</div>
</div>
<!-- header -->
<header id="header" class="header">
	<div class="inner">
		<a href="#" class="btn_nav_close">닫기</a>
		<div class="logo"><h1><a href="${PATH }/pf/index.do"><img src="${IMG_PATH }/common/logo.png" alt="" /></a></h1></div>
		<section class="global_area">
			<h2 class="skip">메뉴</h2>
			<c:if test="${!empty ss_mb_id}">
			<div class="member_info">${mVo.mb_name }(${mVo.mb_id })</div>
			</c:if>
			<ul class="clearfix clear">
			<c:choose>
			<c:when test="${empty ss_mb_id}">
			<li><a href="${PATH }/pf/member/login">LOGIN</a></li>
			<li><a href="${PATH }/pf/member/join">JOIN</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="${PATH }/pf/member/logout">LOGOUT</a></li>
			<li><a href="${PATH }/pf/member/member_update">MYPAGE</a></li>
			</c:otherwise>
			</c:choose>
			</ul>
		</section>
		<!-- myinfo_area -->
		<section class="myinfo_area">
			<h2 class="skip">나의 정보</h2>
			<a href="#" class="btn_myinfo">My infomaition</a>
			<ul class="info_top_area clear">
				<li><span class="icon_phone">전화번호</span>${configVo.cf_info1 }</li>
				<li><span class="icon_mail">메일</span>${configVo.cf_info2 }</li>
				<li><span class="icon_career">직업 </span>${configVo.cf_info3 }</li>
			</ul>						
		</section>
		<!-- //myinfo_area -->
		<!-- gnb_area -->
		<nav class="gnb_area">
			<h2 class="skip">메인메뉴</h2>
			<ul class="clear">
				<c:set var="current_path">
					<c:choose>
					<c:when test="${ !empty query_bo_table }">
						<c:choose>
							<c:when test='${ fn:indexOf(current_path, "write.do") > -1 }'>
							${fn:replace(current_path, "write.do", "list.do") }?bo_table=${query_bo_table }
							</c:when>
							<c:when test='${ fn:indexOf( current_path, "view.do") > 0 }'>
							${fn:replace(current_path, "view.do", "list.do") }?bo_table=${query_bo_table }
							</c:when>
							<c:otherwise>
							${current_path }?bo_table=${query_bo_table }
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						${current_path }
					</c:otherwise>
					</c:choose>
				</c:set>
				<c:forEach items="${menuList }" var="menuVo">
				<li><a href="${PATH }${menuVo.me_link }" class=<c:if test="${current_path == menuVo.me_link}">'on'</c:if>>${menuVo.me_name }</a></li>
				</c:forEach>
			</ul>
		</nav>
		<!-- //gnb_area -->

		<!-- sns_area -->
		<section class="sns_area">
			<h3 class="skip">소셜네트워크</h3>
			<ul class="clear clearfix" >
				<li><a href="https://www.instagram.com/puing25/?hl=en" target="_blank" class="icon_insta" >instagram</a></li>
				<li><a href="https://www.facebook.com/profile.php?id=100003302243009" target="_blank" class="icon_facebook">facebook</a></li>
				<li><a href="http://blog.naver.com/leeminji25" target="_blank" class="icon_blog">Blog+</a></li>
			</ul>
		</section>
		<!-- //sns_area -->
		<!-- footer -->
		<footer class="copyright">
			<address>${configVo.cf_title } <br />＠ 2017 All Rights Reserved</address>
		</footer>
		<!-- //footer -->		
	</div>
	<span class="deco_shadow"></span>
</header>
<!-- //header -->
<hr />
<!-- container -->
<div class="container_wrap">
<section id="containter" class="container">
