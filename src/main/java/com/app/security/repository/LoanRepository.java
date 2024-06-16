package com.app.security.repository;

import com.app.security.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {

    /*
     *  This code is test for method level security.
     *
     *  Check before the method is executed.
     */
    @PreAuthorize("hasAnyRole('USER')")
    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
