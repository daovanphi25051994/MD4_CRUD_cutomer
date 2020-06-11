package com.codegym.controller;

import com.codegym.model.Province;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping()
    public ModelAndView getProvincePage() {
        ModelAndView modelAndView = new ModelAndView("province/list-province");
        modelAndView.addObject("provinces", provinceService.getAll());
        return modelAndView;
    }

    @GetMapping("/create-form")
    public ModelAndView moveToForm() {
        ModelAndView modelAndView = new ModelAndView("province/form-province");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProvince(@ModelAttribute Province province) {
        ModelAndView modelAndView = new ModelAndView("province/form-province");
        Province province1 = provinceService.save(province);
        if (province1 == null) {
            modelAndView.addObject("message", "save not successfully !!");
        } else {
            modelAndView.addObject("message", "save successfully !!");
        }
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteProvince(@PathVariable Long id) {
        provinceService.delete(id);
        ModelAndView modelAndView = new ModelAndView("province/list-province");
        modelAndView.addObject("provinces", provinceService.getAll());
        return modelAndView;
    }

    @GetMapping("/{id}/details")
    public ModelAndView getCustomerByProvince(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("province/list-detail-customer");
        Province province = provinceService.getOne(id);
        modelAndView.addObject("customers", customerService.getAllByProvince(province));
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView moveToEditProvincePage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("province/form-province");
        modelAndView.addObject("province", provinceService.getOne(id));
        modelAndView.addObject("message", "a");
        return modelAndView;
    }
}