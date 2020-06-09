package com.codegym.service;


public interface IService<T> {
    Iterable<T> getAll() throws Exception;
    T save(T model);
    T getOne(Long id);
    boolean delete(Long id);
}
