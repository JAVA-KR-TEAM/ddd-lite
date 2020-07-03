package com.app.dddlite.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    /**
     * 배송지 정보
     */
    private String receiver;
    private String receiveAddr;
    private String receiveAddrDetail;

}
