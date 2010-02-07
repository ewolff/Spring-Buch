package springmail;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class DemoMailSender {

    private JavaMailSender mailSender;

    private SimpleMailMessage mailMessage;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setMailMessage(SimpleMailMessage message) {
        this.mailMessage = message;
    }

    public void send() {
        SimpleMailMessage msg = new SimpleMailMessage(mailMessage);
        msg.setTo("wolff");
        msg.setText("test");
        mailSender.send(msg);
    }

    public void sendMime() {
        mailSender.send(new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(new InternetAddress("info@spring-buch"));
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress("wolff"));
                mimeMessage.setText("MIME test");
            }

        });
    }

    public void sendMimeHelper() {
        mailSender.send(new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessageHelper.setFrom("info@spring-buch");
                mimeMessageHelper.setTo("wolff");
                mimeMessageHelper.setText("MIME test");
            }

        });
    }

    
}
