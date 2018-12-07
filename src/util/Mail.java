package util;


import java.util.*;
        import javax.mail.*;
        import javax.mail.internet.*;
        import javax.activation.*;

/**
 * <p>Title: Mail</p>
 *
 * @author zhanxirnui
 * @date 2018年12月07日
 * @require mail.jar, activation.jar
 * @description 发送邮件的类,调用sendMail发送
 */
public class Mail {
    /**
     * @param Destination 收件人的邮箱
     * @param verifyCode 验证码
     */
    public static void sendMail(String Destination,String verifyCode){
        // 收件人电子邮箱
//        String to = "756222765@qq.com";
        String to = Destination;
        // 发件人电子邮箱
        String from = "zhanxinrui2872110@163.com";

        // 指定发送邮件的主机为 localhost
        String host = "localhost";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
       // properties.setProperty("mail.smtp.host", host);//smtp.qq.com



        properties.setProperty("mail.smtp.host", "smtp.163.com"); // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        properties.put("mail.smtp.host", "smtp.163.com"); // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        properties.put("mail.smtp.auth", "true"); // 用刚刚设置好的props对象构建一个session
//        Session session = Session.getDefaultInstance(props); // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使



        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("zhanxinrui2872110@163.com", "wenwen"); //发件人邮件用户名、密码
            }

        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("This is a verifing message!");

            // 设置消息体
            message.setContent("<h2 style=\"color=#7a7a82\">This message is  from Company 3K Cafeteria:</h2>" +
                    "<p>The validation code is  "+"<span style=\"color:e2e28e;\">"+verifyCode+"</span></p>"+
                    "<p>Do not input it in other platforms</p>","text/html");
//            message.setText("This message is  from Company 3K Cafeteria: \nThe validation code is 547743 \n ");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        sendMail("756222765@qq.com","zhr123");
//        sendMail("583441559@qq.com","sdaf123");
//        sendMail("zhanxinrui2872110@163.com","fjfj11");
//        sendMail("xrzhan@stu.xidian.edu.cn","hello my bro");
//    }
}