package uniqueproject.uz.go2uz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class Go2uzApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void testSendEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("sjahongir221@gmail.com");
		message.setTo("recipient@example.com");
		message.setSubject("Test Email");
		message.setText("This is a test email.");
		mailSender.send(message);
	}

}
