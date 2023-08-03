package com.dsecurity.service;

import java.util.List;

public interface IManufacturerSerrvice<T,E> {
    List<T> getAll();
    T save(T t);
    void delete(E id);

    T getById(E id);
}
