package com.codegym.repository;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, Long > {
    Iterable<Customer> findAllByProvince(Province province);
    Iterable<Customer> findALLByProvince(Province province);
    Page<Customer> findAllByFirstName(String firstName, Pageable pageable);
}
