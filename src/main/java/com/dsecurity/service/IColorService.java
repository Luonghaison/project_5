package com.dsecurity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IColorService<T,E> {
    List<T> getAll();
    T save(T t);
    void delete(E id);

    T getById(E id);
}
