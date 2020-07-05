package com.app.dddlite.domain;

public enum OrderStatus {
    PAYMENT_WAIT("결제대기중"),
    DELIVERY_READY("배송준비중"),
    DELIVERY_DOING("배송중"),
    DELIVERY_DONE("배송완료"),
    PAYMENT_CANCEL("결제취소");

    private String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }

}
