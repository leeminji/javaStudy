/*
 * ux.popup.js
 * 팝업
 */

//팝업 미리보기
function previewPopup(f, className) {
	var t = f.po_top.value + "px";
	var l = f.po_left.value + "px";
	var w = f.po_width.value + "px";
	var h = f.po_height.value + "px";
	var c = f.po_color_content.value;
	var c_line = f.po_color_line.value;
	var content = f.po_content.value;
	var hour = f.po_disable_hours.value;

	var layer_area = $(".layer_area");
	var blind = $(".blind");
	$("<div>", {
		css : {
			"position" : "absolute",
			"zIndex" : "100",
			"top" : t,
			"left" : l,
			"width" : w,
			"height" : h,
			"color" : "#000",
			"background-color" : c,
			"border" : "3px solid " + c_line
		},
		class : className + " layer_popup",
		text : content
	}).appendTo(layer_area);
	$(
			"<div>",
			{
				css : {
					"position" : "absolute",
					"bottom" : 0,
					"left" : 0,
					"right" : 0,
					"background-color" : c_line,
					"line-height" : "20px"
				},
				class : "layer_popup_bottom",
				html : "<label><input type='checkbox' id='chk_view_popup' value='1' />"
						+ hour
						+ " 시간동안 보이지 않습니다. </label>"
						+ "<a href='javascript:closePopup(\""
						+ className
						+ "\");'>[닫기]</a>"
			}).appendTo($(".layer_popup"));

	layer_area.show();
	blind.show();
}
/*
 * 프리뷰(설정) 팝업창 닫기
 * */
function closePopup(className) {
	var layer_area = $(".layer_area");
	var blind = $(".blind");

	$("." + className).hide();
	layer_area.hide();
	blind.hide();
}
/*
 * 메인 팝업창 닫기
 * */
function closeMainPopup(id) {
	var popup = $("#" + id);
	var chk_view = popup.find("input[name='chk_view']");
	if( chk_view.is(":checked") ){
		setCookie(id, "popup", 1);
	}
	popup.hide();
}

/**
 * 쿠키값 추출
 * 
 * @param cookieName 쿠키명
 *            
 */
function getCookie(cookieName) {
	var search = cookieName + "=";
	var cookie = document.cookie;
	// 현재 쿠키가 존재할 경우
	if (cookie.length > 0) {
		// 해당 쿠키명이 존재하는지 검색한 후 존재하면 위치를 리턴.
		startIndex = cookie.indexOf(cookieName);
		// 만약 존재한다면
		if (startIndex != -1) {
			// 값을 얻어내기 위해 시작 인덱스 조절
			startIndex += cookieName.length;
			// 값을 얻어내기 위해 종료 인덱스 추출
			endIndex = cookie.indexOf(";", startIndex);
			// 만약 종료 인덱스를 못찾게 되면 쿠키 전체길이로 설정
			if (endIndex == -1)
				endIndex = cookie.length;
			// 쿠키값을 추출하여 리턴
			return unescape(cookie.substring(startIndex + 1, endIndex));
		} else {
			// 쿠키 내에 해당 쿠키가 존재하지 않을 경우
			return false;
		}
	} else {
		// 쿠키 자체가 없을 경우
		return false;
	}
}

/**
 * 쿠키 설정
 * 
 * @param cookieName 쿠키명
 *            
 * @param cookieValue 쿠키값
 *            
 * @param expireDay 쿠키 유효날짜
 *            
 */
function setCookie(cookieName, cookieValue, expireDate) {
	var today = new Date();
	today.setDate(today.getDate() + parseInt(expireDate));
	document.cookie = cookieName + "=" + escape(cookieValue)
			+ "; path=/; expires=" + today.toGMTString() + ";";
}

/**
 * 쿠키 삭제
 * 
 * @param cookieName
 *            삭제할 쿠키명
 */
function deleteCookie(cookieName) {
	var expireDate = new Date();

	// 어제 날짜를 쿠키 소멸 날짜로 설정한다.
	expireDate.setDate(expireDate.getDate() - 1);
	document.cookie = cookieName + "= " + "; expires="
			+ expireDate.toGMTString() + "; path=/";
}

/**
 * 자신이 지정한 값으로 쿠키 설정
 */
function setMyCookie() {
	setCookie(form.setName.value, form.setValue.value, form.expire.value);
	viewCookie(); // 전체 쿠키 출력 갱신
}

/**
 * 자신이 지정한 쿠키명으로 확인
 */
function getMyCookie() {
	alert("쿠키 값 : " + getCookie(form.getName.value));
}

/**
 * 자신이 지정한 쿠키명으로 쿠키 삭제
 */
function deleteMyCookie() {
	deleteCookie(form.deleteName.value);
	alert("쿠키가 삭제되었습니다.");
	viewCookie();
}

/**
 * 전체 쿠키 출력
 */
function viewCookie() {
	if (document.cookie.length > 0)
		cookieOut.innerText = document.cookie;
	else
		cookieOut.innerText = "저장된 쿠키가 없습니다.";
}
