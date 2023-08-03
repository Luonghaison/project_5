package com.dsecurity.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cartItem")
public class  CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItemId")
    private Long id;

    @Column(name = "totalAmount")
    private float totalAmount;

    @Column(name = "totalPrice")
    private float totalprice;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "productId")
    private Product product;

    public float getTotalprice() {
        this.totalprice = this.product.getPrice() * totalAmount;
        return this.totalprice;
    }

    public CartItems(float totalAmount, Product product) {
        this.totalAmount = totalAmount;
        this.product = product;
    }
}
