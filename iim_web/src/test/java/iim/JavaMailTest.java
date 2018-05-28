package iim;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class JavaMailTest {
	@Test
	public void testJavaMail() throws Exception {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.qq.com");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.timeout", "0");
//		properties.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		
		MimeMessage message = new MimeMessage(session);
		Address from = new InternetAddress("938532469@qq.com");
		Address to = new InternetAddress("806640224@qq.com");
		
		message.setFrom(from);
		message.setRecipient(RecipientType.TO, to);
		
		message.setSubject("哥哥，晚上有空吗？");
		message.setText("加妹妹微信。。。。。。");
		
		message.saveChanges();

		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.qq.com","938532469","hsivklpvlracbebi");
		transport.sendMessage(message, message.getAllRecipients());
	}
}
