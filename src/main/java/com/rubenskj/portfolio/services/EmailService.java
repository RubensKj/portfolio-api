package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.ContactDTO;
import com.rubenskj.portfolio.file.service.ImageService;
import com.rubenskj.portfolio.property.EmailProperty;
import com.rubenskj.portfolio.util.EmailDesign;
import com.rubenskj.portfolio.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static com.rubenskj.portfolio.enums.PathTypeEnum.CONTACT_PATH_URI;

@Service
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final EmailProperty emailProperty;
    private final ImageService imageService;

    public EmailService(EmailProperty emailProperty, ImageService imageService) {
        this.emailProperty = emailProperty;
        this.imageService = imageService;
    }

    public void sendEmailFromContact(List<MultipartFile> attachments, ContactDTO contactDTO) throws MessagingException, UnsupportedEncodingException {
        Session session = this.getSession();

        session.setDebug(true);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailProperty.getUsername(), "RubensKj's Portfolio"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(emailProperty.getToEmail(), true));
        message.setSubject("Portfolio Contact!!");

        message.setFlag(Flags.Flag.RECENT, true);
        message.setHeader("X-Priority", "1");

        String msg = EmailDesign.createDesignedEmail("Portfolio Contacting", contactDTO);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        if (attachments != null && !attachments.isEmpty()) {
            attachments.forEach(attachment -> this.addAttachment(multipart, attachment));
        }

        message.setContent(multipart);

        LOGGER.info("Transporting the e-mail.");
        Transport transport = session.getTransport("smtp");
        transport.connect(emailProperty.getHost(), emailProperty.getUsername(), emailProperty.getPassword());
        transport.sendMessage(message, message.getAllRecipients());
    }

    private Session getSession() {
        String host = emailProperty.getHost();
        String username = emailProperty.getUsername();
        String password = emailProperty.getPassword();
        boolean ssl = emailProperty.isSsl();

        return EmailUtil.getSessionEmailInstance(username, password, host, ssl);
    }

    public void addAttachment(Multipart multipart, MultipartFile multipartFile) {
        try {
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();

            String fileName = transferToLocalToSendToEmail(multipartFile);

            DataSource source = new FileDataSource(fileName);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(multipartFile.getOriginalFilename());

            try {
                multipart.addBodyPart(attachmentBodyPart);
            } catch (MessagingException e) {
                LOGGER.error("Cannot send attachment. Attachment: {}", attachmentBodyPart);
            }
        } catch (MessagingException e) {
            LOGGER.error("Error during the parse or attach to MimeBodyPart. Ex: -> {}", e);
            throw new IllegalStateException("Error during the parse or attach to MimeBodyPart.");
        }
    }

    private String transferToLocalToSendToEmail(MultipartFile multipartFile) {
        String pathToBeResolved = CONTACT_PATH_URI.getType().concat("/emails/").concat(multipartFile.getOriginalFilename());
        String pathToFile = this.imageService.getPath().resolve(pathToBeResolved).normalize().toString();

        File file = new File(pathToFile);

        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            LOGGER.error("Cannot transfer file to locally. File name -> {}", multipartFile.getOriginalFilename());
        }

        return pathToFile;
    }
}
