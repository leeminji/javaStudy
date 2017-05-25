package com.admin.mail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.SMTPAuthenticatior;

import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class MailSendServlet
 */
@WebServlet("/contact/mailSend")
public class MailSendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailSendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/contact/mailForm.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		String sender = new String ("leeminji25".getBytes("utf-8"), "utf-8");
		String receiver = new String (request.getParameter("receiver").getBytes("utf-8"), "utf-8");
		String subject = new String (request.getParameter("subject").getBytes("utf-8"), "utf-8");
		String content = request.getParameter("content");
		
		System.out.println(sender);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try{
			Properties properties = System.getProperties();
			properties.put("mail.transport.protocol", "smtp");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.naver.com");
			properties.put("mail.smtp.debug", "true");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "587"); //gamil 포트
			
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.socketFactory.fallback", "false");
		
			Authenticator auth = new SMTPAuthenticatior();
			Session session = Session.getDefaultInstance(properties, auth);
			
			session.setDebug(true);
			
			//메일의 내용을 담을 객체
			Message message = new MimeMessage(session);
			message.setHeader("content-type", "text/html;charset=utf-8");
			message.setSubject(subject); //제목
			
			//sender_address 에는 메일을 쓰면 오류
			Address sender_address = new InternetAddress(sender);
			message.setFrom(sender_address); //보내는사람.
			
			//받는사람 (이메일)
			Address receiver_address = new InternetAddress(receiver);
			message.addRecipient(Message.RecipientType.TO, receiver_address);
			
			//내용
			message.setContent(content, "text/html;charset=utf-8");
			//날짜
			message.setSentDate(new java.util.Date());
			
			//전송
			Transport.send(message);
			
			out.println("메일이 정상적으로 전송되었습니다.");
		}catch(Exception e){
			System.out.println(e.getMessage()+" SMTP 서버가 잘못되었거나 메일 보내기 에러");
			out.print("SMTP 서버가 잘못되었거나 메일 보내기 에러");
		}
		out.close();
	}

}
