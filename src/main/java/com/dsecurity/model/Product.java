package com.dsecurity.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "productName", columnDefinition = "nvarchar(50)",unique = true,nullable = false)
    private String productName;

    @Column(name = "creatDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date creatDate;

    @Column(name = "price")
    private float price;

    @Column(name = "tittle")
    private String title;

    @ManyToOne
    @JoinColumn(name = "catalogId")
    private Catalog catalog;

    @Column(name = "productImg")
    private String productImg;

    @Column(name="productDescription")
    private String productDescription;

    @Column(name = "productStatus")
    private boolean productStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manuId")
    private Manufacturer manufacturer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "colorId")
    private Color color;


}