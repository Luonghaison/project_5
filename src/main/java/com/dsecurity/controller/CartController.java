package com.dsecurity.controller;

import com.dsecurity.dto.request.CartRequest;
import com.dsecurity.dto.response.CartResponse;
import com.dsecurity.model.*;
import com.dsecurity.security.userPrincipal.UserDetailService;
import com.dsecurity.security.userPrincipal.UserPrincipal;
import com.dsecurity.service.serviceIMPL.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/cartcontroller")
public class CartController {

    @Autowired
    private CartServiceIMPL cartServiceIMPL;

    @Autowired
    private CartItemServiceIMPL cartItemServiceIMPL;
    @Autowired
    private UserServiceIMPL userServiceIMPL;

    @Autowired
    private ProductServiceIMPL productServiceIMPL;

    @Autowired
    UserDetailService userDetailService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createCart(@RequestBody CartRequest cartRequests) {
        User user = userDetailService.getUserPrincipal();
        boolean check = true;
        Cart cart = user.getCart();
        if (cart == null){
            cart = new Cart();
        }
        Set<CartItems> items = cart.getItem();
        boolean checkexit = false;
        try {
            for (CartItems cartItem : items) {
                if (cartItem.getProduct().getProductId() == cartRequests.getProductId()) {
                    cartItem.setTotalAmount(cartItem.getTotalAmount() + cartRequests.getQuantity());
                    cartItemServiceIMPL.save(cartItem);
                    checkexit = true;
                    break;
                }

            } if (!checkexit){
                Product product = productServiceIMPL.getById(cartRequests.getProductId());
                CartItems newCartitem = new CartItems(cartRequests.getQuantity(), product);
                cartItemServiceIMPL.save(newCartitem);
                cart.getItem().add(newCartitem);
                cartServiceIMPL.save(cart);
            }
            user.setCart(cart);
            userServiceIMPL.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Thêm sản phẩm vào giỏ hàng thất bại!");        }
        if (check) {
            return ResponseEntity.ok("Thêm sản phẩm vào giỏ hàng thành công!!!");
        } else {
            return ResponseEntity.ok("Thêm sản phẩm vào giỏ hàng thất bại!");
        }
    }

    @GetMapping("getAllCart")
    public ResponseEntity<?> getAllCart() {
        User user = userDetailService.getUserPrincipal();
        Cart cart = user.getCart();
       CartResponse cartResponse = new CartResponse(cart.getId(),cart.getItem());
        return ResponseEntity.ok(cartResponse);
    }

    @PutMapping("update/{cartItemId}/{quantity}")
    public ResponseEntity<?> updateCart(@PathVariable("quantity") Long productQuantity, @PathVariable("cartItemId") Long cartItemId) {
        try {
            CartItems cartItems = cartItemServiceIMPL.findById(cartItemId);
            cartItems.setTotalAmount(productQuantity);
            cartItemServiceIMPL.save(cartItems);
            return ResponseEntity.ok("Đã cập nhập thành công ");
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.ok("Chưa cập nhập được giỏ hàng");
        }
    }

//    @DeleteMapping("delete")
//    public ResponseEntity<?> clearCart(){
//        User user = userDetailService.getUserPrincipal();
//        Cart cart =  user.getCart();
//        Set<CartItems> cartItemsSet = cart.getItem();
//        cart.setItem(new HashSet<>());
//        for (CartItems items: cartItemsSet) {
//            cartItemServiceIMPL.delete(items.getId());
//        }
//        user.setCart(cart);
//        userServiceIMPL.save(user);
//        return ResponseEntity.ok("Đã xóa khỏi giỏ hàng");
//    }
    @DeleteMapping("delete")
    public ResponseEntity<?> clearCart(){
        Cart cart = userDetailService.getUserPrincipal().getCart();
        Set<CartItems> cartItemsSet = cart.getItem();
        cart.setItem(new HashSet<>());
        cartItemServiceIMPL.deleteSetCart(cartItemsSet);
        cartServiceIMPL.save(cart);
        return ResponseEntity.ok("đã xóa");
    }
}

