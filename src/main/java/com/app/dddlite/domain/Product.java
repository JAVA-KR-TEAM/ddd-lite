package com.app.dddlite.domain;

import javax.persistence.*;

@Entity
@Table(name = "tb_product")
public class Product {
    /**
     * 상품(product) entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Long price;

}
