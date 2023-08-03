package com.dsecurity.repository;

import com.dsecurity.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart,Long> {
}
