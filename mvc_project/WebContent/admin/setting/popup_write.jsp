<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/admin/layout/header.jsp"></c:import>
<section class="content" id="content">
	<div class="title_text">
		<c:choose>
		<c:when test="${update }">	
			<h2 class="title01">Popup Write</h2>
		</c:when>
		<c:otherwise>
			<h2 class="title01">Popup Update</h2>
		</c:otherwise>
		</c:choose>
		<p>초기화면 접속 시 자동으로 뜰 팝업레이어를 설정합니다.</p>
	</div>
	<form name="frm" method="post" action="writeOk.do">
	<c:choose>
		<c:when test="${update }">
		<input type="hidden" name="command" value="update" />
		<input type="hidden" name="po_idx" value="${pVo.po_idx }" />
		</c:when>
		<c:otherwise>
		<input type="hidden" name="command" value="insert" />
		</c:otherwise>
	</c:choose>
	<div class="board_write_area">
		<!-- board_write -->
		<div class="board_write">
			<table>
				<colgroup>
					<col class="col_subject" />
					<col class="col_content" />
				</colgroup>
				<tr>
					<th scope="row"><label for="po_disable_hours" class="chk_must">시간</label></th>
					<td>
						<p class="txt_info">고객이 다시 보지 않음을 선택할 시 몇 시간동안 팝업레이어를 보여주지 않을지 설정합니다.</p>
						<p><input type="text" size="6" value="${pVo.po_disable_hours }"  name="po_disable_hours" id="po_disable_hours" required /> 시간</p>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="po_begin_date" value="${pVo.po_begin_time }" class="chk_must">시작일시</label></th>
					<td>
						<input type="text" class="datepicker" name="po_begin_date" id="po_begin_date" value="<fmt:formatDate value="${pVo.po_begin_time }" pattern="yyyy-MM-dd" />" />
						<c:set var="begin_time">
							<fmt:formatDate value="${pVo.po_begin_time }" pattern="HH:00" />
						</c:set>
						<select name="po_begin_time" id="po_begin_time" class="input-select" style="width:120px">
							<c:forEach begin="0" end="23" var="time">
								<c:set var="set_time"><fmt:formatNumber minIntegerDigits="2" value="${time }" />:00</c:set>
								<option value="${set_time }" <c:if test="${begin_time == set_time }">selected</c:if> >${set_time }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="po_end_date" class="chk_must">종료일시</label></th>
					<td>
						<input type="text" class="datepicker" name="po_end_date" id="po_end_date" value="<fmt:formatDate value="${pVo.po_end_time }" pattern="yyyy-MM-dd" />" />
						<c:set var="end_time">
							<fmt:formatDate value="${pVo.po_end_time }" pattern="HH:00" />
						</c:set>
						<select name="po_end_time" id="po_end_time" class="input-select" style="width:120px">
							<c:forEach begin="0" end="23" var="time">
								<c:set var="set_time"><fmt:formatNumber minIntegerDigits="2" value="${time }" />:00</c:set>
								<option value="${set_time }" <c:if test="${end_time == set_time }">selected</c:if> >${set_time }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="po_left" class="chk_must">팝업 좌측 / 상단</label></th>
					<td>
						<input type="text" name="po_left" id="po_left" size="5" value="${pVo.po_left }" /> px /
						<input type="text" name="po_top" id="po_top" size="5" value="${pVo.po_top }" /> px
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="po_width" class="chk_must">팝업 넓이 / 높이</label></th>
					<td>
						<input type="text" name="po_width" id="po_width" size="5" value="${pVo.po_width }" /> px /
						<input type="text" name="po_height" id="po_height" size="5" value="${pVo.po_height }" /> px
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="po_color">색상</label></th>
					<td>
						내용 : <input type="text" name="po_color" id="po_color_content" size="30" value="#ffffff" /> / 
						하단 : <input type="text" name="po_color" id="po_color_line" size="30" value="#ff0000" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="po_subject" class="chk_must">팝업 제목</label></th>
					<td>
						<input type="text" class="" name="po_subject" id="po_subject" size="200" value="${pVo.po_subject }" />
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="po_content" class="chk_must">내용</label></th>
					<td>
						<textarea name="po_content" id="po_content" cols="30" rows="10">${pVo.po_content }</textarea>
					</td>
				</tr>
			</table>
		</div>
		<!-- //board_write -->
		<!-- board_write_bottom -->
		<div class="clearfix board_write_bottom">
			<div class="button_area_left">
				<a href="list.do" class="btn01">List</a>
			</div>
			<div class="button_area_right">
				<a href="javascript:;" onclick="previewPopup(document.frm, 'pre_popup');" class="btn02">Preview</a>
				<input type="submit" class="btn02" value="Save" />
			</div>
		</div>
		<!-- //board_write_bottom -->
	</div>
	</form>
</section>
<script type="text/javascript" src="${JS_PATH }/ux.popup.js"></script>
<c:import url="/admin/layout/footer.jsp"></c:import>