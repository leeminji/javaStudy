<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>

<!-- content -->
<section class="content">
	<div class="content_area clearfix">
		<div class="section">
			<img src="${IMG_PATH }/common/bg_500error.jpg" alt="" />
			<div class="btn_area_center">
				<a href="javascript:history.go(-1)" class="btn02" >뒤로가기</a>
				<a href="${PATH }/pf/index.do" class="btn01">메인으로</a> <a href="${PATH }/pf/page/contact" class="btn01">Contact</a>
			</div>
		</div>
	</div>
</section>

<c:import url="../layout/footer.jsp"></c:import>