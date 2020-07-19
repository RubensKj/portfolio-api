package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.ContactDTO;
import com.rubenskj.portfolio.file.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.List;

import static com.rubenskj.portfolio.PathTypeEnum.CONTACT_PATH_URI;

@Service
public class ContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    private final EmailService emailService;
    private final ImageService imageService;

    public ContactService(EmailService emailService, ImageService imageService) {
        this.emailService = emailService;
        this.imageService = imageService;
    }

    public void sendContactEmail(List<MultipartFile> attachments, ContactDTO contactDTO) {
        LOGGER.info("Sending contact e-mail. ContactDTO: {}", contactDTO);

        try {
            this.emailService.sendEmailFromContact(attachments, contactDTO);
        } catch (MessagingException e) {
            LOGGER.error("It wasn't able to send the e-mail. Saving the contact locally.");

            this.imageService.saveImages(attachments, CONTACT_PATH_URI.getType());
        }
    }
}
