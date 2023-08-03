package com.dsecurity.service;

import com.dsecurity.model.CartItems;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public interface ICartItemService<T,E> {
    CartItems save(T t);

    void delete(E id);
    CartItems findById(E id);

    List<T> findCartItemByIdCart(E id);

    void deleteSetCart(Set<T> set);
}
