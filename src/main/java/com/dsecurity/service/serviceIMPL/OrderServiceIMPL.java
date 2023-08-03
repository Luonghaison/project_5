package com.dsecurity.service.serviceIMPL;

import com.dsecurity.model.Order;
import com.dsecurity.repository.IManufacturerRepository;
import com.dsecurity.repository.IOrderRepository;
import com.dsecurity.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceIMPL implements IOrderService<Order,Long> {
    @Autowired
    private IOrderRepository iOrderRepository;
    @Override
    public Order saveAndUpdate(Order order) {
        return iOrderRepository.save(order);
    }


    @Override
    public Page<Order> getAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(),5);
        return iOrderRepository.findAll(pageable);

    }
}

