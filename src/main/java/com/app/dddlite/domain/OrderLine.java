package com.app.dddlite.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine {

    @Id
    private Long id;
    private Long quantity;
    @ManyToOne
    private Product product;
}
