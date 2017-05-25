<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/admin/common/common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<!--[if IE 7]><html lang="ko" class="ie7"><![endif]-->
	<!--[if IE 8]><html lang="ko" class="ie8"><![endif]-->
	<title>관리자페이지</title>
	<link rel="stylesheet" type="text/css" href="${CSS_PATH }/admin.css?v=<%=System.currentTimeMillis() %>" />
	<script type="text/javascript" src="${JS_PATH }/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${JS_PATH }/jquery-ui.js"></script>
	<!--[if lte IE 8]>
	<script type="text/javascript" src="${JS_PATH }/html5.js"></script>
	<![endif]-->
	<script type="text/javascript" src="${JS_PATH }/TimelineMax.min.js"></script>
	<script type="text/javascript" src="${JS_PATH }/TweenMax.min.js"></script>
	<script type="text/javascript" src="${JS_PATH }/raphael.js"></script>
	<script type="text/javascript" src="${JS_PATH }/common.js?v=<%=System.currentTimeMillis() %>"></script>
	<script type="text/javascript" src="${JS_PATH }/ux.js?v=<%=System.currentTimeMillis() %>"></script>
</head>
<body>
