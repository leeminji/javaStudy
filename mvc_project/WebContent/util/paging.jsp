<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${paging_param.totalCount != 0 }">
<div class="page_area">
	<c:if test="${paging_param.pageNo != 1 }">
		<a href="${paging_param.url }&page=${paging_param.firstPageNo}" class="page_first">처음</a>
		<a href="${paging_param.url }&page=${paging_param.prevPageNo}" class="page_prev">이전</a>	
	</c:if>
	<c:forEach var="i" begin="${paging_param.startPageNo}" end="${paging_param.endPageNo}" step="1">
		<c:choose>
			<c:when test="${i eq paging_param.pageNo}">
				<em>${i}</em>
			</c:when>
			<c:otherwise>
				<a href="${paging_param.url }&page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="${ paging_param.finalPageNo != paging_param.pageNo }">
		<a href="${paging_param.url }&page=${paging_param.nextPageNo}" class="page_next">다음</a>
		<a href="${paging_param.url }&page=${paging_param.finalPageNo}" class="page_last">마지막</a>
	</c:if>
</div>
</c:if>