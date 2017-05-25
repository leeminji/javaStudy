<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/header.jsp"></c:import>
<!-- content -->
<section class="content">
	<div class="content_area clearfix">
		<div class="section ">
			<h2 class="title01">CONTACT</h2>
			<div class="contact_text">
				<img src="${IMG_PATH }/content/about_img01.png" alt="" />
				<p>문의사항이 있을시 메일을 보내주세요.</p>
			</div>
			<form action="contact" method="post">
				<input type="hidden" name="command" value="writeOk" />
				<ul class="clear list01">
					<li><span class="tit">보내는 사람</span><span class="con"><input type="text" size="500" name="sender_name" required="required" /></span></li>
					<li><span class="tit">답변받을 메일</span><span class="con"><input type="text" size="500" name="sender_email" required="required" /></span></li>
					<li><span class="tit">제목 </span><span class="con"><input type="text" size="500" name="subject" /></span></li>
					<li><span class="tit">내용 </span><span class="con"><textarea name="content" id="content" cols="30" rows="10"></textarea></span></li>
				</ul>
				<div class="btn_area_right">
				<input type="submit" value="보내기" class="btn01"/>
				</div>
			</form>
		</div>
	</div>
</section>
<!-- //content -->
<c:import url="../layout/footer.jsp"></c:import>