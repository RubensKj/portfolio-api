package com.rubenskj.portfolio.controller;

import com.rubenskj.portfolio.dto.ContactDTO;
import com.rubenskj.portfolio.services.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/send")
    public void sendContact(@RequestParam(name = "files", required = false) List<MultipartFile> attachments, @Valid ContactDTO contactDTO) {
        this.contactService.sendContactEmail(attachments, contactDTO);
    }
}
