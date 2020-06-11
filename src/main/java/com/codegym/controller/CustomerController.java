package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    //    @GetMapping()
//    public ModelAndView getCustomerPage() {
//        ModelAndView modelAndView = new ModelAndView("customer/list-customer");
//        modelAndView.addObject("customers", customerService.getAll());
//        modelAndView.addObject("provinces", provinces());
//        modelAndView.addObject("customer", new Customer());
//        return modelAndView;
//    }
    @GetMapping()
    public ModelAndView listCustomers(@RequestParam("s") Optional<String> s, @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = new PageRequest(page, size);
        Page<Customer> customers;
        if (s.isPresent()) {
            customers = customerService.findAllByFirstName(s.get(), pageable);
        } else {
            customers = customerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/customer/list-customer");
        modelAndView.addObject("customers", customers);
        modelAndView.addObject("provinces", provinces());
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/find")
    public ModelAndView getCustomerByProvince(@RequestParam Province province) {
        ModelAndView modelAndView = new ModelAndView("customer/list-customer");
        modelAndView.addObject("customers", customerService.getAllByProvince(province));
        modelAndView.addObject("provinces", provinces());
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return provinceService.getAll();
    }

    @GetMapping("/create-form")
    public ModelAndView moveToCustomerFormPage() {
        ModelAndView modelAndView = new ModelAndView("customer/form-customer");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("provinces", provinces());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView("customer/form-customer");
        Customer customer1 = customerService.save(customer);
        System.out.println(customer1.getFirstName());
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView moveToEditCustomerPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customer", customerService.getOne(id));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editCustomer(@ModelAttribute Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("message", "thanh cong");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomer(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("customer/list-customer");
        customerService.delete(id);
        modelAndView.addObject("customers", customerService.getAll());
        return modelAndView;
    }

}