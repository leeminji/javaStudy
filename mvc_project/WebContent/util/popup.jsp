<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${popList }" var="pVo" varStatus="popCount">
	<div class="popup_area" id="pop${pVo.po_idx }" style="left:${pVo.po_left}px;top:${pVo.po_top}px;">
		<div class="popup_inner">
			<div class="popup_con" style="width:${pVo.po_width}px;height:${pVo.po_height}px;">
			${pVo.po_content}
			</div>
			<div class="popup_close">
			<label><input type="checkbox" name="chk_view" value="${pVo.po_disable_hours }" />${pVo.po_disable_hours }시간 동안 보이지 않습니다.</label>
			<a href="javascript:closeMainPopup('pop${pVo.po_idx }');">[닫기]</a>
			</div>
		</div>
	</div>
</c:forEach>