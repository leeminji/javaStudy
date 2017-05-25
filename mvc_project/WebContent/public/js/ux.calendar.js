/*
	ux.calendar.js
*/

var calendar_items = [];
var now = new Date();
var n_month = now.getMonth();
var n_year = now.getFullYear();
var n_date = now.getDate();

$(document).ready(function(){
	loadSchedule();
});

function Calendar(c_no, c_date, c_title, c_content){
	this.c_no = c_no;
	this.c_date = c_date;
	this.c_title = c_title;
	this.c_content = c_content;
}

function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}


//스케쥴추가
function setAddShedule(){
	layerOpen('calendar.popup.html');
}

//스케쥴보기
function viewCalendar(no){
	layerOpen('calendar.popup.html', calendar_items[no]);
}

//달력 년, 월 설정
function setCalendarInfo(year, month){
	var prev_month;
	var next_month;
	var prev_year = year;
	var next_year= year;

	if(  month-1 <= -1 ){
		prev_month = 11;
		prev_year--;
	}else{
		prev_month = month-1;
	}

	if( month+1 >= 11 ){
		next_month = 0;
		next_year++;
	}else{
		next_month = month+1;
	}

	var output = "";
	output +='<a href="javascript:loadSchedule('+prev_year+','+prev_month+');" class="prev_month icon_prev">이전</a>';
	output +='<div class="info_year">';
		output += '<select name="year" id="select_year" class="input-select" size="7">';
		for( var i=-1; i<3; i++){
			var is_select = "";
			if( year == year+i ){
				is_select = "selected='selected'";
			}
			output +='<option value="'+(year+i)+'" '+is_select+'>'+(year+i)+'년</option>';
		}
		output +='</select>';
	output +='</div>';
	output +='<span class="bg_line"></span>';
	output +='<div class="info_month">';
		output +='<div class="current_month">';
		output +='<select name="month" id="select_month" class="input-select" size="5">';
		for( var i=0; i<12; i++){
			var is_select = "";
			if( month == i ){
				is_select = "selected='selected'";
			}
			output +='<option value="'+(i+1)+'" '+ is_select +'>'+(i+1)+'월</option>';
		}
		output +='</select>';
		output +='</div>';
	output +='</div>';
	output +='<a href="javascript:;" class="btn_calendar_search btn02" id="btn_calendar_go">GO</a>';
	output +='<a href="javascript:loadSchedule('+next_year+','+next_month+');" class="next_month icon_next">다음</a>';
	output +='';
	output +='';
	output +='';
	return output;
}

function setCalendarDate(month, date){
	var today = n_year+"-"+pad((n_month+1),2)+"-"+pad((n_date),2); //오늘날짜
	var current_day = n_year+'-'+pad((month+1),2)+'-'+pad(date,2); //각셀의 날자
	var is_today = "";
	if( today == current_day ){
		is_today = "today";
	}
	var output = "";
	output +='<div class="col_day '+is_today+'" data-date="'+current_day+'">';
	output +='<div class="inner">';
	output +='<div class="date">'+date+'</div>';
	output +='<div class="con"><ul class="clear schedule_list"></ul></div>';
	output +='</div>';
	output +='</div>';

	return output;
}

function setCalendar(year , month){
	//new Date(year, month, day, hours, minutes, seconds, milliseconds);
	var firstDay = (new Date( year, month, 1)).getDay(); //시작 요일
	var lastDate = ( new Date( year, month+1, 0) ).getDate(); //마지막 날짜

	var output = "";
	var d = 1; //날짜
	var week_len = firstDay > 5 ? 6 : 5; //몇주까지 있는지 판단

	for( var week=1; week<=week_len;week++){
		output += '<div class="row_week">';
		for( var j=0; j<7; j++){
			if( week == 1 ){
				if( firstDay <= j  ){
					output += setCalendarDate(month, d);
					d++;
				}else{
					output += setCalendarDate(month, "");
				}
			}else{
				if( d <= lastDate ){
					output += setCalendarDate(month, d);
					d++;
				}else{
					output += setCalendarDate(month, "");
				}
			}
		}
		output += '</div>';
	}
	return output;
}

function loadSchedule(year, month){
	calendar_items = [];
	var calendar_day_area = $("#calendar_day_area");
	var calendar_info = $("#calendar_info");
	var now = new Date().getTime();
	if( typeof(month) == "undefined" ){
		month = n_month;
	}
	if( typeof(year) == "undefined" ){
		year = n_year;
	}
	$.ajax({
		url : "/mvc_project/admin/setting/calendar.list.json?v="+now,
		dataType : "json",
		beforeSend : function(){
			calendar_day_area.append("<div class='loader'></div>");
		},
		success : function(data){
			//제거 후 삽입
			calendar_day_area.empty();
			calendar_info.empty();
			console.log(data);
			for( key in data.schedule ){	
				calendar_items.push( new Calendar(
					data.schedule[key]["no"],
					data.schedule[key]["date"],
					data.schedule[key]["title"], 
					data.schedule[key]["content"]) 
				);
			}

			calendar_day_area.append(setCalendar(year, month)); //date 설정
			calendar_info.append( setCalendarInfo(year, month) ); //정보(년, 월)
			fncSelect($('.input-select'));
			
			for( var i =0; i< calendar_items.length; i++){
				var col_date = calendar_day_area.find("[data-date='"+calendar_items[i].c_date+"']" );
				col_date.find('.schedule_list').append("<li><a title='"+calendar_items[i].c_title+"' class='ellipsis' href='javascript:viewCalendar("+i+")'>"+calendar_items[i].c_title+"</a></li>");
			}
			$("#btn_calendar_go").click(function(){
				loadSchedule( parseInt($("#select_year").val()), parseInt($("#select_month").val())-1 );
			});
		}
	});
}