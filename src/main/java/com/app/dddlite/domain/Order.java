package com.app.dddlite.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {
    /**
     * 주문(order) entity
     */
    @Id
    private Long id;
    private OrderStatus orderStatus;
    private Address address;

    @OneToMany
    private List<OrderLine> orderLines = new ArrayList<>();

}
