package com.app.dddlite.domain;

public class OrderLine {
    /**
     * 주문품목(OrderLine)은 VO로 고유의 식별성이 필요없다.
     */
    private int quantity;
    private Product product;

    public OrderLine(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }
}
