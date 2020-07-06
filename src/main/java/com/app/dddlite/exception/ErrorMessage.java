package com.app.dddlite.exception;

public enum ErrorMessage {
    NOT_READY_FOR_PAYMENT("결제할 수 있는 상태가 아닙니다."),
    NOT_CANCEL_ORDER("주문을 취소할 수 있는 상태가 아닙니다.");

    private String message;

    ErrorMessage(String msg) {
        this.message = msg;
    }

    public String getMessage() {
        return message;
    }
}
