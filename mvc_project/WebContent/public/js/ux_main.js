/* 
ux_main.js 
*/
var t_clock = null;
var t_visual = null;

//메인페이지 실행
$(document).ready(function(){
	//세계시간
	setCurrentTime("SEOUL");
	//메인 비쥬얼
	visualSlider(0, 2000);
	//리스트 슬라이드
	listSlider(0);
	//날씨
	getWeather(0);
});

//퀵메뉴
function fncQuick(){
	var quick = $("#quick_area");
	if( !quick.hasClass('off') ){
		quick.addClass('off', 300);
	}else{
		quick.removeClass('off', 300);
	}
}

//리스트 슬라이드
function listSlider(start_num){
	var current_num = start_num;
	var slider = $(".slider_list");
	var slider_nav = $(".slider_nav");
	var viewer = slider.find('.viewer');
	var v_width = viewer.width();
	var ul = viewer.children('ul');
	var li = ul.children('li');
	var li_size = li.size();
	var total_width = li_size * li.eq(0).width();
	var total_num = Math.floor(total_width/v_width);

	var init = function(){
		if( total_width > v_width ){
			slider_nav.append( 
				'<a href="javascript:;" class="btn_client_next">다음</a>'+
				'<a href="javascript:;" class="btn_client_prev">이전</a>'
			);
		}
	}
	init();

	$(".btn_client_next").click(function(){
		current_num = current_num+1 > total_num ? 0 : current_num+1;
		setSlider(current_num);
	});
	$(".btn_client_prev").click(function(){
		current_num = current_num-1 < 0 ? total_num : current_num-1;
		setSlider(current_num);
	});

	function setSlider(num){
		ul.animate({left: -(num*v_width)}, 1000);
	}
}


//메인 비쥬얼
function visualSlider(startNum, speed){
	var c_num = startNum;
	var v_img = $(".visual_img");
	var v_text = $(".visual_text");
	var v_page = $(".pagging");
	var v_size = v_img.size()-1;

	var init = function(){
		v_img.eq(startNum).show();
		v_text.eq(startNum).show().css({"left": 0});

		for( var i=0;i<=v_size;i++){
			v_img.eq(i).css("z-index", (v_size-i)*10+10);
			var is_chk = i == startNum ? "class='on'" : "";
			v_page.append("<a href='javascript:;' "+is_chk+" >"+(i+1)+"</a>");
		}
		v_page.children('a').each(function(index){
			$(this).click(function(){
				v_stop();
				c_num = index;
				setVisual(c_num, true);
			});
		});
	}

	function setPaging(num){
		v_page.children('a').removeClass('on');
		v_page.children('a').eq(num).addClass('on');
	}

	init();
	v_start();

	function v_start(){
		t_visual = setInterval(function(){
			c_num++;
			c_num = c_num > v_size ? 0 : c_num;
			setVisual(c_num, false);
		}, 5000);
	}

	function v_stop(){
		clearInterval(t_visual);
	}

	function setVisual(num, state){
		v_stop();
		var n_num = num+1 <= v_size ? num+1 : 0;
		var p_num = num-1 < 0 ? v_size : num-1;

		//console.log("p_num:"+p_num +"/num : "+num+"/n_num: "+n_num);
		setPaging(num);
		
		if( !state ){
			v_img.css('z-index', 1);
			//클릭하지 않은 경우 : 자동동작
			v_img.eq(p_num).css({display:'block', opacity: 1, 'z-index' : 30}).animate({opacity : 0}, speed);
			v_img.eq(num).css({ display:'block', opacity: 0, 'z-index' : 20}).animate({opacity: 1}, speed, "linear");
			v_img.eq(n_num).css({'z-index' : 10, display:'block'});
		}else{
			v_img.css('z-index', 1);
			//클릭하여 움직이는 경우 
			v_img.eq(p_num).css({'z-index' : 30, opacity: 0, display: 'none'});
			v_img.eq(n_num).css({'z-index' : 10, display:'block'});
			v_img.eq(num).css({ display:'block', opacity: 1, 'z-index':20});
		}
		
		v_text.attr('style', '').hide();
		v_text.eq(num).show().animate({"left" : 0, "opacity" : 1}, 600, "swing", function(){
			v_start();
		});
	}
}



//전체메뉴
function fncTotalMenu(){
	var con = $(".main_gnb_menu");
	if( con.css('display') == 'none'){
		con.show();
	}else{
		con.hide();
	}
}

//자리수설정
function formatZero(num, len) {
	while(num.toString().length < len) {
	num = "0" + num;
	}
	return num;
}

//세계시간
function openClockList(el){
	var s = null;
	var list = $(".clock_world_list");
	if( list.css('display') == 'block'){
		list.hide();
	}else{
		list.show(10, function(){
			list.children('li').each(function(){
				$(this).children('a').click(function(){
					list.find('a').removeClass('on');
					$(this).addClass('on');
					clearInterval(t_clock);
					var t = $(this).text();
					s = setCurrentTime(t);
					list.hide();
					$(el).text(t);
				});
			});
		});
	}
}
//세계 시간 구하기 Timezone 으로 계산
function getWorldTime(tzOffset) { 
	var now = new Date();
	var tz = now.getTime() + (now.getTimezoneOffset() * 60000) + (tzOffset * 3600000);
	now.setTime(tz);
	var am_pm = now.getHours() > 12 ? "PM" : "AM";
	var s = '<span class="pm_am">'+am_pm+'</span>';
	s += '<span class="time">'+formatZero(now.getHours(), 2) + ':' + formatZero(now.getMinutes(), 2)+"</span>";
	return s;
}

// 1초마다 돌리기
function setCurrentTime(nation){
	t_clock = setInterval( function(){
		setClock(nation);
	}, 1000);

	function setClock(nation){
		var time_area = $("#time_area");
		if( nation == "SEOUL" ){
			time_area.html(getWorldTime(+9));
		}else if( nation == "NEWYORK"){
			time_area.html(getWorldTime(-5));
		}else if( nation == "TOKYO"){
			time_area.html(getWorldTime(+9));
		}else if( nation == "WASINGTON"){
			time_area.html(getWorldTime(-5));
		}else if( nation == "LONDON"){
			time_area.html(getWorldTime(0));
		}else if( nation == "PARIS"){
			time_area.html(getWorldTime(+1));
		}
	}
}

//세계 날씨
function getWeather(startNum){
	var select = startNum;
	var nation_arr = ["Seoul", "Tokyo", "Washington", "London", "Paris"];
	var nation_id = ["1835848", "1850147", "5815135", "2643743", "2988507"];

	function setWorldList(){
		var output = "";
		for( var i=0;i<nation_arr.length;i++){
			var is_on = "";
			if( i == select ) is_on = "class='on'";
			output +='<li '+is_on+'><a href="javascript:;">'+nation_arr[i]+'</a></li>';
		}
		return output;
	}

	function setWeatherData(main, weather){
		var output ="";
		output += '<div class="state">';
		output += '<i class="icon_weather"><img src="http://openweathermap.org/img/w/'+weather[0].icon+'.png"></i>';
		output += '<span class="weather_text">'+weather[0].main+'</span>';
		output += '</div>';
		output += '<div class="temperae">'+(parseFloat(main.temp)-273.15).toFixed(1)+'<span>º</span></div>';
		output += '<div class="nation">'+nation_arr[select]+'</div>';
		return output;
	}

	$.ajax({
		method : "GET",
		dataType: "json",
		url : "http://api.openweathermap.org/data/2.5/weather",
		data: { 
			"id": nation_id[select],
			"appid": "0278249ec95aa8b185e3a073afbc3ada"
		}
	}).done(function(data){
		var list = $("#world_list");
		list.html("").append(setWorldList());
		$("#weather").html("").append(setWeatherData(data.main, data.weather));
		list.eq(select).addClass('on');
		list.children('li').each(function(index){
			$(this).click(function(){
				getWeather(index);
			})
		});
	});
}
