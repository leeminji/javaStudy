<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/header.jsp"></c:import>
<!-- content -->
<section class="content">
	<div class="content_area clearfix">
		<div class="section blog_area ">
			<h2 class="title01">BLOG</h2>
			<p>네이버 블로그 최신글 목록입니다.</p>
			<div class="btn_area_right">
				<a href="http://blog.naver.com/leeminji25" class="btn01" target="_blank">NAVER BLOG 이동</a>
			</div>
			<c:forEach items="${entries }" var="entry">
			<dl>
				<dt><a href="${entry.uri }">${entry.title }</a></dt>
				<dd class="info">INFO : <fmt:formatDate type="Date" value="${entry.publishedDate }" /> </dd>
				<dd class="con"><a href="${entry.uri }">${entry.description }</a></dd>
			</dl>
			</c:forEach>
		</div>
	</div>
</section>
<!-- //content -->
<c:import url="../layout/footer.jsp"></c:import>