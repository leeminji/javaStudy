<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/header.jsp"></c:import>

<!-- 팝업 -->
<c:set var="popList" value="${popList }" scope="request" />
<c:import url="/util/popup.jsp"></c:import>
<script src="${JS_PATH }/ux.popup.js?v=1"></script>
<!-- //팝업 -->

<!-- content -->
<section class="content main_content">
	<h2 class="skip">HOME</h2>
	
	<div class="clearfix">
		<div class="main_blog_list">
			<h3>Blog 최신글</h3>
			<c:forEach items="${entries }" var="entry" begin="1" end="5">
			<dl>
				<dt><a href="${entry.uri }">${entry.title }</a></dt>
				<dd><fmt:formatDate type="Date" value="${entry.publishedDate }" /> </dd>
			</dl>
			</c:forEach>
			<a href="${PATH }/pf/page/blog" class="btn_more">더보기</a>
		</div>
	</div>
	<div class="main_work_list">
		<div class="work_list_area">
			<h3 class="skip">포트폴리오</h3>
			<ul class="clear clearfix">
				<c:forEach items="${boardList }" var="bVo">
				<li>
					<a href="${PATH }/pf/board/view.do?bo_table=portfolio&bo_idx=${bVo.bo_idx }">
						<span class="img">
						<c:choose>
							<c:when test="${ empty bVo.bo_thumb }">
							<img src="${IMG_PATH }/common/sample01.png" alt="" />
							</c:when>
							<c:otherwise>
							<img src="../data/thumb/${bVo.bo_thumb }" alt="" />
							</c:otherwise>
						</c:choose>
						</span>
						<span class="info"><strong>${bVo.bo_subject }</strong> <span class="etc">${bVo.bo_tag }</span></span>
						<span class="btn_arrow"></span>
					</a>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</section>
<!-- //content -->
<script type="text/javascript" src="${JS_PATH }/ux_main.js"></script>
<c:import url="../layout/footer.jsp"></c:import>