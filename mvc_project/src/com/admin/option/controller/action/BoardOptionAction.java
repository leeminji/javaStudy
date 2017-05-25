package com.admin.option.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardOptionAction {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
