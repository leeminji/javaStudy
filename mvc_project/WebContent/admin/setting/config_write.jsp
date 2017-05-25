<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/admin/layout/header.jsp"></c:import>
<section class="content" id="content">
	<div class="title_text">
		<h2 class="title01">Setting</h2>
		<p>홈페이지 기본환경을 설정합니다.</p>
	</div>
	<form action="writeOk.do" name="frm" method="post">
	<div class="board_write_area">
		<!-- //board_write -->
		<div class="board_write">
			<h3 class="title03">기본설정</h3>
			<table>
				<colgroup>
					<col class="col_subject" />
					<col class="col_content" />
				</colgroup>		
				<tbody>
				<tr>
					<th scope="row"><label for="conf_admin" class="chk_must">대시보드스킨</label></th>
					<td>
						<select name="conf_admin" id="conf_admin" class="input-select" style="width:120px">
							<option value="dark">dark</option>
							<option value="white">white</option>
						</select>
					</td>
				</tr>				
				<tr>
					<th scope="row"><label for="cf_title">프로젝트 이름</label></th>
					<td><input type="text" value="${cVo.cf_title }" name="cf_title" id="cf_title" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="cf_admin">최종관리자 아이디</label></th>
					<td><input type="text" value="${cVo.cf_admin }" name="cf_admin" id="cf_admin" /></td>
				</tr>
				<tr>
					<th scope="row"><label for="cf_admin_email">최종관리자 이메일</label></th>
					<td>
					<p class="txt_info">관리자가 보내고 받는 용도로 사용하는 메일 주소를 입력합니다. (회원가입, 인증메일, 테스트, 회원메일발송 등에서 사용)</p>
					<p><input type="text" size="500" value="${cVo.cf_admin_email }" id="cf_admin_email" name="cf_admin_email" /></p>
					</td>
				</tr>	
				<tr>
					<th scope="row"><label for="cf_admin_name">최종관리자 이름</label></th>
					<td>
					<p class="txt_info">관리자가 보내고 받는 용도로 사용하는 메일의 발송이름을 입력합니다. (회원가입, 인증메일, 테스트, 회원메일발송 등에서 사용)</p>
					<p><input type="text" value="${cVo.cf_admin_name }" id="cf_admin_name" name="cf_admin_name" /></p>
					</td>
				</tr>	
				<tr>
					<th scope="row"><label for="cf_addr">프로젝트 주소</label></th>
					<td><input type="text" value="${cVo.cf_addr }" name="cf_addr" id="cf_addr" size="500" /></td>
				</tr>	
				<tr>
					<th scope="row"><label for="cf_tel">전화</label></th>
					<td><input type="text" value="${cVo.cf_tel }" name="cf_tel" id="cf_tel" /></td>
				</tr>	
				<tr>
					<th scope="row"><label for="cf_info1">정보1</label></th>
					<td><input type="text" value="${cVo.cf_info1 }" name="cf_info1" id="cf_info1" size="500" /></td>
				</tr>	
				<tr>
					<th scope="row"><label for="cf_info2">정보2</label></th>
					<td><input type="text" value="${cVo.cf_info2 }" name="cf_info2" id="cf_info2" size="500" /></td>
				</tr>	
				<tr>
					<th scope="row"><label for="cf_info3">정보3</label></th>
					<td><input type="text" value="${cVo.cf_info3 }" name="cf_info3" id="cf_info3" size="500" /></td>
				</tr>
				</tbody>
			</table>
		</div>
		<!-- //board_write -->
		<!-- board_write -->
		<div class="board_write">
			<h3 class="title03">회원가입 설정 <span>회원가입 시 사용할 스킨과 입력 받을 정보 등을 설정할 수 있습니다.</span></h3>
			<table>
				<colgroup>
					<col class="col_subject" />
					<col class="col_content" />
				</colgroup>				
				<tbody>
				<tr>
					<th scope="row">주소</th>
					<td>
					<span class="chk_area"><label><input type="checkbox" value="1" name="cf_use_addr" <c:if test="${ cVo.cf_use_addr == 1 }">checked</c:if> /></label></span>
					</td>
				</tr>	
				<tr>
					<th scope="row">전화</th>
					<td><span class="chk_area"><label><input type="checkbox" value="1" name="cf_use_tel" <c:if test="${ cVo.cf_use_tel == 1 }">checked</c:if> /></label></span></td>
				</tr>	
				<tr>
					<th scope="row">핸드폰</th>
					<td><span class="chk_area"><label><input type="checkbox" value="1" name="cf_use_hp" <c:if test="${ cVo.cf_use_hp == 1 }">checked</c:if> /></label></span></td>
				</tr>
				<tr>
					<th scope="row"><label for="cf_privacy">개인정보취급방침</label></th>
					<td><textarea name="cf_privacy" id="cf_privacy" cols="30" rows="5">${cVo.cf_privacy }</textarea></td>
				</tr>
				<tr>
					<th scope="row"><label for="cf_service">이용약관</label></th>
					<td><textarea name="cf_service" id="cf_service" cols="30" rows="5">${cVo.cf_service }</textarea></td>
				</tr>				
				</tbody>
			</table>
		</div>
		<!-- board_write -->
		<!-- //board_write_bottom -->
		<div class="clearfix board_write_bottom">
			<div class="button_area_right">
				<input type="submit" class="btn02" value="Save" />
			</div>
		</div>
		<!-- //board_write_bottom -->		
	</div>
	<div>
		
	</div>
</form>
</section>
<c:import url="/admin/layout/footer.jsp"></c:import>