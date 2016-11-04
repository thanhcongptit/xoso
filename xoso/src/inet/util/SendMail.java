/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author iNET
 */
public class SendMail {
  public static boolean send(String msg)
  {
    String to = "adm@xosobamien.vn";
    String from = "lienhe@xosobamien.vn";

    String host = "smtp.zoho.com";

    Properties properties = System.getProperties();

    properties.setProperty("mail.smtp.host", host);
    properties.setProperty("mail.smtp.port", "465");

    Session session = Session.getDefaultInstance(properties);

    boolean isSendMail = false;
    try
    {
      MimeMessage message = new MimeMessage(session);

      message.setFrom(new InternetAddress(from));

      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      message.setSubject("Xo so ba mien");

      message.setText(msg);

      Transport.send(message);
      isSendMail = true;
      System.out.println("Sent message successfully....");
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }

    return isSendMail;
  }
  
  
  public static boolean sendMailFromZoho(String msg) {
    boolean result = false;    

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.zoho.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    Session session = Session.getInstance(props, new Authenticator()
    {
      protected PasswordAuthentication getPasswordAuthentication()
      {
        return new PasswordAuthentication("lienhe@xosobamien.vn", "iNET@123");
      }
    });
    try
    {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress("lienhe@xosobamien.vn"));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("adm@xosobamien.vn"));

      message.setSubject("Xo so ba mien");
      message.setText(msg);

      Transport.send(message);

      System.out.println("Done");
      result = true;
    }
    catch (MessagingException e) {
      System.err.println("loi gui mail " + e.toString());
    }

    return result;
  }
  
  public static void main(String [] arg){
      SendMail sendMail=new SendMail();
      sendMail.sendMailFromZoho("test");
  }

}
