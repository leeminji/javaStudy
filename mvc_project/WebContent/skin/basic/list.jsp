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
				</div>	
				<!-- 정렬 -->
				<div class="sort_list">
					<a href="${sort_link }&sst=bo_regdate" <c:if test="${ empty param.sst }">class="on"</c:if> <c:if test="${param.sst == 'bo_regdate' }">class="on"</c:if> >등록순</a> 
					<a href="${sort_link }&sst=bo_hit" <c:if test="${param.sst == 'bo_hit' }">class="on"</c:if> >조회수</a> 
					<a href="${sort_link }&sst=bo_subject" <c:if test="${param.sst == 'bo_subject' }">class="on"</c:if>>제목순</a> 
				</div>
				<!-- //정렬 -->
				
				<form method="post" name="frm">
					<input type="hidden" name="bo_table" value="${optionVo.op_table }" />
					<div class="board_list">
						<table>
							<colgroup>
								<col class="col_num m_area" />
								<col class="col_title" />
								<col class="col_author" />
								<col class="col_date m_area" />
								<col class="col_hit m_area" />
							</colgroup>					
							<thead>
								<tr>
									<th class="m_area">No.</th>
									<th scope="col">제목</th>
									<th scope="col">작성자</th>
									<th scope="col" class="m_area">작성일</th>
									<th scope="col" class="m_area">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${boardList }" var="bVo">
								<tr>
									<td class="m_area">${bVo.bo_eq }</td>
									<td class="left">
										<a class="link" href="view.do${link }&bo_idx=${bVo.bo_idx }">
										<c:forEach begin="1" end="${bVo.bo_level }"><span class="icon_reply">[답변]</span></c:forEach>
										<span class="subject_area">${bVo.bo_subject } </span>
										<c:if test="${bVo.bo_is_secret == 1 }"><span class="icon_secret">[비밀글]</span></c:if>
										</a>
									</td>
									<td>${bVo.bo_writer }</td>
									<td class="m_area" ><fmt:formatDate type="Date" value="${bVo.bo_regdate }" /></td>
									<td class="m_area" >${bVo.bo_hit }</td>
								</tr>
								</c:forEach>
								<c:if test="${totalCount == 0 }">
								<tr>
									<td colspan="5">게시물이 없습니다.</td>
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
						<div class="button_area_right">
							<c:if test="${is_write }">
							<a href="write.do${link }" class="btn02"  onclick="return writeBoard()" >글쓰기</a>
							</c:if>
						</div>
					</div>
					<!-- //board_list_bottom -->
				</form>
				<script>
					//게시물작성
					function writeBoard(){
						var f= document.frm;
						f.action = "write.do";
					}
				</script>
			</div>
		</div>
	</div>
</section>
<!-- //content -->
<c:import url="../../layout/footer.jsp"></c:import>