package com.app.security.repository;

import com.app.security.model.NoticeDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDetailRepository extends CrudRepository<NoticeDetails, Long> {

    @Query(value = "from NoticeDetails where CURDATE() BETWEEN noticBegDt AND noticEndDt")
    List<NoticeDetails> findAllActiveNoticDetails();


}
