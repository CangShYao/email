import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class Send {
    // 获取系统属性
    public Send(String receiver, String subjection, String content) {
        String myEmailAccount = "xxxxxxxxxxx@163.com";
        // 发件人电子邮箱
        String myEmailPassword = "xxxxxx";
        // 发件人密码
        String myEmailSMTPHost = "smtp.163.com";
        // smtp host
        String receiveMailAccount;
        // 收件人电子邮件
        String emailSubject;
        // 主题
        String emailContent;
        // 内容
        receiveMailAccount = receiver;
        emailSubject = subjection;
        emailContent = content;
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);         // 设置为debug模式, 可以查看详细的发送 log

        // 3. 创建一封邮件
        MimeMessage message = null;
        try {
            message = createMimeMessage(session, myEmailAccount, receiveMailAccount, emailSubject, emailContent);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // 4. 根据 Session 获取邮件传输对象
        Transport transport = null;
        try {
            transport = session.getTransport();
        } catch (
                NoSuchProviderException e1) {
            e1.printStackTrace();
        }

        try {
            transport.connect(myEmailAccount, myEmailPassword);
        } catch (
                MessagingException e1) {
            e1.printStackTrace();
        }

        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients()
        // 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        try {
            transport.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e1) {
            e1.printStackTrace();
        }

        // 7. 关闭连接
        try {
            transport.close();
        } catch (MessagingException e1) {
            e1.printStackTrace();

        }
    }

    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String emailSubject, String emailContent) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, sendMail, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveMail, "UTF-8"));

        // 4. Subject: 邮件主题
        message.setSubject(emailSubject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(emailContent, "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }
}
