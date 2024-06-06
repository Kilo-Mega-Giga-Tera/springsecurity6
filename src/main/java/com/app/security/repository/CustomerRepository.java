package com.app.security.repository;

import com.app.security.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByEmail(@Param("email") String email);

}
