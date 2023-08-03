package com.dsecurity.repository;

import com.dsecurity.model.CartItems;
import com.dsecurity.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order,Long> {
}
