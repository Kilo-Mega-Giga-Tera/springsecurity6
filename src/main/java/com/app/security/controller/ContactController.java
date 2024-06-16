package com.app.security.controller;

import com.app.security.model.ContactMessages;
import com.app.security.repository.ContactMessageRespository;
import com.app.security.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactMessageRespository contactMessageRespository;

    /* PreFilter & PostFiler sample */

    /*
     *  This code is test for method level security.
     *  If 'contactName' contains the word 'Test', it will be filtered.
     *  In this case, you cannot save contact message.
     *
     *  Check before the method is executed.
     */
    @PreFilter("filterObject.contactName != 'Test'")

    /*
     *  This code is test for method level security.
     *  If 'contactName' contains the word 'Test', it will be filtered.
     *  In this case you can save contact message.
     *  But you cannot receive result from spring web application.
     *
     *  Check after the method is executed.
     */
    // @PostFilter("filterObject.contactName != 'Test'")
    // import org.springframework.security.access.prepost.PostFilter;
    @PostMapping("/contact")
    public List<ContactMessages> saveContactInquiryDetails(@RequestBody List<ContactMessages> contactMessage) {

        if (ObjectUtils.isEmpty(contactMessage))
            throw new RuntimeException("Cannot include Test");

        List<ContactMessages> contactMessageList = new ArrayList<>();
        ContactMessages message = contactMessage.get(0);

        message.setContactId(CommonUtils.getServiceReqNumber());
        message.setCreateDt(new Date(System.currentTimeMillis()));

        message = contactMessageRespository.save(message);

        contactMessageList.add(message);

        return contactMessageList;
    }

}
