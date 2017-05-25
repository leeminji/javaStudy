package com.util;

import java.io.UnsupportedEncodingException;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticatior extends Authenticator {
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		String id = "leeminji25";
		String pass = "alswl85917988";
		try {
			id = new String(id.getBytes("utf-8"), "utf-8");
			pass = new String(pass.getBytes("utf-8"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new PasswordAuthentication(id , pass);
	}

}
