package utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    private static MailUtil instance;
    private Session mailSession;
    private String mailAccount;
    private String mailPasswd;

    public static MailUtil getInstance() {
        if (instance == null)
            instance = new MailUtil();
        return instance;
    }

    public void sendMessage() throws MessagingException {
        Transport transport = mailSession.getTransport();
        transport.connect(mailAccount, mailPasswd);
        MimeMessage mm = new MimeMessage(mailSession);
        mm.setFrom(new InternetAddress(mailAccount));
        mm.setRecipient(Message.RecipientType.TO, new InternetAddress("154075059@qq.com"));
        mm.setSubject("测试邮件");
        mm.setContent("asdasdas", "text/html;charset=utf-8");
        transport.sendMessage(mm, mm.getAllRecipients());
        transport.close();
    }

    public void Init() {
        try {
            LoggerUtil.Log("邮件服务初始化...");
            Properties conf = PropertiesUtil.GetPropFromResource("mail_config.properties");
            mailAccount = conf.getProperty("mailAccount");
            mailPasswd = conf.getProperty("mailPassword");
            Properties prop = new Properties();
            prop.put("mail.host", conf.getProperty("smtpHost"));
            prop.put("mail.transport.protocol", "smtp");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.ssl.enable", "true");
            mailSession = Session.getInstance(prop);
            LoggerUtil.Log("邮件服务初始化完毕！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
