package com.dsecurity.dto.response;

import com.dsecurity.model.CartItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CartResponse {
    private Long cartId;
    private Set<CartItems> cartItems;
    private int totalQuantity;
    private float totalPrice;


    public CartResponse(Long cartId, Set<CartItems> cartItems) {
        this.cartId = cartId;
        this.cartItems = cartItems;
    }

    public int getTotalQuantity() {
        for (CartItems item:this.cartItems
        ) {
            this.totalQuantity += item.getTotalAmount();
        }
        return totalQuantity;
    }

    public float getTotalPrice() {
        for (CartItems item:this.cartItems
             ) {
            this.totalPrice += item.getTotalprice();
        }
        return totalPrice;
    }
}
