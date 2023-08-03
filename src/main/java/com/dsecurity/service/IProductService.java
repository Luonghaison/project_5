package com.dsecurity.service;

import com.dsecurity.model.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService<T,E> {
    Page<T> getAll(Pageable pageable);

    T saveAndUpdate(T t);

    void delete(E id);

    T getById(E id);

    List<T> findByProductNameContaining(String name);

    List<T> findByCatalog_CatalogId(Long catalogId);

    Catalog findCatalogByIdProduct(Long productId);

    List<T> displaProductTrue();
}
