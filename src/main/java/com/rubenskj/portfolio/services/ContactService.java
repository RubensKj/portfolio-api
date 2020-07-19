package com.rubenskj.portfolio.services;

import com.rubenskj.portfolio.dto.ContactDTO;
import com.rubenskj.portfolio.file.service.ImageService;
import com.rubenskj.portfolio.model.Contact;
import com.rubenskj.portfolio.repository.IContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.rubenskj.portfolio.enums.ContactStatus.WAITING;
import static com.rubenskj.portfolio.enums.PathTypeEnum.CONTACT_PATH_URI;

@Service
public class ContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactService.class);

    private final IContactRepository contactRepository;
    private final EmailService emailService;
    private final ImageService imageService;

    public ContactService(IContactRepository contactRepository, EmailService emailService, ImageService imageService) {
        this.contactRepository = contactRepository;
        this.emailService = emailService;
        this.imageService = imageService;
    }

    public void sendContactEmail(List<MultipartFile> attachments, ContactDTO contactDTO) {
        LOGGER.info("Sending contact e-mail. ContactDTO: {}", contactDTO);

        try {
            this.emailService.sendEmailFromContact(attachments, contactDTO);
        } catch (Exception e) {
            LOGGER.error("It wasn't able to send the e-mail. Saving the contact locally.");

            if (attachments != null) {
                this.imageService.saveImages(attachments, CONTACT_PATH_URI.getType());
            }

            Contact contactFromDTO = this.createContactFromDTO(contactDTO);

            LOGGER.info("Saving Contact.");
            this.contactRepository.save(contactFromDTO);
        }
    }

    private Contact createContactFromDTO(ContactDTO contactDTO) {
        return new Contact(
                contactDTO.getCompleteName(),
                contactDTO.getEmail(),
                contactDTO.getDescription(),
                WAITING.name()
        );
    }
}
