/*
	ajax.menu.js
	메뉴설정
*/
$(function(){
	setMenu($("#menu_list_area") );
	var menu_arr = new Array();

	function Menu(m_code, m_title, m_link){
		this.m_code = m_code;
		this.m_title = m_title;
		this.m_link = m_link;
	}

	function setMenuBox(m_code, m_title, m_link, p_code, state){
		var state = state;
		var output = "";
		var len = m_code.length-2;
		var margin = 10*len;
		var c_code = getStringLenLast( m_code, len); //순서코드
		//수정 가능 /불가능
		var is_read = "";
		if( state ){
			is_read = "readonly='readonly'";
		}

		output += '<li class="inner" id="L_'+m_code+'">';
		output +=	'<input type="hidden" name="m_name[]" value = "'+m_code+'" />';
		output +=	'<div class="box">';
		output +=		'<div class="info">';
		output +=		'<span class="m_code">'+m_code+'</span>';
		output +=		'<label class="m_title">'
		output +=		'<span class="tit">메뉴명</span><input type="text" '+is_read+' name="m_title[]" value="'+m_title+'" size="100" /></label>';
		output +=		'<label class="m_link">';
		output +=		'<span class="tit">링크</span><input type="text" '+is_read+' name="m_link[]" value="'+m_link+'" size="300" /></label>';
		output +=		'</div>';
		output +=		'<div class="btn">';
		if( state ){
		output +=		'<a href="javascript:;" class="btn_m_delete" data-code="L_'+m_code+'">Delete</a>';
		output +=		'<a href="javascript:;" class="btn_m_modify" data-code="L_'+m_code+'">Modify</a>';
		output +=		'<a href="javascript:;" class="btn_m_add" data-code="M_'+m_code+'">Add</a>';
		}else{
		output +=		'<a href="javascript:;" class="btn_m_cancel" data-code="L_'+m_code+'">Cancel</a>';
		output +=		'<a href="javascript:" class="btn_m_save" data-code="L_'+m_code+'">Save</a>';
		}
		output +=		'</div>';
		output +=	'</div>';
		output +=	'<ul class="clear outer" id="M_'+m_code+'"></ul>';
		output += '</li>';

		return output;
	}

	//앞에서 2자리
	function getStringLenFirst(str, num){
		return str.substr(0, num);
	}

	//뒤에서 2자리
	function getStringLenLast(str, num){
		return str.substring( str.length-num, str.length );
	}

	//버튼
	//하위메뉴추가
	function setMenuBtnAdd(con){
		//console.log("호출");
		$(".btn_m_add").each(function(){
			$(this).off("click");
			$(this).on("click", function(){
				//console.log("ADD버튼");
				var p_code = $(this).attr('data-code');
				var re_p_code = p_code.replace("L_", "");
				var outer = $("#"+re_p_code );
				//console.log( re_p_code );
				var c_code = (outer.children('.inner').size()+1)*10;
				var code = re_p_code.replace("M_", "")+c_code;
				outer.append(setMenuBox(code, "", "", "", false));
				setMenuBtnSave(con);
			});
		});
	}

	//우선저장
	function setMenuBtnSave(con){
		$(".btn_m_save").each(function(){
			$(this).off("click");
			$(this).on("click", function(){
				var code = $(this).attr('data-code');
				var list = $("#"+code);
				var input = list.children().children('.info').find('input');
				for( i=0;i<input.size();i++){
					
					if( input.eq(i).val() == "" ){
						input.eq(i).focus();
						alert("입력해주세요");
						return false;
						break;
					}
				}
				input.attr('readonly', 'readonly');
				setBtnSwitch(con, code, true);
			});
		});
	}

	//수정
	function setMenuBtnModfiy(con){
		$(".btn_m_modify").each(function(){
			$(this).off("click");
			$(this).on("click", function(){
				var code = $(this).attr('data-code');
				console.log(code);
				var list = $("#"+code);
				var input = list.children().children('.info').find('input');
				input.removeAttr('readonly');
				setBtnSwitch(con, code, false);
			});
		});
	}

	//버튼바꾸기
	function setBtnSwitch(con, id , state){

		var list = $("#"+id);
		var btn = list.children().children('.btn');
		var m_code = id.replace("li_", "");

		//state 가 true 이면 add/modify/delete
		//state 가 false 이면 save/ cancel

		var output = "";
		if( state ){
			output +=		'<a href="javascript:;" class="">Delete</a>';
			output +=		'<a href="javascript:;" class="btn_m_modify" data-code="L_'+m_code+'">Modify</a>';
			output +=		'<a href="javascript:;" class="btn_m_add" data-code="M_'+m_code+'">Add</a>';
		}else{
			output +=		'<a href="javascript:;" class="btn_m_cancel" data-code="L_'+m_code+'">Cancel</a>';
			output +=		'<a href="javascript:" class="btn_m_save" data-code="L_'+m_code+'">Save</a>';
		}

		btn.empty().append( output );

		if( state ){
			setMenuBtnAdd(con);
			setMenuBtnModfiy(con);
		}else{
			setMenuBtnSave(con);
		}
	}

	//가져오기
	function setMenu(con){
		var con = con.children('ul');
		$.ajax({
			url : '/js/menu.list.json',
			dataType : 'json',
			success : function(data){
				for( var i = 0; i<data.length; i++){
					var p_code = getStringLenFirst( data[i].m_code, data[i].m_code.length-2 ); //부모 코드
					if( data[i].m_code.length == 2){
						con.append(setMenuBox( data[i].m_code , data[i].m_title , data[i].m_link, p_code, true ));
					}
				}
				for( var i = 0; i<data.length; i++){
					var p_code = getStringLenFirst( data[i].m_code, data[i].m_code.length-2 ); //부모 코드
					if( data[i].m_code.length == 4){
						con.find("#M_"+p_code).append(setMenuBox( data[i].m_code , data[i].m_title , data[i].m_link, p_code, true ));
					}
				}
				for( var i = 0; i<data.length; i++){
					var p_code = getStringLenFirst( data[i].m_code, data[i].m_code.length-2 ); //부모 코드
					if( data[i].m_code.length == 6){
						con.find("#M_"+p_code).append(setMenuBox( data[i].m_code , data[i].m_title , data[i].m_link, p_code,true ));
					}
				}
				setMenuBtnAdd(con);
				setMenuBtnModfiy(con);
			}
		});
	}

});