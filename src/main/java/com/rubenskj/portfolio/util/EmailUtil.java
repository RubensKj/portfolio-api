package com.rubenskj.portfolio.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class EmailUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    private EmailUtil() {
    }

    public static Session getSessionEmailInstance(String username, String password) {
        Properties properties = getProperties();

        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public static Properties getProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "mail.rubenskj.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.trust", "mail.rubenskj.com");

        return prop;
    }

    public static MimeBodyPart addAttachment(MultipartFile multipartFile) {
        try {
            File file = parseMultipartFileToFile(multipartFile);

            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            attachmentBodyPart.attachFile(file);

            return attachmentBodyPart;
        } catch (IOException | MessagingException e) {
            LOGGER.error("Error during the parse or attach to MimeBodyPart. Ex: -> {}", e);
            throw new IllegalStateException("Error during the parse or attach to MimeBodyPart.");
        }
    }

    private static File parseMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getName());

        multipartFile.transferTo(file);

        return file;
    }
}
