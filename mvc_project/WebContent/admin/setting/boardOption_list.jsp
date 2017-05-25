<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/admin/layout/header.jsp"></c:import>
<section class="content" id="content">
	<div class="title_text">
		<h2 class="title01">Board Setting.</h2>
		<p>생성된 게시판수 <strong>${ totalCount }</strong>개</p>
	</div>
	<div class="board_list_area">
		<!-- board_list_top -->
		<div class="clearfix board_list_top">
			<!-- search_area -->
			<form action="list.do" name="fboardSearch" method="get">
			<div class="search_area">
				<select name="sfl" id="select01" class="input-select">
					<option value="" <c:if test="${empty param.sfl }"> selected </c:if> >선택</option>
					<option value="op_table" <c:if test="${param.sfl == 'op_table' }"> selected </c:if> >Table</option>
					<option value="op_name" <c:if test="${param.sfl == 'op_name' }"> selected </c:if> >게시판이름</option>
				</select>
				<input type="text" name="stx" value="${param.stx }" placeholder="Search" />
				<input type="submit"class="btn01" value="Search" />
			</div>
			</form>
			<!-- //search_area -->
			<!-- button_area -->
			<div class="button_area">
				<a href="write.do" class="btn02">Add New</a>
			</div>
			<!-- //button_area -->
		</div>
		<!-- //board_list_top -->
		<form name="frm" method="post">
			<div class="board_list">
			<table>
				<colgroup>
					<col class="col_num" />
					<col class="col_etc" />
					<col class="col_title" />
					<col class="col_select" />
					<col class="col_select" />
					<col class="col_adm" />
				</colgroup>			
				<thead>
					<tr>
						<th scope="col">
							<span class="chk_area"><label for="chk_all" onclick="fncCheckAll(chk_all, 'op_idx')">All checked<input type="checkbox" value="" name="" id="chk_all" /></label></span>
						</th>
						<th scope="col">Table</th>
						<th scope="col">게시판 이름</th>
						<th scope="col">스킨</th>
						<th scope="col">관리자 스킨</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${boardOptionList }" var="optionVo">
					<tr>
						<td><span class="chk_area"><label><input type="checkbox" value="${optionVo.op_idx }" name="op_idx" /></label></span></td>
						<td><a class="link" href="update.do?op_idx=${optionVo.op_idx }">${optionVo.op_table }</a></td>
						<td class="left"><a class="link" href="update.do?op_idx=${optionVo.op_idx }">${optionVo.op_name }</a></td>
						<td>${optionVo.op_skin }</td>
						<td>${optionVo.op_adm_skin }</td>
						<td><a class="btn_close" href="boardOptionDelete.do?op_idx=${optionVo.op_idx }">삭제</a></td>
					</tr>
					</c:forEach>
					<c:if test="${totalCount == 0 }">
					<tr><td colspan="6">게시물이 없습니다.</td></tr>
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
					<input type="submit" class="btn01" value="Delete" onclick="return boardOptionDel();"/>
				</div>
				<div class="button_area_right">
					<a href="write.do" class="btn02">Add New</a>
				</div>
			</div>
			<!-- //board_list_bottom -->
			
		</form>
		<script>
			function boardOptionDel(){
				var f = document.frm;
				f.action = "delete.do";
				f.submit();
			}
		</script>
	</div>
</section>
<c:import url="/admin/layout/footer.jsp"></c:import>