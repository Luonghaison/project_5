package com.dsecurity.controller;


import com.dsecurity.model.*;
import com.dsecurity.security.userPrincipal.UserDetailService;
import com.dsecurity.service.serviceIMPL.CartItemServiceIMPL;
import com.dsecurity.service.serviceIMPL.CartServiceIMPL;
import com.dsecurity.service.serviceIMPL.OrderServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ordercontroller")
public class OrderController {

    @Autowired
    UserDetailService userDetailService;

    @Autowired
    OrderServiceIMPL orderServiceIMPL;

    @Autowired
    private CartItemServiceIMPL cartItemServiceIMPL;

    @Autowired
    private CartServiceIMPL cartServiceIMPL;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createOrder(@RequestBody Order order) {
        User user = userDetailService.getUserPrincipal();
        Cart cart = user.getCart();
        Order orderUpdate = new Order();
        orderUpdate.setUser(user);
        orderUpdate.setOrderName(user.getFullName());
        orderUpdate.setAdress(order.getAdress());
        orderUpdate.setEmail(order.getEmail());
        orderUpdate.setPhone(order.getPhone());
        orderUpdate.setTotalPrice((int) cart.getTotalPrice());
        orderServiceIMPL.saveAndUpdate(orderUpdate);
        //xoa cart
        Cart cartDelete = userDetailService.getUserPrincipal().getCart();
        Set<CartItems> cartItemsSet = cart.getItem();
        cart.setItem(new HashSet<>());
        cartItemServiceIMPL.deleteSetCart(cartItemsSet);
        cartServiceIMPL.save(cartDelete);
        return ResponseEntity.ok("Bạn đã order thành công");
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
    public Page<Order> getAllOrder(Pageable pageable) {
        return orderServiceIMPL.getAll(pageable);
    }
}