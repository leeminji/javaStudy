<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../../layout/header.jsp"></c:import>
<!-- content -->
<section class="content">
	<div class="content_area about_area clearfix">
		<div class="section">
			<h2 class="title01">${optionVo.op_name }</h2>
			<div class="board_view_area">
				<form method="post" name="frm">
					<input type="hidden" name="bo_table" value="${optionVo.op_table }" />
					<input type="hidden" name="bo_idx" value="${bVo.bo_idx }" />
					<input type="hidden" name="command" value="update" />
					<div class="clearfix board_view_top">
						<span class="info">- Date : ${bVo.bo_regdate } </span>
						<span class="info">- Writer : ${bVo.bo_writer }</span>
						<span class="info">- Hit : ${bVo.bo_hit }</span>
					</div>
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
					<!-- board_write -->
					<div class="board_view">
						<c:forEach items="${fileImgList }" var="bfVo">
						<div><img src="../../data/${bfVo.bf_source }" alt="${bfVo.bf_file }" style="max-width:${optionVo.op_img_width}px"/></div>
						</c:forEach>					
						<div class="view_content">${bVo.bo_content }</div>
					</div>
					<!-- //board_write -->
					<!-- board_write_bottom -->
					<div class="clearfix board_view_bottom">
						<div class="button_area_left">
							<c:if test="${is_write }">
								<a href="boardCheckPass.do?bo_table=${optionVo.op_table }&bo_idx=${bVo.bo_idx}&command=update" class="btn02">수정</a>
							</c:if>
							<c:if test="${is_reply }">
								<a  class="btn02" href="reply.do?bo_table=${optionVo.op_table }&bo_idx=${bVo.bo_idx}">답변</a>
							</c:if>
						</div>
						<div class="button_area_right">
							<a href="list.do?bo_table=${optionVo.op_table }" class="btn01">목록</a>
						</div>
					</div>
					<!-- //board_write_bottom -->
				</form>
			</div>
		</div>
	</div>
</section>
<c:import url="../../layout/footer.jsp"></c:import>
