package com.utils;

import sun.net.smtp.SmtpClient;

public class Sendmail {
    String host = "mail.inet.vn";

    String to = "khoivt@inet.vn";

    String subject = "";

    String from = "";

    String content = "";

    public boolean sendMail() {
        try {
            SmtpClient mailer = new SmtpClient(host);
            mailer.from(from);
            mailer.to(to);
            java.io.PrintStream ps = mailer.startMessage();
            ps.println("text/html; charset=UTF-8");
            ps.println("charset=UTF-8");
            ps.println("Content-Type: text/html; charset=utf-8");
            ps.println("Content-Transfer-Encoding: 8bit");
            ps.println("From:" + from);
            ps.println("To:" + to);
            ps.println("Subject:" + subject);
            ps.println();
            ps.println(content);
            mailer.closeServer();
            System.out.println("mail has been sent");
            return true;
        } catch (Exception ex) {
            System.out.println(" ERR : " + ex.toString() + " "
                    + ex.getMessage());
            return true;
        }

    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {

    }

    /***************************************************************************
     * @param host
     *            The host to set.
     **************************************************************************/
    public void setHost(String host) {
        this.host = host;
    }

    /***************************************************************************
     * @param to
     *            The to to set.
     **************************************************************************/
    public void setTo(String to) {
        this.to = to;
    }
}