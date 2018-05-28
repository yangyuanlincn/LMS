package iim;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class JavaMailTest02 {
	@Test
	public void testJavaMail() throws MessagingException {
		ClassPathXmlApplicationContext cls = new ClassPathXmlApplicationContext("classpath:applicationContext-mail.xml");
		JavaMailSender mailSender = (JavaMailSender) cls.getBean("mailSender");
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		
		helper.setFrom("938532469@qq.com");
		helper.setTo("806640224@qq.com");
		helper.setSubject("哥哥你好！");
		helper.setText("<html><head></head><body><a href='http://www.4399.com'>哥哥你好</a>"+"<img src='cid:image'/></body></html>",true);
		
		FileSystemResource resource = new FileSystemResource("C:\\Users\\Linn\\Desktop\\0142135541fe180000019ae9b8cf86.jpg@1280w_1l_2o_100sh.png");
		helper.addInline("image", resource);
		mailSender.send(message);
	}
}
