package com.app.security.repository;

import com.app.security.model.ContactMessages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRespository extends CrudRepository<ContactMessages, Long> {

}
