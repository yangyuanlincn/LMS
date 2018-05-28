package iim;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class JavaMailTest01 {
	@Test
	public void testJavaMail() {
		ClassPathXmlApplicationContext cls = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml");
		SimpleMailMessage mailMessage=(SimpleMailMessage) cls.getBean("mailMessage");
		JavaMailSender mailSender = (JavaMailSender) cls.getBean("mailSender");
		
		mailMessage.setSubject("哥哥，你好");
		mailMessage.setText("晚上有空吗？");
		mailMessage.setTo("806640224@qq.com");
		
		mailSender.send(mailMessage);
		
	}
}
