package com.codegym.service.customer;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.IService;

public interface ICustomerService extends IService<Customer> {
    Iterable<Customer> getAllByProvince(Province province);
}