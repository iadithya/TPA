package com.server.common;


import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class UtilFactory {
	private static UtilFactory instance;

//	private static String SMTP_HOST = null;
//	private static String SMTP_PORT = null;
//	private static String d_email = null;
//	private static String d_password = null;
//	
//	private UtilFactory(){
//		try{
//			SMTP_HOST = PropertiesLoader.getInstance().getValue(
//					"email.smtp.host");
//			 SMTP_PORT = PropertiesLoader.getInstance().getValue(
//					"email.smtp.port");
//			 d_email=PropertiesLoader.getInstance().getValue("pop.admin.email");
//			 d_password=PropertiesLoader.getInstance().getValue("pop.admin.email.password");
//			
//			if (logger.isInfoEnabled()) {
//				logger.logInfo("SMTP Host > "+SMTP_HOST+" Port > "+SMTP_PORT+" email > "+d_email);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	public static UtilFactory getInstance() throws Exception {
		if (instance == null) {
			return instance = new UtilFactory();
		} else {
			return instance;
		}
	}
//	private static class SMTPAuthenticator extends javax.mail.Authenticator {
//		public PasswordAuthentication getPasswordAuthentication() {
//			return new PasswordAuthentication(d_email, d_password);
//		}
//	}
//	public boolean sendEmail(String m_to, String subject, String emailcontent) {
//		boolean emailSend = true;
//		try {
//			
//			if(d_email!=null&&SMTP_HOST!=null&&SMTP_PORT!=null){
//				Properties props = new Properties();
//				props.put("mail.smtp.user", d_email);
//				props.put("mail.smtp.host", SMTP_HOST);
//				props.put("mail.smtp.port", SMTP_PORT);
//				props.put("mail.smtp.starttls.enable", "true");
//				props.put("mail.smtp.auth", "true");
//				props.put("mail.smtp.debug", "true");
//				props.put("mail.smtp.socketFactory.port", SMTP_PORT);
//				props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
//				props.put("mail.smtp.socketFactory.fallback", "false");
//			
				
	//			SecurityManager security = System.getSecurityManager();
		
//				Authenticator auth = new SMTPAuthenticator();
//				Session session = null;
//				if(d_password!=null){
//					session=Session.getInstance(props, auth);
//				}
//				else {
//				session=Session.getInstance(props);
//				}
//				session.setDebug(true);
//				MimeMessage msg = new MimeMessage(session);
//				// msg.setText(m_text);
//				msg.setSubject(subject);
//				msg.setFrom(new InternetAddress(d_email));
//				msg.addRecipient(Message.RecipientType.TO,
//						new InternetAddress(m_to));
//		
//				// Create the message part
//				BodyPart messageBodyPart = new MimeBodyPart();
//		
				// Fill the message
				
		
				// Create a multipart message
			//	Multipart multipart = new MimeMultipart();
		
				// Set text message part
		//		multipart.addBodyPart(messageBodyPart);
				// Part two is attachment
				// MimeBodyPart messageBodyPart = new MimeBodyPart();
//				try {
//					
//					messageBodyPart.setContent(emailcontent,"text/html");
//				} catch (Exception e) {
//					e.printStackTrace();
//					emailSend = false;
//				}
				//multipart.addBodyPart(messageBodyPart);
				// Send the complete message parts
		//		msg.setContent(multipart);
		
				// Send message
//				if (emailSend) {
//					Transport.send(msg);
//					
//				} else {
//					
//				}
//			} else {
//				
//				emailSend = false;
//			}
//		} catch (MessagingException mex) {
//			mex.printStackTrace();
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//	
//		return emailSend;
//	}
	public Pattern getSearchPattern(String searchPattern) throws Exception{
		Pattern pattern = null;
			System.out.println("Seearch pattern word is "+searchPattern);
		
		Pattern pattern1 = java.util.regex.Pattern.compile("(\\w+)");
		Matcher matcher = pattern1.matcher(searchPattern);
		if(matcher.find()){
			System.out.println("After taking the word >>>>>>>>>>>>>"+searchPattern.substring(matcher.start(), matcher.end()));
			pattern = java.util.regex.Pattern.compile(searchPattern.substring(matcher.start(), matcher.end()));
		}
		return pattern;
	}
//	public static void main(String[] args){
//		try{
//			EmailSenderFactory f = new EmailSenderFactory();
//			System.out.println(f.getSearchPattern("4535353fine4554)"));
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}