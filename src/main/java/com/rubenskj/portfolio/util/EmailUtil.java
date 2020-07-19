package com.rubenskj.portfolio.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    private EmailUtil() {
    }

    public static Session getSessionEmailInstance(String username, String password, String host, boolean ssl) {
        Properties properties = getProperties(host, username, ssl);

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public static Properties getProperties(String host, String username, boolean ssl) {
        Properties props = new Properties();

        props.put("mail.smtp.user", username);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.localhost", host);

        if (ssl) {
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", 465);
        } else {
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.socketFactory.port", 587);
        }

        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");

        return props;
    }
}
