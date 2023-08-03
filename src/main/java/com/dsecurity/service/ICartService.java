package com.dsecurity.service;

import com.dsecurity.model.Cart;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ICartService<T,E> {
    Cart save(T t);

    void delete(E id);
    Optional<Cart> findById(E id);
    Cart findAllUserId(E id);
}
