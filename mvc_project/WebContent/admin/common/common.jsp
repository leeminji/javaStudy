<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");

	//경로설정
	String path = request.getContextPath();
	application.setAttribute("PATH", path );
	application.setAttribute("JS_PATH", path +"/public/js");
	application.setAttribute("CSS_PATH", path +"/public/css");
	application.setAttribute("PLUGIN_PATH", path +"/public/plugin");
	application.setAttribute("IMG_PATH", path +"/public/images");
	
%>
