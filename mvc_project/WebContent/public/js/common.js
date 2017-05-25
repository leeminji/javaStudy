/*
	Data 처리 javascript
	common.js
*/

//ajax 로딩
$(document).ajaxStart(function(){
	$(".loading").css("display", "block");
});

$(document).ajaxComplete(function(){
	$(".loading").css("display", "none");
});	

$(function(){
	setDatePicker();
});


//정규식
function chkRegEpx(str, value){
	var regexp = "/"+str+"/g";
	regexp.trim();
	console.log(regexp+"/"+value+"/" +regexp.test(value) );
	return ;
}

//datepicker 설정
function setDatePicker(){
	$( ".datepicker" ).datepicker({
		dateFormat : 'yy-mm-dd',
		showOn: "button",
		buttonImage: "/mvc_project/public/images/icon_calendal.png",
		buttonImageOnly: true,
		buttonText: "Select date",
		changeMonth: true,
		changeYear: true,
		monthNamesShort : [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		dayNamesShort: [ "일", "월", "화", "수", "목", "금", "토" ],
		gotoCurrent : true,
		showButtonPanel: true
	});
}

//파일추가
function addFile(file_max_count){
	var file_list = $("#file_list");
	var file_count = $("#file_count");
	var count = file_count.val();
	var output = "";
	count++;
	if( file_max_count >= count ){
		output += '<li><span class="num">'+count+'.</span>';
		output += '<input type="file" name="file'+count+'" value="File Search" onChange="fn_previewImg(this, \'preImg'+count+'\')" />';
		output +='<span class="prevImg"><img id="preImg'+count+'" /></span></li>';
		file_count.val(count);
		file_list.append(output);
	}else{
		alert("더이상 추가할 수 없습니다.");
	}
}

function fn_previewImg(input, preImg ) {
   // param : input - 파일업로드 input 객체 change 이벤트에서 this 로 받아온다
   //           preImg - 미리보기 이미지를 보여줄 img 태그  ID 
	if ($(input).val()!="") {
		//확장자 확인
		var ext = $(input).val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['gif','png','jpg','jpeg']) != -1) {
			if ( window.FileReader ) {
				 /*IE 9 이상에서는 FileReader  이용*/
				var reader = new FileReader();
					reader.onload = function (e) {
						$('#'+preImg).attr('src', e.target.result); 
					};
					reader.readAsDataURL(input.files[0]);
					return input.files[0].name;  // 파일명 return
			} else {
				/* IE8 전용 이미지 미리보기 */ 
				input.select();
				var src = document.selection.createRange().text;
				$('#'+preImg).attr('src', src);  
				var n = src.substring(src.lastIndexOf('\\') + 1);
				return n; // 파일명 return
			}
			return;
		}
	}
}