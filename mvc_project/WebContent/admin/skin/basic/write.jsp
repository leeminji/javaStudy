<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/admin/layout/header.jsp"></c:import>
<section class="content" id="content">
	<div class="title_text">
		<c:choose>
		<c:when test="${update }">
		<h2 class="title01">${optionVo.op_name } Modify.</h2>
		</c:when>
		<c:when test="${reply }">
		<h2 class="title01">${optionVo.op_name } Reply.</h2>
		</c:when>
		<c:otherwise>
		<h2 class="title01">${optionVo.op_name } Write.</h2>
		</c:otherwise>
		</c:choose>
		<p>입력해주세요.</p>
	</div>
	
	<form method="post" name="frm" action="writeOk.do" enctype="multipart/form-data">
		<input type="hidden" name="bo_table" value="${optionVo.op_table }" />
		<c:choose>
		<c:when test="${update }">
		<input type="hidden" name="command" value="update" />
		<input type="hidden" name="bo_idx" value="${bVo.bo_idx }" />
		</c:when>
		<c:when test="${reply }">
		<input type="hidden" name="command" value="reply" />
		<input type="hidden" name="bo_ref" value="${parent_bVo.bo_ref }" />
		<input type="hidden" name="bo_level" value="${parent_bVo.bo_level+1 }"/>
		</c:when>
		<c:otherwise>
		<input type="hidden" name="command" value="write" />
		</c:otherwise>
		</c:choose>
		<div class="board_write_area">
			<!-- 제목 -->
			<div class="clearfix board_write_top">
				<c:choose>
				<c:when test="${reply}">
				<input type="text" name="bo_subject" value="RE : ${parent_bVo.bo_subject }" placeholder="Post Title">
				</c:when>
				<c:otherwise>
				<input type="text" name="bo_subject" value="${bVo.bo_subject }" placeholder="Post Title" />
				</c:otherwise>
				</c:choose>
			</div>
			<!-- //제목 -->
			<!-- 기타설정 -->
			<div class="board_write_chk">
				<ul class="clear list01">
					<c:choose>
					<c:when test="${update}">
					<li><span class="tit">작성자</span>
						<span class="con">
						<input type="text" size="500" name="bo_writer" value="${bVo.bo_writer }" />
						</span>
					</li>
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
					</c:choose>
					<li><span class="tit">비밀글</span>
						<span class="con">
						<span class="chk_area"><label for="bo_is_secret">비밀글<input type="checkbox" id="bo_is_secret" name="bo_is_secret" value="1" <c:if test="${bVo.bo_is_secret == 1 }">checked</c:if> /></label></span>
						</span>
					</li>
					<li><span class="tit">공지</span>
						<span class="con">
						<span class="chk_area"><label for="bo_is_notice">공지<input type="checkbox" value="1" name="bo_is_notice" id="bo_is_notice" <c:if test="${bVo.bo_is_notice == 1 }">checked</c:if> /></label></span>
						</span>
					</li>
					<li><span class="tit">등록여부</span><span class="con">
						<span class="con">
						<span class="chk_area"><label for="bo_is_view">등록여부<input type="checkbox" value="1" name="bo_is_view" id="bo_is_view" <c:if test="${bVo.bo_is_view == 1 }">checked</c:if> /></label></span> 체크시 목록에 보여집니다.
						</span>
					</li>
				</ul>
			</div>
			<!-- //기타설정 -->
			<!-- board_write -->
			<div class="board_write">
				<textarea name="bo_content" id="bo_content" width="100%" height="300px">${bVo.bo_content }</textarea>
			</div>
			<!-- //board_write -->
			<!-- board_file -->
			<div class="board_file">
				<input type="hidden" name="bo_file" value="${bVo.bo_file }" />
				<c:choose>
					<c:when test="${empty bVo.bo_file || bVo.bo_file ==0  }">
					<input type="hidden" name="file_count" value="2" id="file_count" />
					</c:when>
					<c:otherwise>
					<input type="hidden" name="file_count" value="${bVo.bo_file }" id="file_count" />
					</c:otherwise>
				</c:choose>
				<div class="btn_file"><a href="javascript:addFile(4);" class="btn01">Add File</a></div>
				<ul class="clear" id="file_list">
				<c:if test="${empty fileList}">
					<li>
					<span class="num">1.</span>
					<input type="file" name="bo_file1" value="" onChange="fn_previewImg(this, 'preImg1')" />
					<span class="prevImg"><img id="preImg1" /></span>
					</li>
					<li>
					<span class="num">2.</span>
					<input type="file" name="bo_file2" value="" onChange="fn_previewImg(this, 'preImg2')" />
					<span class="prevImg"><img id="preImg2" /></span>
					</li>					
				</c:if>
				<c:forEach items="${fileList}" var="bfVo" varStatus="status">
					<li>
					<span class="num">${status.count }.</span>
					<input type="file" name="bo_file${status.count }" value="" onChange="fn_previewImg(this, 'preImg${status.count }')" />
					<span class="chk_area"><label for="bf_del${status.count }"><input type="checkbox" id="bf_del${status.count }" value="${bfVo.bf_source }" name="bf_del" /></label></span>
					<span class="ellipsis">[ 삭제시 선택해주세요 ]${bfVo.bf_file }</span>
					<span class="prevImg"><img id="preImg${status.count }" /></span>
					</li>
				</c:forEach>
				</ul>
			</div>
			<!-- //board_file -->
			<div class="board_write_chk">
				<ul class="clear list01">
					<li><span class="tit">태그입력</span>
						<span class="con"><input type="text" name="bo_tag" placeholder="태그입력" size="500" value="${bVo.bo_tag }"></span>
					</li>
				</ul>
			</div>
		</div>
		<!-- 버튼 -->
		<div class="clearfix board_write_bottom">
			<div class="button_area_left">
				<a href="list.do?bo_table=${optionVo.op_table }" class="btn01">List</a>
			</div>
			<div class="button_area_right">
				<c:choose>
				<c:when test="${update }">			
				<input type="submit" class="btn02" value="Modify" />
				</c:when>
				<c:when test="${reply }">
				<input type="submit" class="btn02" value="Reply" />
				</c:when>					
				<c:otherwise>
				<input type="submit" class="btn02" value="Write" />
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		<!-- //버튼 -->
	</form>
</section>
<c:import url="/admin/layout/footer.jsp"></c:import>
