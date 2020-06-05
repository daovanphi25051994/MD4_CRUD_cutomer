package com.codegym.service.province;

import com.codegym.model.Province;
import com.codegym.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProvinceService implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepository;
    @Override
    public Iterable<Province> getAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province save(Province model) {
        return provinceRepository.save(model);
    }

    @Override
    public Province getOne(Long id) {
        return provinceRepository.getOne(id);
    }

    @Override
    public boolean delete(Long id) {
         provinceRepository.delete(id);
         return true;
    }

}