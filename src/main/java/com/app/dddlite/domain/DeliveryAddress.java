package com.app.dddlite.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class DeliveryAddress {
    @Column(name = "receiver")
    private String receiver;
    @Column(name = "receiveAddr")
    private String receiveAddr;
    @Column(name = "receiveAddrDetail")
    private String receiveAddrDetail;

    @Builder
    public DeliveryAddress(String receiver, String receiveAddr, String receiveAddrDetail) {
        this.receiver = receiver;
        this.receiveAddr = receiveAddr;
        this.receiveAddrDetail = receiveAddrDetail;
    }
}
