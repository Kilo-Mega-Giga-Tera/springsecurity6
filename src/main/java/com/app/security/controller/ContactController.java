package com.app.security.controller;

import com.app.security.model.ContactMessages;
import com.app.security.repository.ContactMessageRespository;
import com.app.security.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactMessageRespository contactMessageRespository;

    @PostMapping("/contact")
    public ContactMessages saveContactInquiryDetails(@RequestParam("id") ContactMessages contactMessage) {
        contactMessage.setContactId(CommonUtils.getServiceReqNumber());
        contactMessage.setCreateDt(new Date(System.currentTimeMillis()));
        return contactMessageRespository.save(contactMessage);
    }

}
