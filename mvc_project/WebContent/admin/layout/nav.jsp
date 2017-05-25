<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!-- nav_main_area -->
<nav class="nav_main_area bg_black30">
	<h2 class="skip">메인메뉴</h2>
	<ul class="clear">
		<li <c:if test="${ fn:indexOf(current_path, '/adm/main') > -1 }">class='current'</c:if>><a  href="${PATH }/adm/main">HOME</a></li>
		<li <c:if test="${ fn:indexOf(current_path, '/adm/member/') > -1 }">class='current'</c:if> ><a href="${PATH }/adm/member/list.do">회원관리</a></li>
		<li <c:if test="${ fn:indexOf(current_path, '/adm/boardOption/') > -1 }">class='current'</c:if> ><a href="${PATH }/adm/boardOption/list.do">게시판관리</a></li>
		<li <c:if test="${ fn:indexOf(current_path, '/adm/board/') > -1 }">class='current'</c:if> ><a href="${PATH }/adm/board/list.do?bo_table=notice">게시판</a>
			<ul class="clear bg_black90">
				<li><a href="${PATH }/adm/board/list.do?bo_table=notice">공지사항</a></li>
				<li><a href="${PATH }/adm/board/list.do?bo_table=qna">Q&A</a></li>
				<li><a href="${PATH }/adm/board/list.do?bo_table=portfolio">Portfolio</a></li>
			</ul>
		</li>
		<li <c:if test="${ fn:indexOf(current_path, '/adm/config/') > -1 }">class='current'</c:if> ><a href="${PATH }/adm/config/write.do">환경설정</a>
			<ul class="clear bg_black90">
				<li><a href="${PATH }/adm/config/write.do">환경설정</a></li>
				<li><a href="${PATH }/adm/menu/write.do">Menu</a></li>
				<li><a href="${PATH }/adm/popup/list.do">Popup</a></li>
			</ul>
		</li>
	</ul>
</nav>
<!-- //nav_main_area -->
