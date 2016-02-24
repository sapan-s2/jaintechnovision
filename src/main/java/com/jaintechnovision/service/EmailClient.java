package com.jaintechnovision.service;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by sjain on 2/19/2016.
 */
@org.springframework.stereotype.Service
public class EmailClient {


    protected String message_recip = "jsapan4@gmail.com";

    /* What's it all about, Alfie? */
    protected String message_subject = "Re: your mail";

    /** The message CC recipient. */
    protected String message_cc = "jsapan123@yahoo.com";

    /** The message body */
    protected String message_body = "Test Email Client";




    /** The JavaMail session object */
    protected Session session;

    /** The JavaMail message object */
    protected Message mesg;

    /** Do the work: send the mail to the SMTP server. */
    public void doSend() {

        // We need to pass info to the mail server as a Properties, since
        // JavaMail (wisely) allows room for LOTS of properties...
        Properties props = new Properties();

        // Your LAN must define the local SMTP server as "mailhost"
        // for this simple-minded version to be able to send mail...
        props.put("mail.smtp.host", "mailhost");

        // Create the Session object
        session = Session.getDefaultInstance(props, null);
        session.setDebug(true); // Verbose!

        try {
            // create a message
            mesg = new MimeMessage(session);

            // From Address - this should come from a Properties...
            mesg.setFrom(new InternetAddress("techhelp@jaintechnovisions.site88.net"));

            // TO Address
            InternetAddress toAddress = new InternetAddress(message_recip);
            mesg.addRecipient(Message.RecipientType.TO, toAddress);

            // CC Address
            InternetAddress ccAddress = new InternetAddress(message_cc);
            mesg.addRecipient(Message.RecipientType.CC, ccAddress);

            // The Subject
            mesg.setSubject(message_subject);

            // Now the message body.
            mesg.setText(message_body);
            // XXX I18N: use setText(msgText.getText(), charset)

            // Finally, send the message!
            Transport.send(mesg);

        } catch (MessagingException ex) {
            while ((ex = (MessagingException) ex.getNextException()) != null) {
                ex.printStackTrace();
            }
        }
    }

    /** Simple test case driver */
//    public static void main(String[] av) {
//        EmailClient emailClient = new EmailClient();
//        emailClient.doSend();
//    }
}

