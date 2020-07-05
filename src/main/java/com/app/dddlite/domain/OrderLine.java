package com.app.dddlite.domain;

import javax.persistence.*;

@Entity
@Table(name = "tb_orderline")
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable=false, updatable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(name = "order_id", insertable=false, updatable = false)
    private Order order;
}
