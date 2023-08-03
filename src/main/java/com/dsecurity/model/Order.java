package com.dsecurity.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import net.bytebuddy.dynamic.loading.InjectionClassLoader;
//import sun.util.resources.Bundles;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "oders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String orderName;

    @Column
    private String email;

    @Column
    private String adress;

    @Column
    private String phone;

    @Column
    private int totalPrice;

    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orderdetail",
            joinColumns = {@JoinColumn(name = "oder_id")},
            inverseJoinColumns = {@JoinColumn(name = "cart_id")})
    private Set<CartItems> item = new HashSet<>();
}
