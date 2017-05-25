<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/admin/layout/header.jsp"></c:import>

<section class="content" id="content">
	
	<div class="title_text">
		<h2 class="title01">Member.</h2>
		<p>총회원수 <strong>2명</strong> 중, 차단 <strong>0명</strong>, 탈퇴 <strong> 0명</strong></p>
	</div>
	
	<div class="board_cate_area">
		<ul class="clear">
			<li <c:if test="${empty param.sfl}">class='on'</c:if>><a href="list.do">전체</a></li>
			<li <c:if test="${param.sfl=='mb_level' && param.stx==1 }">class='on'</c:if> ><a href="list.do?&sfl=mb_level&stx=1">일반회원(1)</a></li>
			<li <c:if test="${param.sfl=='mb_level' && param.stx==10 }">class='on'</c:if> ><a href="list.do?&sfl=mb_level&stx=10">관리자(10)</a></li>
		</ul>
	</div>
	
	<div class="board_list_area">
		<div class="clearfix board_list_top">
			<!-- search_area -->
			<form action="list.do" name="fboardSearch" method="get">
			<div class="search_area">
				<select name="sfl" id="select01" class="input-select">
					<option value="" <c:if test="${empty param.sfl }"> selected </c:if> >선택</option>
					<option value="mb_id" <c:if test="${param.sfl == 'mb_id' }"> selected </c:if> >아이디</option>
					<option value="mb_level" <c:if test="${param.sfl == 'mb_level' }"> selected </c:if> >회원권한</option>
					<option value="mb_name" <c:if test="${param.sfl == 'mb_name' }"> selected </c:if> >이름</option>
					<option value="mb_email" <c:if test="${param.sfl == 'mb_email' }"> selected </c:if> >이메일</option>
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
		<!-- board_list -->
		<form name="frm" method="post">
			<div class="board_list">
				<table>
					<colgroup>
						<col class="col_num" />
						<col />
						<col class="col_m_name" />
						<col class="col_m_phone" />
						<col class="col_m_date" />
						<col class="col_m_date" />
						<col class="col_m_lank" />
						<col class="col_adm" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">
								<span class="chk_area"><label for="chk_all" onclick="fncCheckAll(chk_all, 'chk_id')">All checked<input type="checkbox" value="" name="" id="chk_all" /></label></span>
							</th>
							<th scope="col">아이디</th>
							<th scope="col">이름</th>
							<th scope="col">전화번호</th>
							<th scope="col">가입일</th>
							<th scope="col">접속일</th>
							<th scope="col">회원권한</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${memberList }" var="mVo">
						<tr style="<c:if test="${mVo.mb_level == 0 }">background:#eee</c:if>" >
							<td><span class="chk_area"><label><input type="checkbox" value="${mVo.mb_idx }" name="mb_idx" /></label></span></td>
							<td class="left"><a href="write.do?mb_idx=${mVo.mb_idx }" class="link">${mVo.mb_id }</a></td>
							<td>${mVo.mb_name }</td>
							<td>${mVo.mb_hp }</td>
							<td><fmt:formatDate type="date" value="${mVo.mb_regdate}" /></td>
							<td><fmt:formatDate type="date" value="${mVo.mb_lastest}" /></td>
							<td>${mVo.mb_level }</td>
							<td>
								<a href="delete.do?mb_idx=${mVo.mb_idx }" class="btn_close"><span class="skip">delete</span></a>
								<a href="out.do?mb_idx=${mVo.mb_idx }" class="btn_check"><span class="skip">modify</span></a>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${totalCount == 0 }">
					<tr><td colspan="8">게시물이 없습니다.</td></tr>
					</c:if>	
					</tbody>
				</table>
			</div>
			<!-- //board_list -->
			<!-- 페이징 -->
			<c:set var="paging_param" value="${paging }" scope="request" />
			<c:import url="/util/paging.jsp"></c:import>
			<!-- //페이징 -->
						
			<!-- board_list_bottom -->
			<div class="clearfix board_list_bottom">
				<div class="button_area_left">
					<input type="submit" class="btn01" value="Delete" onclick="return memberDel();"/>
					<input type="submit" class="btn01"value="탈퇴" onclick="return memberOut();"/>
				</div>
				<div class="button_area_right">
					<a href="write.do" class="btn02">Add New</a>
				</div>
			</div>
			<!-- //board_list_bottom -->
		</form>
	</div>
	
	<script>
		function memberDel(){
			var f = document.frm;
			f.action = "delete.do";
			f.submit();
		}
		function memberOut(){
			var f = document.frm;
			f.action = "out.do";
			f.submit();
		}	
	</script>	
</section>

<c:import url="/admin/layout/footer.jsp"></c:import>