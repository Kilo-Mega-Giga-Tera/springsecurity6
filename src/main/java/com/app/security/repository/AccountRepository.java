package com.app.security.repository;

import com.app.security.model.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Accounts, Long> {

    Accounts findByCustomerId(Integer customerId);

}
