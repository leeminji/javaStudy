package com.front.page.controller.action;

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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ConfigDAO;
import com.dto.ConfigVO;
import com.util.SMTPAuthenticatior;

public class PageContactAction implements PageAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command") == null ? "write" : "writeOk";
		ConfigDAO configDAO = ConfigDAO.getInstance();
		ConfigVO cfVo = configDAO.getConfig();
		
		if( command.equals("writeOk") ){
			String sender_name = request.getParameter("sender_name");
			String sender_email = request.getParameter("sender_email");
			String sender = new String ("leeminji25".getBytes("utf-8"), "utf-8");
			String receiver = new String (cfVo.getCf_admin_email().getBytes("utf-8"), "utf-8");
			String subject = new String (request.getParameter("subject").getBytes("utf-8"), "utf-8");
			String content = request.getParameter("content");
			String msg = "";
			
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
				message.setSubject("[포트폴리오에서 "+sender_name+"님이 보냄]"+subject); //제목
				
				//sender_address 에는 메일을 쓰면 오류
				Address sender_address = new InternetAddress(sender);
				message.setFrom(sender_address); //보내는사람.
				
				//받는사람 (이메일)
				Address receiver_address = new InternetAddress(receiver);
				message.addRecipient(Message.RecipientType.TO, receiver_address);
				
				//내용
				msg += "<div> 이름 : "+ sender_name+"</div>";
				msg += "<div> 메일 : "+ sender_email+"</div>";
				msg += "<div> 내용 : "+ content+"</div>";
				
				message.setContent(msg, "text/html;charset=utf-8");
				
				//날짜
				message.setSentDate(new java.util.Date());
				
				//전송
				Transport.send(message);
				out.println("<script>");
				out.println("alert('메일이 정상적으로 전송되었습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
				
			}catch(Exception e){
				System.out.println(e.getMessage()+" SMTP 서버가 잘못되었거나 메일 보내기 에러");
				out.println("<script>");
				out.println("alert('메일보내기에 실패하였습니다. 다시 시도해주세요.');");
				out.println("history.go(-1);");
				out.println("</script>");
			}
			out.close();
		}else{
			request.setAttribute("title", " contact");
			String url = "/content/contact.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}
}
