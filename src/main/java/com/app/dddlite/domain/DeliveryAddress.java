package com.app.dddlite.domain;

import lombok.Builder;

public class DeliveryAddress {
    private String receiver;
    private String receiveAddr;
    private String receiveAddrDetail;

    @Builder
    public DeliveryAddress(String receiver, String receiveAddr, String receiveAddrDetail) {
        this.receiver = receiver;
        this.receiveAddr = receiveAddr;
        this.receiveAddrDetail = receiveAddrDetail;
    }
}
