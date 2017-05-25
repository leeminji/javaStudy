/*
	ux.menu.js
*/

var menu_items = [];

$(document).ready(function(){
	loadMenu();
});


function Menu(idx, no, title, link, text, target, view){
	this.idx = idx;
	this.title = title;
	this.no = no;
	this.link = link;
	this.text = text;
	this.target = target;
	this.view = view;

	this.getNo = function(){
		return no;
	}

	this.getParentNo = function(){
		if( no.length != 2){
			return no.substring(0, no.length-2);
		}else{
			return false;
		}
	}
}

function setInitMenuInfo(idx, len, title, link, target, text, view){
	var is_chk_on = "";
	var is_chk = "";
	var is_chk_on2 = "";
	var is_chk2 = "";	
	if( target == 1 ){
		is_chk_on = "class='on'";
		is_chk = "checked='checked'";
	}
	if( view == 1 ){
		is_chk_on2 = "class='on'";
		is_chk2 = "checked='checked'";
	}	
	var output = '<dl class="clear">';
	output += '<input type="hidden" name="idx" value="'+idx+'" />';
	output += '<dt><input type="text" name="title" value="'+title+'" size="400"></dt>';
	output += '<dd><span class="tit">링크</span><span class="con"><input type="text" name="link" value="'+link+'" size="400" /></span></dd>';
	output += '<dd><span class="tit">새창여부</span><span class="con">';
	output += '<span class="chk_area"><label '+is_chk_on+'><input type="checkbox"'+is_chk+' value="1" name="target"></label></span>';
	output += '</span></dd>';
	output += '<dd><span class="tit">보이기</span>';
	output += '<span class="con"><span class="chk_area"><label '+is_chk_on2+'><input type="checkbox"'+is_chk2+' value="1" name="view"></label></span></span></dd>';
	output += '<dd><span class="tit">기타정보</span>';
	output += '<span class="con"><input type="text" name="text" value="'+text+'" size="400" /></span></dd>';
	output += '</dl>';
	output += '<div class="btn_area">';
	output += '<a href="javascript:;" class="btn02" onclick="saveMenuInfo('+len+');">저장</a> <a href="javascript:;" onclick="cancleMenu('+len+');" class="btn02">삭제</a>';
	output += '</div>';
	return output;
}

function setInitMBox(len, no, title){
	var output = "";
	output += "<li class=code_"+no+" index="+len+">";
	output += "<div class='mbox'>";
	output += '<span class="num"><strong class="skip">메뉴번호</strong>'+no+'</span>';
	output += ' <span class="title"><strong class="skip">메뉴명</strong>'+title+'</span>';
	output += '<span class="btn">';
	output += '<a href="javascript:;" onclick="setMenuInfo('+len+', this)" class="btn01">설정</a> <a href="javascript:;" onclick="javascript:addMenu('+len+')" class="btn01">추가</a>';
	output += '</span>';
	output += '</div>';
	output += '<ul class="clear submenu"></ul>';
	output += "</li>";
	return output;
}

//메뉴정보저장
function saveMenuInfo(no){
	var menuinfo_area = $("#menuinfo_area"); //메뉴정보
	var menuInfo = menu_items[no];
	var me_num = no;
	var command = "insert";
	
	menuInfo.idx = menuinfo_area.find("input[name='idx']").val();
	menuInfo.title = menuinfo_area.find("input[name='title']").val();
	menuInfo.text = menuinfo_area.find("input[name='text']").val();
	menuInfo.link = menuinfo_area.find("input[name='link']").val();

	if( menuinfo_area.find("input[name='target']").prop("checked") ){
		menuInfo.target = "1";
	}else{
		menuInfo.target = "0";
	}
	if( menuinfo_area.find("input[name='view']").prop("checked") ){
		menuInfo.view = "1";
	}else{
		menuInfo.view = "0";
	}	
	
	// idx값이 있는 경우 
	if( menuInfo.idx != '' ){
		command = "update";
	}else{
		command = "insert";
	}
	$.ajaxSettings.traditional = true;//배열 형태로 서버쪽 전송을 위한 설정
	$.ajax({
		url : "writeOk.do",
		type : "POST",
		data : {
			"command" : command,
			"me_code" : menuInfo.no,
			"me_link" : menuInfo.link,
			"me_name" : menuInfo.title,
			"me_num" : me_num,
			"me_idx" : menuInfo.idx,
			"me_content" : menuInfo.text,
			"me_is_view" : menuInfo.view,
			"me_is_target" : menuInfo.target
 		},
 		success : function(data){
			alert(data);
			setMenuList();
		}
	});
}

//메뉴저장 취소
function cancleMenu(no){
	var menuinfo_area = $("#menuinfo_area"); //메뉴정보
	var menu_item = menu_items[no];
	var me_idx = menu_item.idx;
	menu_items.splice(no, 1);
	$.ajax({
		url : "delete.do",
		type : "post",
		data : {
			"me_idx" : me_idx
		},
		success : function(data){
			alert(data);
			if( setMenuList() ){
				menuinfo_area.empty();
			}
		}
	});	
}

//메뉴 정보 설정
function setMenuInfo(no, e){
	var menuinfo_area = $("#menuinfo_area"); //메뉴정보
	var mbox_area = $("#mbox_area"); //메뉴리스트
	var menuInfo = menu_items[no];
	var mBox = $(".code_"+menuInfo.getNo()).children('.mbox');

	mbox_area.find('.mbox').removeClass('on');
	mBox.addClass('on');
	
	menuinfo_area.empty();
	//title, link, target, text , etc
	menuinfo_area.append(setInitMenuInfo(menuInfo.idx, no, menuInfo.title, menuInfo.link, menuInfo.target, menuInfo.text, menuInfo.view ));
	if( menuinfo_area.parent().height() - $(e).offset().top > 0){;
		menuinfo_area.css({ top: $(e).offset().top-(menuinfo_area.height()+50) });
	}else{
		menuinfo_area.css({ top:  menuinfo_area.parent().height()-menuinfo_area.height()-50 });
	}
	fncCheckBox($(".chk_area"));
}

//메뉴 리스트 추가
function addMenu(no){
	// 자바스크립트에서 false : "", null, undefined, 0, NaN 
	var add_code = "";
	var menuinfo_area = $("#menuinfo_area"); //메뉴정보
	var mbox_area = $("#mbox_area");
	//대메뉴추가
	if( no == "top"){
		var num = ""+(mbox_area.children('li').length+1)*10;
		menu_items.push( new Menu( "", num , "", "", "", "", ""));
		add_code = ""+num;
	}else{
	//하위메뉴추가
		var p_no = menu_items[no].getNo();
		var c_no = $(".code_"+p_no).children('ul').children('li').length;
		var total_no = p_no + (c_no+1)*10;
		menu_items.splice(no+1+c_no, 0, new Menu("", total_no,"설정을 클릭해주세요","", "", "", ""));
		add_code = total_no;
	}
	if ( setMenuList() ){
		$(".code_"+add_code).children('.mbox').addClass('on');
	}
	scrollCustom($('.scroll_wrap')); //스크롤설정
}

//리스트 정렬
function setMenuList(){
	if( menu_items.length > 0 ){
		var mbox_area = $("#mbox_area");
		mbox_area.empty();
		for( var i = 0; i<menu_items.length;i++ ){
			if( !menu_items[i].getParentNo() ){
				mbox_area.append( setInitMBox( i ,menu_items[i].no, menu_items[i].title) );
			}else{
				mbox_area.find('.code_'+ menu_items[i].getParentNo() ).children('ul').append( setInitMBox(i, menu_items[i].no, menu_items[i].title ) );
			}
		}
	}
	
	mbox_area.parent().find('ul').each(function(){
		$(this).sortable({
			cancel: "a, input, select, label",
			placeholder: "ui-state-highlight",
			stop : function ( event, ui ){
				var temp_arr = menu_items;
				menu_items = [];
				mbox_area.find('li').each(function(index){
					var i = $(this).attr('index');
					menu_items[index] = temp_arr[i];
				});
				setMenuList();
			}
		}).disableSelection();
	});
	return true;
}

function loadMenu(){
	var now = new Date().getTime();
	var mbox_area = $("#mbox_area");
	$.ajax({
		url : "list.do",
		dataType : "json",
		beforeSend : function(){
			mbox_area.append("<div class='loader'></div>");
		},
		success : function(data){
			mbox_area.empty();
			for( key in data.menu ){
				menu_items.push( new Menu(
					data.menu[key]["me_idx"], 	
					data.menu[key]["no"], 
					data.menu[key]["title"], 
					data.menu[key]["link"], 
					data.menu[key]["text"], 
					data.menu[key]["target"],
					data.menu[key]["view"]) 
				);
			}
			setMenuList();
		}
	});
}

//메뉴 저장
function saveMenu(){
	// 배열에 객체의 키 값을 모두 추가
	var me_idxs = []; 
	var me_codes = []; 
	var me_links = [];
	var me_names = [];
	var me_nums = [];
	var me_contents = [];
	var me_is_views = [];
	var me_is_targets = [];
	
	for (var i =0; i< menu_items.length;i++) {
		me_idxs.push(menu_items[i].idx);
		me_codes.push(menu_items[i].no);
		me_links.push(menu_items[i].link);
		me_names.push(menu_items[i].title);
		me_nums.push(i);
		me_contents.push(menu_items[i].text);
		me_is_views.push(menu_items[i].view);
		me_is_targets.push(menu_items[i].target);
	}
	console.log(me_is_views)
	$.ajaxSettings.traditional = true; //배열 형태로 서버쪽 전송을 위한 설정
	$.ajax({
		url : "writeOk.do",
		type : "POST",
		data : {
			"command" : "update",
			"me_code" : me_codes,
			"me_link" : me_links,
			"me_name" : me_names,
			"me_idx" : me_idxs,
			"me_content" : me_contents,
			"me_num" : me_nums,
			"me_is_view" : me_is_views,
			"me_is_target" : me_is_targets
		}, success : function(data){
			alert(data);
		}
	});
}