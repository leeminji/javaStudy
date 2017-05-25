<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/admin/layout/header.jsp"></c:import>
<section class="content" id="content">
	<div class="title_text">
		<h2 class="title01">Calendar.</h2>
		<p>스케쥴을 정할 수 있습니다.</p>
	</div>
	<div class="schedule_area">
		<div class="btn_area_right">
			<a href="javascript:;" class="btn02" onclick="setAddShedule();">Add</a>
		</div>
		<!-- calendar_info -->
		<div class="calendar_info clearfix" id="calendar_info"></div>
		<!-- //calendar_info -->
		<div class="calendar_area">
			<div class="row_week day_week">
				<div class="col_day"><div class="inner">Sunday</div></div>
				<div class="col_day"><div class="inner">Monday</div></div>
				<div class="col_day"><div class="inner">Tuesday</div></div>
				<div class="col_day"><div class="inner">Wednesday</div></div>
				<div class="col_day"><div class="inner">Thursday</div></div>
				<div class="col_day"><div class="inner">Friday</div></div>
				<div class="col_day"><div class="inner">Saturday</div></div>
			</div>
			<!-- calendar_day_area -->
			<div class="calendar_day_area" id="calendar_day_area"></div>
			<!-- //calendar_day_area -->
		</div>
	</div>
</section>
<script src="${JS_PATH }/ux.calendar.js"></script>
<c:import url="/admin/layout/footer.jsp"></c:import>