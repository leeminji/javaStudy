<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/header.jsp"></c:import>
<!-- content -->
<section class="content">
	<div class="content_area about_area clearfix">
		<div class="section">
			<h2 class="title01">비밀번호확인</h2>
			<form action="./boardCheckPass.do" method="post">
			<fieldset>
				<legend class="hidden">비밀번호확인</legend>
				<input type="hidden" name="bo_idx" value="${bVo.bo_idx }" />
				<input type="hidden" name="bo_table" value="${bVo.bo_table }" />
				<input type="hidden" name="command" value="${param.command }" />
				<div class="board_chk_pass">
					<p class="hint">게시물을 입력할 때 설정한 비밀번호를 입력해주세요.<br />${message }</p>
					<ul class="clear list01">
						<li><span class="tit">제목</span>
							<span class="con">${bVo.bo_subject }</span>
						</li>
						<li><span class="tit">비밀번호</span>
							<span class="con"><input type="password" name="bo_pass" size="20" /></span>
						</li>
					</ul>
				</div>
				
				<div class="btn_area_center">
					<a href="javascript:" onclick="window.history.go(-1)" class="btn02">뒤로</a>	
					<a href="../member/login"  class="btn02" >로그인</a>
					<input type="submit" value="확인" class="btn01" />		
				</div>				
			</fieldset>

			</form>
		</div>
	</div>
</section>
<c:import url="../layout/footer.jsp"></c:import>