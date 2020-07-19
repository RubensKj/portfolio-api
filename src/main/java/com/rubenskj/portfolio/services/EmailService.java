package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.ContactDTO;
import com.rubenskj.portfolio.property.EmailProperty;
import com.rubenskj.portfolio.util.EmailDesign;
import com.rubenskj.portfolio.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final EmailProperty emailProperty;

    public EmailService(EmailProperty emailProperty) {
        this.emailProperty = emailProperty;
    }

    public void sendEmailFromContact(List<MultipartFile> attachments, ContactDTO contactDTO) throws MessagingException {
        try {

            Session session = this.getSession();

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(contactDTO.getEmail()));
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(emailProperty.getToEmail()));
            message.setSubject("Portfolio Contact!!");

            String msg = EmailDesign.createDesignedEmail("Portfolio Contacting", contactDTO);

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            if (attachments != null && !attachments.isEmpty()) {
                attachments.forEach(attachment -> {
                    MimeBodyPart toAttach = EmailUtil.addAttachment(attachment);

                    try {
                        multipart.addBodyPart(toAttach);
                    } catch (MessagingException e) {
                        LOGGER.error("Cannot send attachment. Attachment: {}", toAttach);
                    }
                });
            }

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.error("It wasn't able to send the e-mail. Ex: {}", e);
            throw e;
        }
    }

    private Session getSession() {
        String username = emailProperty.getUsername();
        String password = emailProperty.getPassword();

        return EmailUtil.getSessionEmailInstance(username, password);
    }
}
