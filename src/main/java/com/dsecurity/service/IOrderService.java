package com.dsecurity.service;

import com.dsecurity.model.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IOrderService<T,E> {
    T saveAndUpdate(T t);


    Page<T> getAll(Pageable pageable);
}
