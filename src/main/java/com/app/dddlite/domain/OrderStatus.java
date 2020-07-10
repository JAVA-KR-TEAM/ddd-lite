package com.app.dddlite.domain;

public enum OrderStatus {
    PAYMENT_WAIT("결제대기중", true),
    DELIVERY_READY("배송준비중", true),
    DELIVERY_DOING("배송중", false),
    DELIVERY_DONE("배송완료", false),
    CANCEL("취소", false);

    private String desc;
    private boolean cancelOrder;

    OrderStatus(String desc, boolean cancelOrder) {
        this.desc = desc;
        this.cancelOrder = cancelOrder;
    }

    public boolean isCancelOrder() {
        return cancelOrder;
    }
}
