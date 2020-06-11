package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Iterable<Customer> getAll() {
        return customerRepository.findAll();
    }


    @Override
    public Customer save(Customer model) {
        return customerRepository.save(model);
    }

    @Override
    public Customer getOne(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public boolean delete(Long id) {
        customerRepository.delete(id);
        return true;
    }

    @Override
    public Iterable<Customer> getAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllByFirstName(String firstName, Pageable pageable) {
        return customerRepository.findAllByFirstName(firstName, pageable);
        
    }


}