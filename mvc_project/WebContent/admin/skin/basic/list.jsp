<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/admin/layout/header.jsp"></c:import>
<section class="content" id="content">
	<div class="title_text">
		<h2 class="title01">${optionVo.op_name } 목록</h2>
		<p>게시물을 입력할 수 있습니다.</p>
	</div>
	<div class="board_list_area">
		<div class="clearfix board_list_top">
			<!-- search_area -->
			<form action="list.do" name="fboardSearch" method="get">
			<input type="hidden" name="bo_table" value="${optionVo.op_table }" />
			<div class="search_area">
				<select name="sfl" id="select01" class="input-select">
					<option value="bo_writer" <c:if test="${param.sfl == 'bo_writer' }"> selected </c:if> >작성자</option>
					<option value="bo_subject" <c:if test="${param.sfl == 'bo_subject' }"> selected </c:if> >제목</option>
					<option value="bo_content" <c:if test="${param.sfl == 'bo_content' }"> selected </c:if> >내용</option>
				</select>
				<input type="text" name="stx" value="${param.stx }" placeholder="Search" />
				<input type="submit"class="btn01" value="Search" />
			</div>
			</form>
			<!-- //search_area -->
			<!-- button_area -->
			<div class="button_area">
				<a href="write.do?bo_table=${optionVo.op_table }" class="btn02">Add New</a>
			</div>
			<!-- //button_area -->
		</div>	

		<!-- 정렬 -->
		<div class="sort_list">
			<a href="${sort_link }&sst=bo_regdate" <c:if test="${param.sst == 'bo_regdate' }">class="on"</c:if> >등록순</a> 
			<a href="${sort_link }&sst=bo_hit" <c:if test="${param.sst == 'bo_hit' }">class="on"</c:if> >조회수</a> 
			<a href="${sort_link }&sst=bo_subject" <c:if test="${param.sst == 'bo_subject' }">class="on"</c:if>>제목순</a> 
		</div>
		<!-- //정렬 -->
		
			<form method="post" name="frm">
				<input type="hidden" name="bo_table" value="${optionVo.op_table }" />
				<div class="board_list">
					<table>
						<colgroup>
							<col class="col_num" />
							<col class="col_num" />
							<col class="col_title" />
							<col class="col_author" />
							<col class="col_date" />
							<col class="col_hit" />
							<col class="col_adm" />
						</colgroup>					
						<thead>
							<tr>
								<th><span class="chk_area"><label for="chk_all" onclick="fncCheckAll(chk_all, 'bo_idx')">All checked<input type="checkbox" value="" name="" id="chk_all" /></label></span></th>
								<th>No.</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
								<th scope="col">작성일</th>
								<th scope="col">조회수</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${boardList }" var="bVo">
							<tr>
								<td><span class="chk_area"><label><input type="checkbox" name="bo_idx" value="${bVo.bo_idx }" /></label></span></td>
								<td>${bVo.bo_eq }</td>
								<td class="left">
									<a class="link" href="view.do?bo_table=${optionVo.op_table }&bo_idx=${bVo.bo_idx }">
									<c:if test="${bVo.bo_is_secret == 1 }">[비밀글]</c:if>
									${bVo.bo_subject } 
									</a>
								</td>
								<td>${bVo.bo_writer }</td>
								<td><fmt:formatDate type="Date" value="${bVo.bo_regdate }" /></td>
								<td>${bVo.bo_hit }</td>
								<td>
									<a href="delete.do?bo_table=${optionVo.op_table }&bo_idx=${bVo.bo_idx }" class="btn_close"><span class="skip">delete</span></a>
									<a href="update.do?bo_table=${optionVo.op_table }&bo_idx=${bVo.bo_idx }" class="btn_check"><span class="skip">modify</span></a>
								</td>
							</tr>
							</c:forEach>
							<c:if test="${totalCount == 0 }">
							<tr>
								<td colspan="7">게시물이 없습니다.</td>
							</tr>
							</c:if>
						</tbody>
					</table>
				</div>
				
				<!-- 페이징 -->
				<c:set var="paging_param" value="${paging }" scope="request" />
				<c:import url="/util/paging.jsp"></c:import>
				<!-- //페이징 -->
				
				<!-- board_list_bottom -->
				<div class="clearfix board_list_bottom">
					<div class="button_area_left">
						<input type="submit" value="Delete" class="btn01" onclick="return deleteBoard()" />
					</div>
					<div class="button_area_right">
						<input type="submit" value="Add New" class="btn02" onclick="return writeBoard()" />
					</div>
				</div>
				<!-- //board_list_bottom -->
			</form>
			<script>
				//게시물삭제
				function deleteBoard(){
					var f = document.frm;
					if(f.bo_idx.length > 0){
						f.action = "delete.do";
					}else{
						alert("삭제할 게시물을 선택해주세요.")
						return false;
					}
				}
				//게시물작성
				function writeBoard(){
					var f= document.frm;
					f.action = "write.do";
				}
			</script>
		</div>
	
</section>
<c:import url="/admin/layout/footer.jsp"></c:import>
