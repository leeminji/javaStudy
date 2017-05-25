/*
	Data 처리 javascript
	comment.js
*/

//수정
function cmtModify(n){
	var f = $("form[name='fcomment"+n+"']");
	var input = f.find('.con_textarea').eq(0);
	var text = f.find('.con_text').eq(0);

	if( input.css('display') == 'none'){
		input.show();
		text.hide();
	}else{
		input.hide();
		text.show();
	}
}

function cmtReply(n){
	var f = $("form[name='fcomment"+n+"']");
	var c = f.find('input[name="count"]').val();

	$.ajax({
		type : "POST",
		url : "comment.reply.html",
		dataType: 'html',  
		data : {
			num : n,
			count : c
		},
		success : function(data){
			if( c==0 ){
				f.parent().append(data);
			}else{
				f.parent().children('ul').prepend(data);
			}
		},
		statusCode: {
			404: function() {
				alert( "page not found" );
			}
		}
	});

}