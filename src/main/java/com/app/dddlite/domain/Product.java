package com.app.dddlite.domain;

public class Product {
    /**
     * 상품(Product)은 Entity로 고유의 식별성을 가져야한다.
     */
    private String uuid;
    private String name;
    private int price;

    public Product(String uuid, String name, int price) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
    }
}
