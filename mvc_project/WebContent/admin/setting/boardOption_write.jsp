<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:import url="/admin/layout/header.jsp"></c:import>
<!-- content -->
<section class="content" id="content">
	<div class="title_text">
		<c:choose>
		<c:when test="${update }">
		<h2 class="title01">Board Setting Update</h2>
		</c:when>
		<c:otherwise>
		<h2 class="title01">Board Setting Write</h2>
		</c:otherwise>
		</c:choose>
		<p>게시판 기본 설정</p>
	</div>
	
	<form action="writeOk.do" name="frm" method="post">
	<c:choose>
	<c:when test="${update }">
		<input type="hidden" name="op_idx" value="${optionVo.op_idx }" />
		<input type="hidden" name="command" value="update" />
	</c:when>
	<c:otherwise>
		<input type="hidden" name="command" value="write" />
	</c:otherwise>
	</c:choose>
	
	<div class="board_write_area">
		<!-- board_write -->
		<div class="board_write">
			<h3 class="board_title">기본설정</h3>
			<table>
				<colgroup>
					<col class="col_subject" />
					<col class="col_content" />
				</colgroup>
				<tr>
					<th scope="row"><label for="bo_name" class="chk_must">TABLE</label></th>
					<td>
						
						<c:choose>
							<c:when test="${update }"><p>${optionVo.op_table }</p></c:when>
							<c:otherwise>
							<p class="txt_info">테이블 이름을 지정합니다.</p>
							<p><input type="text" size="400" required name="op_table" id="op_table" /></p>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_name" class="chk_must">게시판제목</label></th>
					<td>
						<input type="text" name="op_name" id="op_name" size="400" value="${optionVo.op_name }"/>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_cate">카테고리</label></th>
					<td>
						<p class="txt_info">분류와 분류사이는 | 로 구분해주세요.</p>
						<p><input type="text" value="${optionVo.op_cate }" name="op_cate" id="op_cate" size="400" /></p>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_sort" class="chk_must">정렬</label></th>
					<td>
						<select name="op_sort" id="op_sort" class="input-select">
							<option value="bo_datetime">최신순</option>
							<option value="bo_subject">제목순</option>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_skin" class="chk_must">스킨</label></th>
					<td>
						<select name="op_skin" id="op_skin" class="input-select">
							<option value="">선택</option>
							<c:forEach items="${frontSkinList }" var="dir">
							<option value="${dir }" <c:if test="${dir == optionVo.op_skin }">selected</c:if> >${dir }</option>
							</c:forEach>
						</select>
					</td>
				</tr>				
				<tr>
					<th scope="row"><label for="op_skin" class="chk_must">관리자스킨</label></th>
					<td>
						<select name="op_adm_skin" id="op_adm_skin" class="input-select">
							<option value="">선택</option>
							<c:forEach items="${admSkinList }" var="dir">
							<option value="${dir }" <c:if test="${dir == optionVo.op_adm_skin }">selected</c:if> >${dir }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_is_secret">비밀글사용</label></th>
					<td>
						<span class="chk_area"><label><input type="checkbox" value="1" name="op_is_secret" <c:if test="${optionVo.op_is_secret == 1 }"> checked </c:if> /></label></span>
					</td>
				</tr>					
				<tr>
					<th scope="row"><label for="op_is_ip">아이피사용</label></th>
					<td>
						<span class="chk_area"><label><input type="checkbox" value="1" name="op_is_ip" <c:if test="${optionVo.op_is_ip == 1 }"> checked </c:if> /></label></span>
					</td>
				</tr>	
				<tr>
					<th scope="row"><label for="op_is_sign">서명 사용</label></th>
					<td>
						<span class="chk_area"><label><input type="checkbox" value="1" name="op_is_sign" <c:if test="${optionVo.op_is_sign == 1 }"> checked </c:if> /></label></span>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_page_length">한페이지당 레코드갯수</label></th>
					<td><input type="text" name="op_page_length" id="op_page_length" value="${optionVo.op_page_length }" size="5" /></td>
				</tr>				
				<tr>
					<th scope="row"><label for="op_is_preview">다음 / 이전 목록</label></th>
					<td><span class="chk_area"><label><input type="checkbox" name="op_is_preview" id="op_is_preview" value="1" <c:if test="${optionVo.op_is_preview == 1 }"> checked </c:if> /></label></span></td>
				</tr>
				<tr>
					<th scope="row"><label for="op_is_notice">공지사항사용</label></th>
					<td><span class="chk_area"><label><input type="checkbox" name="op_is_notice" id="op_is_notice" value="1" <c:if test="${optionVo.op_is_notice == 1 }"> checked </c:if> /></label></span></td>
				</tr>
				<tr>
					<th scope="row"><label for="op_new_date">새글 아이콘 등록시간</label></th>
					<td><input type="text" name="op_new_date" id="op_new_date" size="5" value="${optionVo.op_new_date }"  /> 시간</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_img_width">게시물 이미지 넓이</label></th>
					<td><input type="text" name="op_img_width" size="5" id="op_img_width" value="${optionVo.op_img_width }"/> px</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_thumb">썸네일 넓이/높이</label></th>
					<c:set var="op_thumb" value="${optionVo.op_thumb }"/>
					<c:set var="op_thumb_width" value="${fn:split(op_thumb, ';')[0]}"/>
					<c:set var="op_thumb_height" value="${fn:split(op_thumb, ';')[1]}"/>
					<td>
						넓이 : <input type="text" name="op_thumb_width" id="op_thumb" size="5" value="${op_thumb_width}" /> px &nbsp;/&nbsp;
						높이 : <input type="text" name="op_thumb_height" size="5" value="${op_thumb_height}" /> px
					</td>
				</tr>
			</table>
			<h3 class="board_title">권한설정</h3>
			<table>
				<colgroup>
					<col class="col_subject" />
					<col class="col_content" />
				</colgroup>
				<tr>
					<th scope="row"><label for="op_admin" class="chk_must">관리자</label></th>
					<td>
						<input type="text" size="400" value="" name="op_admin" id="op_admin" /> 
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_list_level" class="chk_must">목록보기권한</label></th>
					<td>
						<select name="op_list_level" id="op_list_level" class="input-select">
						<c:forEach begin="1" end="10" var="level">
						<option value="${level }" <c:if test="${optionVo.op_list_level == level }">selected</c:if> >${level }</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_view_level" class="chk_must">글읽기권한</label></th>
					<td>
						<select name="op_view_level" id="op_view_level" class="input-select">
						<c:forEach begin="1" end="10" var="level">
						<option value="${level }" <c:if test="${optionVo.op_view_level == level }">selected</c:if> >${level }</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_write_level" class="chk_must">글쓰기권한</label></th>
					<td>
						<select name="op_write_level" id="op_write_level" class="input-select">
						<c:forEach begin="1" end="10" var="level">
						<option value="${level }" <c:if test="${optionVo.op_write_level == level }">selected</c:if> >${level }</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_reply_level" class="chk_must">글답변권한</label></th>
					<td>
						<select name="op_reply_level" id="op_reply_level" class="input-select">
						<c:forEach begin="1" end="10" var="level">
						<option value="${level }" <c:if test="${optionVo.op_reply_level == level }">selected</c:if> >${level }</option>
						</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row"><label for="op_comment_level" class="chk_must">댓글쓰기권한</label></th>
					<td>
						<select name="op_comment_level" id="op_comment_level" class="input-select">
						<c:forEach begin="1" end="10" var="level">
						<option value="${level }" <c:if test="${optionVo.op_comment_level == level }">selected</c:if> >${level }</option>
						</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			<p>${message }</p>
		</div>
		<!-- //board_write -->
		<!-- board_write_bottom -->
		<div class="clearfix board_write_bottom">
			<div class="button_area_left">
				<a href="list.do" class="btn01">List</a>
			</div>
			<div class="button_area_right">
				<input type="submit" value="Save"  class="btn02" />
			</div>
		</div>
		<!-- //board_write_bottom -->
	</div>	
	</form>
</section>
<!-- //content -->
<c:import url="/admin/layout/footer.jsp"></c:import>