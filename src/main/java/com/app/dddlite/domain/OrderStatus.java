package com.app.dddlite.domain;

public enum OrderStatus {
    WAIT("결제대기중"),
    READY("준비중"),
    DELIVERY("배송중"),
    DONE_DELIVERY("배송완료"),
    CANCEL("결제취소");

    private String desc;
    OrderStatus(String desc) {
        this.desc = desc;
    }

}
