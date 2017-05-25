<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/admin/layout/header.jsp"></c:import>

<section class="content" id="content">
	<div class="title_text">
		<h2 class="title01">Popup.</h2>
		<p>전체 <strong>${totalCount }</strong>건</p>
	</div>
	<div class="board_list_area">
		<div class="clearfix board_list_top">
			<!-- search_area -->
			<form action="" name="fboardSearch">
			<div class="search_area">
				<select name="select01" id="select01" class="input-select">
					<option value="title">Title</option>
					<option value="content">Content</option>
				</select>
				<input type="text" name="stx" value="" placeholder="Search" />
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
		<!-- board_list -->
		<form name="frm" method="post">
		<div class="board_list">
			<table>
				<colgroup>
					<col class="col_num" />
					<col class="col_num" />
					<col class="col_title" />
					<col class="col_datetime" />
					<col class="col_datetime" />
					<col class="col_etc" />
					<col class="col_etc" />
					<col class="col_state" />
					<col class="col_adm2" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col">
							<span class="chk_area"><label for="chk_all" onclick="fncCheckAll(chk_all, 'po_idx')">All checked<input type="checkbox" value="" name="" id="chk_all" /></label></span>
						</th>
						<th scope="col">No.</th>
						<th scope="col">제목</th>
						<th scope="col">Start Time</th>
						<th scope="col">Last Time</th>
						<th scope="col">Left / Top</th>
						<th scope="col">Width / Height</th>
						<th scope="col">State</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${popupList }" var="pVo">
					<tr>
						<td><span class="chk_area"><label><input type="checkbox" value="${pVo.po_idx }" name="po_idx" /></label></span></td>
						<td>${pVo.po_eq }</td>
						<td class="left"><a href="write.do?po_idx=${pVo.po_idx }" class="link">${pVo.po_subject }</a></td>
						<td><fmt:formatDate value="${pVo.po_begin_time }" pattern="YYYY-MM-dd HH:00" /></td>
						<td><fmt:formatDate value="${pVo.po_end_time }" pattern="YYYY-MM-dd HH:00" /></td>
						<td>${pVo.po_left }px / ${pVo.po_top }px</td>
						<td>${pVo.po_width }px / ${pVo.po_height }px</td>
						<td>${pVo.po_state }</td>
						<td>
							<a href="delete.do?po_idx=${pVo.po_idx }" class="btn_close"><span class="skip">delete</span></a>
						</td>
					</tr>
					</c:forEach>
					<c:if test="${totalCount == 0 }">
					<tr>
						<td colspan="9">데이터가 없습니다.</td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		</form>
		<!-- //board_list -->
		<!-- board_list_bottom -->
		<div class="clearfix board_list_bottom">
			<div class="button_area_left">
				<a href="javascript:popupDel();" class="btn01">Delete</a>
			</div>
			<div class="button_area_right">
				<a href="write.do" class="btn02">Add New</a>
			</div>
		</div>
		
		<!-- //board_list_bottom -->
		<!-- 페이징 -->
		<c:set var="paging_param" value="${paging }" scope="request" />
		<c:import url="/util/paging.jsp"></c:import>
		<!-- //페이징 -->
	</div>
	<script>
		function popupDel(){
			alert("삭제하시겠습니까?");
			var f = document.frm;
			f.action = "delete.do";
			f.submit();
		}	
	</script>	
</section>

<c:import url="/admin/layout/footer.jsp"></c:import>