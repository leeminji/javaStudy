<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/admin/layout/header.jsp"></c:import>
<section class="content" id="content">
	<div class="title_text">
		<h2 class="title01">${optionVo.op_name } - ${bVo.bo_subject } </h2>
	</div>
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
			<!-- board_view -->
			<div class="board_view">
				<div class="view_content">
				<c:forEach items="${fileImgList }" var="bfVo">
				<div><img src="../../data/${bfVo.bf_source }" alt="${bfVo.bf_file }" style="max-width:${optionVo.op_img_width}px"/></div>
				</c:forEach>
				${bVo.bo_content }
				</div>
			</div>
			<!-- //board_view -->
			<!-- board_write_bottom -->
			<div class="clearfix board_view_bottom">
				<div class="button_area_left">
					<a href="list.do?bo_table=${optionVo.op_table }" class="btn01">List</a>
				</div>
				<div class="button_area_right">
					<input type="submit" class="btn02" value="Modify" onclick="return updateBoard()"/>
					<input type="submit" class="btn02" value="Reply" onclick="return replyBoard()"/>
					<input type="submit" class="btn02" value="Delete" onclick="return deleteBoard()"/>				
				</div>
			</div>
			<!-- //board_write_bottom -->
		</form>
		<script>
			//수정
			function updateBoard(){
				var f = document.frm;
				f.command.value="update";
				f.action = "update.do";
			}
			//답변
			function replyBoard(){
				var f = document.frm;
				f.action = "reply.do";
			}
			//삭제
			function deleteBoard(){
				var f = document.frm;
				f.command.value="delete";
				f.action = "delete.do";
			}
		</script>
	</div>
</section>
<c:import url="/admin/layout/footer.jsp"></c:import>
