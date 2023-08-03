package com.dsecurity.repository;

import com.dsecurity.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartItemRepository extends JpaRepository<CartItems,Long> {

}
