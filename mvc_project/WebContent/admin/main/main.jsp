<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/admin/layout/header.jsp"></c:import>

<section class="content" id="content">
	<div class="clearfix main_area01">
		<div class="welcome_text">
			<h2 class="title01">Dashboard.</h2>
			<p><em>Welcome to dashboard!</em><br />회원과 게시판을 설정할 수 있습니다.</p>
		</div>
	</div>
	<div class="clearfix main_area02">
		<section class="unit_area recent_area">
			<div class="inner">
			<h2><span class="icon_calendal"></span>Recent Member</h2>
			<div class="scroll_wrap">
				<div class="scroll_inner">
					<ul class="clear list_recent">
						<c:forEach items="${memberList }" var="mVo">
						<li>
							<a href="${PATH }/adm/member/write.do?mb_idx=${mVo.mb_idx}">
								<span class="img"><img src="${IMG_PATH }/temp_img01.png" alt="${mVo.mb_name }"></span>
								<span class="text">${mVo.mb_id }<br />${mVo.mb_name }<br />${mVo.mb_email }</span>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<a href="${PATH }/adm/member/list.do" class="btn_all">see all</a>
			</div>
		</section>
		<section class="unit_area address_area">
			<div class="inner">
			<h2><span class="icon_calendal"></span>Recent Board</h2>
			<div class="scroll_wrap">
				<div class="scroll_inner">
				<ul class="clear list_address">
					<li><a href="" class="tit ellipsis">Wassim Awadallah Wassim Awadallah Wassim Awadallah</a> <span class="btn"><a href="">Edit</a>.<a href="">Delete</a></span></li>
					<li><a href="" class="tit ellipsis">Wassim Awadallah</a> <span class="btn"><a href="">Edit</a>.<a href="">Delete</a></span></li>
					<li><a href="" class="tit ellipsis">Wassim Awadallah</a> <span class="btn"><a href="">Edit</a>.<a href="">Delete</a></span></li>
					<li><a href="" class="tit ellipsis">Wassim Awadallah</a> <span class="btn"><a href="">Edit</a>.<a href="">Delete</a></span></li>
					<li><a href="" class="tit ellipsis">Wassim Awadallah</a> <span class="btn"><a href="">Edit</a>.<a href="">Delete</a></span></li>
					<li><a href="" class="tit ellipsis">Wassim Awadallah</a> <span class="btn"><a href="">Edit</a>.<a href="">Delete</a></span></li>
					<li><a href="" class="tit ellipsis">Wassim Awadallah</a> <span class="btn"><a href="">Edit</a>.<a href="">Delete</a></span></li>
					<li><a href="" class="tit ellipsis">Wassim Awadallah</a> <span class="btn"><a href="">Edit</a>.<a href="">Delete</a></span></li>
					<li><a href="" class="tit ellipsis">Wassim Awadallah</a> <span class="btn"><a href="">Edit</a>.<a href="">Delete</a></span></li>
				</ul>
				</div>
			</div>
			<a href="" class="btn_all">see all</a>
			</div>
		</section>
	</div>
	<div class="clearfix main_area03">
		<section class="unit_area notice_area">
			<div class="inner">
			<h2><span class="icon_calendal"></span>Alert Messages & Notifications</h2>
			<ul class="clear list_notice">
				<li class="icon_noitce">Liala added a new comment to the public form.<a href="#" class="btn_notice"></a></li>
				<li class="icon_check">Liala added a new comment to the public form.<a href="#" class="btn_check"></a></li>
				<li class="icon_close">Liala added a new comment to the public form.<a href="#" class="btn_close"></a></li>
			</ul>
			</div>
		</section>
		<section class="unit_area recent_area">
			<div class="inner">
			<h2><span class="icon_calendal"></span>Recent Signups</h2>
			<div class="scroll_wrap">
				<div class="scroll_inner">
				<ul class="clear list_recent">
					<li>
						<a href="#">
							<span class="img"><img src="${IMG_PATH }/temp_img01.jpg" alt=""></span>
							<span class="text">Liala added a new comment to the public form.</span>
						</a>
					</li>
					<li>
						<a href="#">
							<span class="img"><img src="${IMG_PATH }/temp_img01.jpg" alt=""></span>
							<span class="text">Liala removed Freddy from her friend without giving any reason.</span>
						</a>
					</li>
					<li>
						<a href="#">
							<span class="img"><img src="${IMG_PATH }/temp_img01.jpg" alt=""></span>
							<span class="text">Liala added a new comment to the public form.</span>
						</a>
					</li>
				</ul>
				</div>
			</div>
			<a href="" class="btn_all">see all</a>
			</div>
		</section>
	</div>
</section>

<c:import url="/admin/layout/footer.jsp"></c:import>