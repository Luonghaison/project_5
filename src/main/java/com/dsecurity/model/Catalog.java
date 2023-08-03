package com.dsecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="catalog")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalogId")
    private Long catalogId;

    @Column(name = "catalogName", nullable = false,unique = true)
    private String catalogName;

    @Column(name = "catadescription")
    private String catalogDescription;
}
