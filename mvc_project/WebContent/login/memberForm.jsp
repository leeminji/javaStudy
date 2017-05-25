<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/header.jsp"></c:import>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- content -->
<section class="content">
	<div class="content_area about_area clearfix">
		<div class="section">
			<c:choose>
			<c:when test="${!empty ss_mb_id }">
			<h2 class="title01">회원정보수정</h2>
			<form action="member_update_ok" method="post" onSubmit="return frmUpdateSubmit(this);">
				<input type="hidden" value="1" name="mb_id_chk" />
			</c:when>
			<c:otherwise>
			<h2 class="title01">회원가입</h2>
			<form action="join_ok" method="post" onSubmit="return frmJoinSubmit(this);">
				<input type="hidden" value="0" name="mb_id_chk" />
			</c:otherwise>
			</c:choose>
				<table class="tbl_join">
					<colgroup>
						<col class="col1" />
						<col class="col2" />
					</colgroup>
					<tr>
						<th>이름</th>
						<c:choose>
							<c:when test="${!empty ss_mb_id }">
								<td><input type="hidden" name="mb_name"
									value="${mVo.mb_name }" /> ${mVo.mb_name }</td>
							</c:when>
							<c:otherwise>
								<td><input type="text" name="mb_name" value="${mVo.mb_name }" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th>아이디</th>
						<c:choose>
							<c:when test="${!empty ss_mb_id }">
								<td><input type="hidden" name="mb_id" size="80"
									value="${mVo.mb_id }" /> ${mVo.mb_id }</td>
							</c:when>
							<c:otherwise>
								<td><input type="text" name="mb_id" size="80" value="${mVo.mb_id }" /> <a
									href="javascript:;" onclick="javascript:chkIsId();"
									class="btn01">아이디중복확인</a></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="mb_pass" size="100" /></td>
					</tr>
					<tr>
						<th>비밀번호 재입력</th>
						<td><input type="password" name="mb_pass_re" size="100" /></td>
					</tr>								
					<tr>
						<th>이메일</th>
						<td>
						<input type="text" name="mb_email" value="${mVo.mb_email }" size="100" placeholder="이메일을 정확하게 입력해주세요." />&nbsp; 
						
						<span class="chk_area <c:if test="${mVo.mb_mailing  == 1}"> on </c:if> ">
						<label for="mb_mailing"><input type="checkbox" id="mb_mailing" <c:if test="${mVo.mb_mailing == 1}"> checked=checked </c:if> name="mb_mailing" value="1" />
						</label> 메일링여부</span>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<div class="addr_area">
								우편번호 &nbsp;<input type="text" name="mb_zip"
									placeholder="000-000" size="10" value="${mVo.mb_zip }" />
								<a href="javascript:;" onclick="getAddress()" class="btn01">우편번호검색</a>
							</div>
							<div class="addr_area">
								<input type="text" name="mb_addr1" placeholder="주소" size="200"
									value="${mVo.mb_addr1 }" />
							</div>
							<div class="addr_area">
								<input type="text" name="mb_addr2" placeholder="상세정보" size="200"
									value="${mVo.mb_addr2 }" />
							</div>
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td><input type="text" name="mb_tel"
							value="${mVo.mb_tel }" placeholder="숫자만 입력해주세요." />
						</td>
					</tr>
					<tr>
						<th>핸드폰번호</th>
						<td><input type="text" name="mb_hp" value="${mVo.mb_hp }"
							placeholder="숫자만 입력해주세요." /></td>
					</tr>
				</table>
				<p>${message }</p>
				<div class="btn_area_right">
					<c:choose>
						<c:when test="${!empty ss_mb_id }">
							<input type="submit" value="회원정보수정" class="btn01" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="회원가입" class="btn01" />
						</c:otherwise>
					</c:choose>
					<a href="${PATH}/pf/index.do" class="btn02">취소</a>
				</div>
			</form>
		</div>
	</div>
</section>
<script type="text/javascript">
	function frmUpdateSubmit(frm){
		var f = frm;
		
		if( f.mb_id.value == '' ){
			alert("이름을 입력하세요");
			return false;
		}
		
		if( f.mb_pass.value == '' ){
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		if( f.mb_pass_re.value == '' ){
			alert("비밀번호를 재입력하세요.");
			return false;
		}
		
		if( f.mb_pass.value != f.mb_pass_re.value ){
			alert("비밀번호가 동일하지 않습니다.");
			return false;
		}
	
		if( f.mb_email.value == '' ){
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		var regExg=/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		if( ! regExg.test(f.mb_email.value) ){
			alert("올바른 이메일 형식이 아닙니다.");
			return false;
		}
		return true;
	}
	
	function frmJoinSubmit(frm){
		var f = frm;
		
		if( f.mb_id.value == '' ){
			alert("이름을 입력하세요");
			return false;
		}
		
		if( f.mb_pass.value == '' ){
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		if( f.mb_pass_re.value == '' ){
			alert("비밀번호를 재입력하세요.");
			return false;
		}
		
		if( f.mb_pass.value != f.mb_pass_re.value ){
			alert("비밀번호가 동일하지 않습니다.");
			return false;
		}

		if( f.mb_email.value == '' ){
			alert("이메일을 입력해주세요.");
			return false;
		}
		
		var regExg=/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		if( ! regExg.test(f.mb_email.value) ){
			alert("올바른 이메일 형식이 아닙니다.");
			return false;
		}
		
		if( f.mb_id_chk.value == 0 ){
			alert("아이디 중복확인을 해주세요.");
			return false;
		}
		
		return true;
	}
	
	function getAddress(){
		//다음 api 주소겁색 사용
		var mb_zip = $("input[name='mb_zip']");
		var mb_addr = $("input[name='mb_addr1']");
		daum.postcode.load(function(){
			new daum.Postcode({
			    oncomplete: function(data) {
					console.log(data);
					mb_zip.val( data.postcode );
					mb_addr.val( data.address );
			    }
			}).open();
		});
	}
	
	function chkIsId() {
		var mb_id = $("input[name='mb_id']");
		var mb_id_chk = $("input[name='mb_id_chk']");
		mb_id_chk.val(0);

		if (mb_id.val() == '') {
			alert("아이디를 입력하세요");
			mb_id_chk.val(0);
			mb_id.focus();
			return;
		}
		
		var regExp = /^[a-z]+[a-z0-9]{5,19}$/g;
		if( !regExp.test(mb_id.val()) ){
			alert("아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.");
			mb_id_chk.val(0);
			mb_id.focus();			
			return;
		}
	
		$.ajax({
			type : "get",
			url : 'member.do?command=member_id_chk',
			data : {
				'mb_id' : mb_id.val()
			}
		}).done(function(data) {
			var msg = mb_id + "는";
			if (data.trim() == "true") {
				msg = " 이미존재하는 아이디 입니다. 다른 아이디를 입력해주세요.";
				mb_id.val('').focus();
				mb_id_chk.val(0);
			} else {
				mb_id_chk.val(1);
				msg = " 사용하능한 아이디입니다.";
			}
			alert(msg);
		});

	}
</script>
<c:import url="../layout/footer.jsp"></c:import>