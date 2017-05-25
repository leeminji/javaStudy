<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>

<!-- content -->
<section class="content">
	<div class="content_area about_area clearfix">
		<div class="section">
			<h2 class="title01">회원가입을 축하합니다.</h2>
			<div class="btn_area_center">
				<a href="login" class="btn02">로그인</a>
				<a href="${PATH }/index.do" class="btn02">메인으로</a>
			</div>
		</div>
	</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>