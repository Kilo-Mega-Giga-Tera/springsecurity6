package com.app.security.controller;

import com.app.security.model.NoticeDetails;
import com.app.security.repository.NoticeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final NoticeDetailRepository noticeDetailRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<NoticeDetails>> getNotices() {
        List<NoticeDetails> noticeDetails = noticeDetailRepository.findAllActiveNoticDetails();

        if (noticeDetails != null)
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(noticeDetails);
        else
            return null;

    }

}
