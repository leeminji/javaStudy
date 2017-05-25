
/* ux.js */

//초기실행
$(document).ready(function(){
	$('input, textarea').placeholder();
	mainMenu($('.nav_main_area'));
	fncSelect($('.input-select'));
	fncCheckBox($(".chk_area"));
	fncBoardList($(".board_list"));
	
	$("#btn_setting").click(function(){
		openBox( $(this).next('ul') );
	});
	
	$(".btn_nav_menu").click(function(e){
		e.preventDefault();
		var header = $("#header");
		if( header.hasClass('on')){
			header.removeClass('on');
		}else{
			header.addClass('on');
		}
	});
	$(".btn_nav_close").click(function(e){
		var header = $("#header");
		header.removeClass('on');
	});
	
	$(".btn_myinfo, .btn_nav_info").click(function(e){
		e.preventDefault();
		var into = $(".info_top_area");
		if( into.css('display') == 'block'){
			into.hide(200);
		}else{
			into.show(200);
		}
	});
});

$(window).load(function(){
	scrollCustom($('.scroll_wrap'));
});
$(window).resize(function(){
	scrollCustom($('.scroll_wrap'));
});

//레이어팝업닫기
function layerClose(){
	var blind = $(".blind");
	var layer = $(".layer_area");
	blind.hide();
	layer.hide().empty();
}

//레이어팝업 열기
function layerOpen(url, data){
	var layer = $(".layer_area");
	var blind = $(".blind");
	var send_data = "";
	if( typeof(data) != "undefined" ){
		send_data = data;
	}
	$.ajax({
		method : "POST",
		url : url,
		data : send_data,
		success : function(data){
			blind.fadeIn(100, function(){
				layer.fadeIn(200).empty();
				layer.append(data);
				$('input, textarea').placeholder();
				setDatePicker();
			});
		}
	});
}

//박스 열기 / 닫기
function openBox(el){
	if( el.css('display') == 'block'){
		el.slideUp();
		el.removeClass('off').addClass('on');
	}else{
		el.slideDown();
		el.removeClass('on').addClass('off');
	}
}

//테이블
function fncBoardList(el){
	$(el).each(function(){
		$(this).find('table').find('td').on({
			"mouseenter" : function(){
				$(this).parent('tr').addClass('on');
			},
			"mouseleave" :function(){
				$(this).parent('tr').removeClass('on');
			}
		});
	});
}

//체크박스 모두 체크
function fncCheckAll(chk , name){
	var chk_list = $("input[name='"+name+"']").parent();
	
	if( chk.checked ){
		chk_list.each(function(){
			$(this).removeClass('on');
			$(this).children('input').removeAttr('checked');
		})
	}else{
		//모두체크
		chk_list.each(function(){
			$(this).addClass('on');
			$(this).children('input').attr('checked', 'checked');
		})
	}
}

//체크박스 디자인
function fncCheckBox(el){
	$(el).each(function(){
		var label = $(this).children('label');
		if(label.children('input').is(":checked")){
			label.addClass('on');
		}
		label.on("click", function(e){
			e.preventDefault();
			if(!$(this).hasClass('on')){
				$(this).addClass('on');
				$(this).children('input').attr('checked','checked');
			}else{
				$(this).removeClass('on');
				$(this).children('input').removeAttr('checked');
			}
		});
	});
}

//셀렉션디자인
function fncSelect(el){
	$(el).each(function(){
		var width = $(this).css('width');
		$(this).wrap('<span class="select_area" style="width:'+width+'"></span>');

		var select = $(this);
		var select_area = $(this).parent(".select_area");
		var selected = select.children('option:selected').text();
		select_area.append(setSelectList(select, selected));
		
		var select_list = getSelectList(select_area);
		select_list.children('a.select_on').click(function(){
			var btn = $(this);
			var list = $(this).next('ul');
			if( list.css('display') == 'none'){
				$(this).next('ul').slideDown(200, function(){
					$(this).children('li').children('a').off().click(function(e){
						e.preventDefault();
						var str_select = $(this).text();
						var str_val = $(this).attr('data-value');
						list.children('li').removeClass('on');
						$(this).parent().addClass('on');
						list.hide(10, function(){
							setSelected(select, str_val);
							btn.text(str_select);
						});
					});
				});
			}else{
				$(this).next('ul').slideUp(200); 
			}
		});
	});
	function setSelected(el, str_val){
		$(el).children('option').each(function(){
			$(this).removeAttr('selected');
			if( $(this).val() == str_val ){
				$(this).attr('selected', 'selected');
			}
		});
	}
	function setSelectList(el , str_select){
		$(el).hide();
		var output = "<div class='select_list'>";
		var str_select = str_select != false ? str_select : $(el).children('option').eq(0).text();

		output +="<a href='javascript:;' class='select_on'>"+str_select+"</a>";
		output += "<ul style='display:none;list-style:none;margin:0;padding:0'>";
		$(el).children('option').each(function(){
			output +="<li><a href='#' data-value='"+$(this).attr('value')+"'>"+$(this).text()+"</a></li>";
		});
		output +="</ul>";
		output +="</div>";

		return output;
	}

	function getSelectList(el){
		return $(el).find('.select_list');
	}
}

//메인메뉴
function mainMenu(el){
	var menu = $(el).children('ul');
	
	menu.children('li').each(function(){
		var depth2 = $(this).children('ul');
		$(this).on({
			"mouseenter" : function(){
				menuOn($(this));
				if( depth2.length > 0){
					depth2.slideDown(300);
				}
			},
			"mouseleave" : function(){
				menuOff($(this));
				if( depth2.length > 0){
					depth2.stop().hide();
				}
			}
		});
	});
	
	function menuOn(el){
		menu.children('li').removeClass('on');
		$(el).addClass('on');
		if( $(el).children('span').length < 1){
			$(el).append("<span></span>");
		}

	}
	function menuOff(el){
		$(el).remove('on');
		if( $(el).children('span').length > 0){
			$(el).children('span').remove();
		}
	}
}

//커스텀 스크롤바
function scrollCustom(el){
	var scroll = $(el);
	scroll.each(function(){
		var inner = $(this).find('.scroll_inner');
		if( inner.children().height() > inner.height()){

			function setScrollTrack(el){
				if( el.find('.scroll_track').length <1 ){
				el.append('<div class="scroll_track"></div>');
				}
				return el.find('.scroll_track');
			}
			function setScrollBar(el){
				if( el.find('.scroll_bar').length <1 ){
					el.append('<div class="scroll_bar"></div>');
				}
				return el.find('.scroll_bar');
			}
			function setScrollBtnUp(el){
				if( el.find('.scroll_btn_up').length <1 ){
					el.append('<div class="scroll_btn_up">▲</div>');
				}
				return el.find('.scroll_btn_up');
			}
			function setScrollBtnDown(el){
				if( el.find('.scroll_btn_down').length <1 ){
					el.append('<div class="scroll_btn_down">▼</div>');
				}
				return el.find('.scroll_btn_down');
			}

			//스크롤 바 높이값
			function getBarHeight(){
				return inner.height()/(inner.prop('scrollHeight')/(inner.height()-(btn_h*2)));
			}

			var track = setScrollTrack($(this));
			var bar = setScrollBar($(this));
			var btn_up = setScrollBtnUp($(this));
			var btn_down = setScrollBtnDown($(this));

			var btn_h = btn_up.height(); //버튼높이값

			var init = function(){
				bar.css({
					'height':getBarHeight(),
					'top' : btn_h,
					'left' : 'initial'
				});
			}

			init();

			inner.scroll(function(e){
				bar.css({
					top: $(this).scrollTop()/(inner.prop('scrollHeight')/(inner.height()-(btn_h*2)))+btn_h+"px"
				});
			});

			bar.draggable({
				axis: "y",
				containment: $(this).find(".scroll_track"),
				drag: function(event, ui){
					inner.scrollTop((ui.position.top-20)*(inner.prop('scrollHeight')/(inner.height()-(btn_h*2))));
				}
			});

			btn_up.bind("click", function(){
				if(inner.scrollTop() > 0){
					var len = 40;
					inner.scrollTop( inner.scrollTop()-len );
				}
			});
			btn_down.bind("click", function(){
				var len = 40;
				inner.scrollTop( inner.scrollTop()+len );
			});
		}
	});
}

//모바일체크
function isCheckMobile(){
	var mobileInfo = new Array('Android', 'iPhone', 'iPod', 'BlackBerry', 'Windows CE', 'SAMSUNG', 'LG', 'MOT', 'SonyEricsson');
	for (var info in mobileInfo){
		if (navigator.userAgent.match(mobileInfo[info]) != null){
			// 모바일 수행
			return true;
		}else{
			return false;
		}
	}
}

/* 레이어팝업 열기 */
function openLayerPop(id){
	var blind = $(".blind");
	var id_con = $("#"+id);

	function adjustLayer(data){
		blind.fadeIn(10 , function(){
			id_con.parent().css({
				display:'block',
				top : getScrollTop()
			});
			id_con.show();
			id_con.append(data);
		});
	}
}

//레이어 팝업 닫기
function closeLayerPop(id){
	var blind = $(".blind");
	var id_con = $("#"+id);
	blind.hide();
	id_con.parent().css('display','none');
	id_con.empty();
}

//스크롤 위치
function getScrollTop(){
	return $(window).scrollTop();
}

//콤마찍기
function comma(str) {
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

//공지사항
function listSlide(el){
	var slider = $(el);
	var viewer = $(el).find('.list_view');
	var ul = viewer.children('ul');
	var list = ul.children('li');
	var list_size = list.size();
	var btn_prev = slider.find('.btn_prev');
	var btn_next = slider.find('.btn_next');
	var timer;
	var init = function(){
		viewer.css({
			position:"relative",
			zIndex:10,
			overflow:'hidden'
		});
		ul.css({
			position:"absolute",
			zIndex:20,
			marginTop : -list.height()
		});
		ul.children('li').eq(-1).prependTo(ul);
	}
	if(list_size > 1){
		init();
		timer = setInterval(toNext,6000);
	}
	btn_prev.on("click",function(e){
		toPrev();
		e.preventDefault();
	});
	btn_next.on("click",function(e){
		toNext();
		e.preventDefault();
	});
	function toPrev(){
		clearInterval(timer);
		ul.not(":animated").animate({
			top:-list.height()
		},300,function(){
			$(this).children('li').eq(0).appendTo(ul);
			$(this).css('top',0);
			timer = setInterval(toNext,6000);
		});
	}
	function toNext(){
		clearInterval(timer);
		ul.not(":animated").animate({
			top:list.height()
		},300,function(){
			$(this).children('li').eq(-1).prependTo(ul);
			$(this).css('top',0);
			timer = setInterval(toNext,6000);
		});
	}
}

//탭
function tabProduct(el, init_num){
	var tab = $(el).find(".tab");
	var tab_list = tab.children('ul').children('li');
	var tab_con = $(el).find('.tab_content');

	var init = function(){
		tab_list.eq(init_num).children('a').addClass('on');
		tab_con.hide();
		tab_con.eq(init_num).show();
	}
	init();
	
	tab_list.each(function(){
		$(this).children('a').click(function(e){
			var href = $(this).attr('href');
			tab_list.find('a').removeClass('on');
			$(this).addClass('on');
			tab_con.hide();
			$(href).show();
			e.preventDefault();
		});
	});
}

//ie8이하 이미지 opacity적용
function ieOpacityImage(){
	if(getInternetExplorerVersion()==8 || getInternetExplorerVersion()==7){
		$('.ie_png').each(function(){
			var src = String($(this).attr('src'));
				if (src.indexOf('.png') > -1 ) {
					$(this).attr('style',"filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='crop',src='" + src + "')");
				}
		});
	}
}

//ie 버전체크
function getInternetExplorerVersion() {
	var rv = -1; // Return value assumes failure.    
	if (navigator.appName == 'Microsoft Internet Explorer') {
		var ua = navigator.userAgent;
		var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		if (re.exec(ua) != null)            
		rv = parseFloat(RegExp.$1);    
	}
	return rv; 
}

//아코디언
function fncArcodian(className, start_num){
	var obj = $('.'+className).children('dl');
	
	if(start_num>=0){
		obj.find('dd').not(obj.find('dd').eq(start_num)).css('display','none'); //오픈
		addOn(start_num); //오픈
	}else{
		obj.find('dd').css('display','none');
	}

	obj.children('dt').each(function(index){
		var index = index;
		$(this).children('a').click(function(){
			if($(this).parent().next('dd').css('display') == 'block'){
				$(this).parent().next('dd').slideUp();
				removeOn();
			}else{
				obj.find('dd').not($(this).parent().next('dd')).slideUp();
				$(this).parent().next('dd').slideDown();
				removeOn();
				addOn(index);
			}
		});
	});
	function addOn(index){
		obj.find('dt').eq(index).addClass('on');
		obj.find('dd').eq(index).addClass('on');
	}
	function removeOn(){
		obj.find('dd').removeClass("on");
		obj.find('dt').removeClass("on");
	}
}

//팝업오픈 openPopup(경로, 넓이, 높이, top, left)
function openPopup(path, width, height, top, left){
	window.open(path, "","width="+width+",height="+height+",top="+top+",left="+left+",noresizable,toolbar=no,status=no,scrollbars=yes,directory=no");
}

//갤러리 정렬 리스트
function fnArrayList(el){
	var gallery = $(el);
	var ul = gallery.children('ul');
	var ul_width = ul.width();
	var list = ul.children('li');
	var list_width = list.width();
	
	//열갯수
	var getCol = function(){
		ul_width = ul.width();
		list = ul.children('li');
		return parseInt(ul_width/list_width);
	}

	var init = function(){
		gallery.addClass('opcacity0');
		ul.css({
			position: 'relative',
			height : maxHeight()+"px",
			'z-index' : 40
		});
		list.css({
			position: "absolute",
			'z-index':45
		});
	}
	if( list.size() > 0){
	var resizeTimer;
	$(window).resize(function(){
		clearTimeout(resizeTimer);
		resizeTimer = setTimeout(
		function(){
			list_width = list.width();
			setListLocation();
		}, 200);
	});

	init();

	setTimeout(function(){
		setListLocation();
	},400);
	}
	function maxHeight(){
		var h_list = new Array();
		for(var i =1; i<=getCol();i++){
			try{
			h_list.push(list.eq(-i).position().top+list.eq(-i).height());
			}catch(err){}
		}
		return Math.max.apply(null, h_list);
		
	}

	//위치셋팅
	function setListLocation(){
		var h_list = setListHeight();
		var col_len = getCol();

		list.each(function(index){
			var i = index;
			var x = index%col_len;
			$(this).animate({
				"left": x*list_width,
				"top":getListHeight(index)
			},50, function(){
				ul.css({
					height : maxHeight()+"px"
				});
				gallery.removeClass('opcacity0');
			});
		});
	}

	//리스트 높이 배열로 얻기
	function setListHeight(){
		var h_list = new Array();
		list.each(function(){
			h_list.push($(this).height()+parseInt($(this).css('margin-bottom')));
		});

		return h_list;
	}

	function getListHeight(index){
		var h_list = setListHeight();
		var sum = 0;
		var col_len = getCol();
		for(var i=0;i<h_list.length ; i++){
			if(index >i && index%col_len == i%col_len){
				sum+=h_list[i]
			}
		}
		return sum;
	}
}

//placeholder 플러그인
/*! http://mths.be/placeholder v2.0.8 by @mathias */
;(function(window, document, $) {

	// Opera Mini v7 doesn?셳 support placeholder although its DOM seems to indicate so
	var isOperaMini = Object.prototype.toString.call(window.operamini) == '[object OperaMini]';
	var isInputSupported = 'placeholder' in document.createElement('input') && !isOperaMini;
	var isTextareaSupported = 'placeholder' in document.createElement('textarea') && !isOperaMini;
	var prototype = $.fn;
	var valHooks = $.valHooks;
	var propHooks = $.propHooks;
	var hooks;
	var placeholder;

	if (isInputSupported && isTextareaSupported) {

		placeholder = prototype.placeholder = function() {
			return this;
		};

		placeholder.input = placeholder.textarea = true;

	} else {

		placeholder = prototype.placeholder = function() {
			var $this = this;
			$this
				.filter((isInputSupported ? 'textarea' : ':input') + '[placeholder]')
				.not('.placeholder')
				.bind({
					'focus.placeholder': clearPlaceholder,
					'blur.placeholder': setPlaceholder
				})
				.data('placeholder-enabled', true)
				.trigger('blur.placeholder');
			return $this;
		};

		placeholder.input = isInputSupported;
		placeholder.textarea = isTextareaSupported;

		hooks = {
			'get': function(element) {
				var $element = $(element);

				var $passwordInput = $element.data('placeholder-password');
				if ($passwordInput) {
					return $passwordInput[0].value;
				}
				return $element.data('placeholder-enabled') && $element.hasClass('placeholder') ? '' : element.value;
			},
			'set': function(element, value) {
				var $element = $(element);

				var $passwordInput = $element.data('placeholder-password');
				if ($passwordInput) {
					return $passwordInput[0].value = value;
				}

				if (!$element.data('placeholder-enabled')) {
					return element.value = value;
				}
				if (value == '') {
					element.value = value;
					// Issue #56: Setting the placeholder causes problems if the element continues to have focus.
					if (element != safeActiveElement()) {
						// We can't use `triggerHandler` here because of dummy text/password inputs :(
						setPlaceholder.call(element);
					}
				} else if ($element.hasClass('placeholder')) {
					clearPlaceholder.call(element, true, value) || (element.value = value);
				} else {
					element.value = value;
				}
				// `set` can not return `undefined`; see http://jsapi.info/jquery/1.7.1/val#L2363
				return $element;
			}
		};

		if (!isInputSupported) {
			valHooks.input = hooks;
			propHooks.value = hooks;
		}
		if (!isTextareaSupported) {
			valHooks.textarea = hooks;
			propHooks.value = hooks;
		}

		$(function() {
			// Look for forms
			$(document).delegate('form', 'submit.placeholder', function() {
				// Clear the placeholder values so they don't get submitted
				var $inputs = $('.placeholder', this).each(clearPlaceholder);
				setTimeout(function() {
					$inputs.each(setPlaceholder);
				}, 10);
			});
		});

		// Clear placeholder values upon page reload
		$(window).bind('beforeunload.placeholder', function() {
			$('.placeholder').each(function() {
				this.value = '';
			});
		});

	}

	function args(elem) {
		// Return an object of element attributes
		var newAttrs = {};
		var rinlinejQuery = /^jQuery\d+$/;
		$.each(elem.attributes, function(i, attr) {
			if (attr.specified && !rinlinejQuery.test(attr.name)) {
				newAttrs[attr.name] = attr.value;
			}
		});
		return newAttrs;
	}

	function clearPlaceholder(event, value) {
		var input = this;
		var $input = $(input);
		if (input.value == $input.attr('placeholder') && $input.hasClass('placeholder')) {
			if ($input.data('placeholder-password')) {
				$input = $input.hide().next().show().attr('id', $input.removeAttr('id').data('placeholder-id'));
				// If `clearPlaceholder` was called from `$.valHooks.input.set`
				if (event === true) {
					return $input[0].value = value;
				}
				$input.focus();
			} else {
				input.value = '';
				$input.removeClass('placeholder');
				input == safeActiveElement() && input.select();
			}
		}
	}

	function setPlaceholder() {
		var $replacement;
		var input = this;
		var $input = $(input);
		var id = this.id;
		if (input.value == '') {
			if (input.type == 'password') {
				if (!$input.data('placeholder-textinput')) {
					try {
						$replacement = $input.clone().attr({ 'type': 'text' });
					} catch(e) {
						$replacement = $('<input>').attr($.extend(args(this), { 'type': 'text' }));
					}
					$replacement
						.removeAttr('name')
						.data({
							'placeholder-password': $input,
							'placeholder-id': id
						})
						.bind('focus.placeholder', clearPlaceholder);
					$input
						.data({
							'placeholder-textinput': $replacement,
							'placeholder-id': id
						})
						.before($replacement);
				}
				$input = $input.removeAttr('id').hide().prev().attr('id', id).show();
				// Note: `$input[0] != input` now!
			}
			$input.addClass('placeholder');
			$input[0].value = $input.attr('placeholder');
		} else {
			$input.removeClass('placeholder');
		}
	}

	function safeActiveElement() {
		// Avoid IE9 `document.activeElement` of death
		// https://github.com/mathiasbynens/jquery-placeholder/pull/99
		try {
			return document.activeElement;
		} catch (exception) {}
	}

}(this, document, jQuery));

