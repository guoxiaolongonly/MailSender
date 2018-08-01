import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class MailTranslator {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String setMail(ConfigInfo configInfo, String receiverAddress, String subject, String time, String content) {
        String isSuccess="false";
        String [] receivers=receiverAddress.split(";");
        Address[] internetAddresses =new InternetAddress[receivers.length];


        // 发件人电子邮箱
        String from = configInfo.account;

        // 获取系统属性
        Properties properties = System.getProperties();


        // 设置邮件服务器
        properties.setProperty(configInfo.server, configInfo.mailSendHost);

        properties.put(configInfo.auth, "true");
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configInfo.account, configInfo.password); //发件人邮件用户名、密码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            for(int i=0;i<receivers.length;i++)
            {
                internetAddresses[i]=new InternetAddress(receivers[i]);
            }
            // Set To: 头部头字段
            message.setRecipients(Message.RecipientType.TO,
                    internetAddresses);

            // Set Subject: 头部头字段
            message.setSubject(subject);
            message.setSentDate(simpleDateFormat.parse(time));

            // 设置消息体
//            message.setText("This is actual message");
            message.setContent(content, "text/html;charset=UTF-8");
            // 发送消息
            Transport.send(message);
            isSuccess = "true";
        } catch (AddressException e) {
            isSuccess=e.getMessage();
            e.printStackTrace();
        } catch (javax.mail.MessagingException e) {
            isSuccess=e.getMessage();
            e.printStackTrace();
        } catch (ParseException e) {
            isSuccess=e.getMessage();
            e.printStackTrace();
        }
        return isSuccess;
    }

}
