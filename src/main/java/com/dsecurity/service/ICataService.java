package com.dsecurity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface ICataService<T,E> {
        List<T> getAll();
        T saveAndUpdate(T t);
        void delete(E id);
        T getById(E id);
        List<T> findByCatalogNameContaining(String name);
        T findByCatalogId(Long id);
        List<T> sortCatalogByCatalogName(String direction);
        Page<T> getPagging(Pageable pageable);
}
