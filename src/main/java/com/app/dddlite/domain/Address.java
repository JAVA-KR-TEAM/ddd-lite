package com.app.dddlite.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
    /**
     * 배송지 정보
     */
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "receive_addr")
    private String receiveAddr;
    @Column(name = "receive_addr_detail")
    private String receiveAddrDetail;

}
