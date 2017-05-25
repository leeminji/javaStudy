<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../../layout/header.jsp"></c:import>
<!-- content -->
<section class="content">
	<div class="content_area about_area clearfix">
		<form method="post" name="frm">
		<input type="hidden" name="bo_table" value="${optionVo.op_table }" />
		<input type="hidden" name="bo_idx" value="${bVo.bo_idx }" />
		<input type="hidden" name="command" value="update" />
		<div class="section01">
			<h2 class="title01">${optionVo.op_name }</h2>
			<div class="work_content">
				<h3 class="title02">${bVo.bo_subject }</h3>
			</div>
			<div class="work_img_area">
				<div class="img_gallery">
					<ul class="clear clearfix slider">
					<c:forEach items="${fileImgList }" var="bfVo">
					<li><img src="../../data/${bfVo.bf_source }" alt="${bfVo.bf_file }"/></li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<script>
			$(document).ready(function(){
				$(".slider").bxSlider({
					'speed' : 500,
					'auto' : true,
					'controls' : true
				});
			});
		</script>
		<div class="section02">
			<div class="work_content">
				<h3 class="title01">PROJECT INFO</h3>
				<div class="work_content_text">
					${bVo.bo_content }
				</div>
			</div>
			<!-- project_info -->
			<div class="project_info">
				<h3 class="title01">Project Detail</h3>
					<ul class="clear">
					<li class="writer">참여율 : ${bVo.bo_1 }%</li>
					<li class="doc">${bVo.bo_tag }</li>
					<li class="date">${bVo.bo_2 }~${bVo.bo_3 }</li>	
					<li class="target">${bVo.bo_4 }</li>
					<li class="target"><a href='${bVo.bo_5 }' target='_blank' title='새창으로열림'>${bVo.bo_5 }</a></li>
				</ul>
			</div>
			<!-- //project_info -->
			<c:if test="${!empty fileList }">
			<div class="board_view_file">
				<h3 class="tit">첨부파일</h3>
				<ul class="clear">
					<c:forEach items="${fileList }" var="bfVo">
					<li><a href="${PATH }/adm/board/download.do?bf_source=${bfVo.bf_source }&bf_file=${bfVo.bf_file }">${bfVo.bf_file }</a></li>
					</c:forEach>
				</ul>
			</div>
			</c:if>
			<div class="clearfix">
				<div class="btn_area_right">
					<a href="list.do?bo_table=${optionVo.op_table }" class="btn01">목록</a>
				</div>
			</div>
		</div>
		</form>
	</div>
</section>
<c:import url="../../layout/footer.jsp"></c:import>
