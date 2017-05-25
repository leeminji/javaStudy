<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/admin/layout/header_sub.jsp"></c:import>
<div class="allwrap cover bg01">
	<div id="container" class="container_cover">
		<!-- login_area -->
		<form action="loginOK.do" method="post">
		<fieldset>
		<legend class="skip">로그인폼</legend>
			<div class="login_area">
				<h2 class="login_title"><span class="icon_login"></span>Dashboard Login</h2>
				<div class="login_list">
					<ul class="clear">
						<li class="icon_id"><input type="text" placeholder="e-mail address" name="mb_id" /></li>
						<li class="icon_pw"><input type="password" placeholder="password" name="mb_pass" /></li>
					</ul>
					<div class="login_btn">
						<input type="submit" class="btn_login" value="LOGIN" />
					</div>
					<div class="login_bottom">
						<a href="${PATH }/pf/index.do">메인으로</a>
					</div>
				</div>
			</div>
		</fieldset>
		</form>
		<!-- //login_area -->
	</div>
</div>	
<c:import url="/admin/layout/footer_sub.jsp"></c:import>