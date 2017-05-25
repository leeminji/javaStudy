<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/admin/layout/header.jsp"></c:import>

<form action="boardCheckPass.do" method="post">

<input type="hidden" name="bo_idx" value="${param.bo_idx }" />
<input type="hidden" name="bo_table" value="${param.bo_table }" />
<input type="hidden" name="command" value="${param.command }" />

<c:choose>
	<c:when test="${param.command =='update'}">
	<h2>수정</h2>
	</c:when>
	<c:otherwise>
	<h2>삭제</h2>
	</c:otherwise>
</c:choose>

<ul>
	<li>비밀번호 <input type="text" name="bo_pass" />${message }</li>
</ul>

<c:choose>
<c:when test="${param.command =='update'}">
<input type="submit" value="수정" />
</c:when>
<c:otherwise>
<input type="submit" value="삭제" />
</c:otherwise>
</c:choose>

</form>

<c:import url="/admin/layout/footer.jsp"></c:import>