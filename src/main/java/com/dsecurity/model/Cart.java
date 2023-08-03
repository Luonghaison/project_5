package com.dsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cart")
public class Cart {
    @Id
    @Column(name = "cartId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "id")
//    private User users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cartdetail",
            joinColumns = {@JoinColumn(name = "cartdetailId")},
            inverseJoinColumns = {@JoinColumn(name = "cartItemId")})
    private Set<CartItems> item = new HashSet<>();

    private double totalPrice;

    public double getTotalPrice() {
        for (CartItems cartiten : item) {
            this.totalPrice += cartiten.getTotalprice();
        }
        return totalPrice;
    }
}
