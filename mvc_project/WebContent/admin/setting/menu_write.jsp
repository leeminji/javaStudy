<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/admin/layout/header.jsp"></c:import>

<section class="content" id="content">
	<div class="title_text">
		<h2 class="title01">Menu.</h2>
		<p>주의! 메뉴설정 작업 후 반드시 저장을 누르셔야 저장됩니다.</p>
	</div>
	<form action="writeOk.do" method="post">
	<div class="menu_list_area">
		<div class="inner clearfix">
			<div class="float_l menu_list">
				<div class="menu_title bg_black30">
					<h3 class="title04">MENU LIST</h3>
					<a href="javascript:addMenu('top');" class="btn_add_menu btn02">대메뉴추가</a>
				</div>
				<div class="menu_area bg_black30">
					<div class="scroll_wrap">
						<div class="scroll_inner">
							<!-- mbox_area -->
							<ul class="clear mbox_area" id="mbox_area">
							</ul>
							<!-- //mbox_area -->
							<div class="btn_area_center">
								<a href="javascript:;" onclick="saveMenu()" class="btn02" id="btn_menu_save">Save</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- menu_content -->
			<div class="flot_r menu_content">
				<div class="menu_title bg_brown30">
					<h3 class="title04">MENU INFORMATION</h3>
				</div>
				<!-- menu_area -->
				<div class="menu_area bg_brown30">
					<div class="menuinfo_area" id="menuinfo_area">
					</div>
				</div>
				<!-- //menu_area -->
			</div>
			<!-- //menu_content -->
		</div>
	</div>
	</form>
</section>
<script type="text/javascript" src="${JS_PATH }/ux.menu.js?v=1"></script>
<c:import url="/admin/layout/footer.jsp"></c:import>
