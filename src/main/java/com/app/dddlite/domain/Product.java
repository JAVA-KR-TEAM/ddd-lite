package com.app.dddlite.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Product {
    /**
     * 상품(Product)은 Entity로 고유의 식별성을 가져야한다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String uuid;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;

    public Product(String uuid, String name, int price) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
    }
}
