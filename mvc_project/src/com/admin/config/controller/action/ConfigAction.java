package com.admin.config.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ConfigAction {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
