package com.app.dddlite.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    /**
     * 상품(product) entity
     */
    @Id
    private Long id;
    private String name;
    private Long price;
}
