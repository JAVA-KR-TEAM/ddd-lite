package com.app.dddlite.domain;

import com.app.dddlite.exception.CustomException;
import com.app.dddlite.exception.ErrorMessage;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Order {
    /**
     * 주문(Order)은 Entity로 고유의 식별성을 가져야한다.
     */
    private String uuid;
    private OrderStatus orderStatus;
    private DeliveryAddress address;
    private PaymentType paymentType;
    private List<OrderLine> orderLines;


    public Order(DeliveryAddress address, List<OrderLine> orderLines) {
        this.uuid = UUID.randomUUID().toString();
        this.orderStatus = OrderStatus.PAYMENT_WAIT;
        this.address = address;
        this.orderLines = orderLines;
    }

    public void payment(PaymentType paymentType) throws CustomException {
        if(!OrderStatus.PAYMENT_WAIT.equals(this.orderStatus)){
            throw new CustomException(ErrorMessage.NOT_READY_FOR_PAYMENT);
        }

        this.paymentType = paymentType;
        this.orderStatus = OrderStatus.DELIVERY_READY;
    }

    public void delivery() throws CustomException {
        if(!OrderStatus.DELIVERY_READY.equals(this.orderStatus)){
            throw new CustomException(ErrorMessage.NOT_READY_FOR_PAYMENT);
        }

        this.orderStatus = OrderStatus.DELIVERY_DOING;
    }

    public void cancel() throws CustomException {
        if(!orderStatus.isCancelOrder()){
            throw new CustomException(ErrorMessage.NOT_CANCEL_ORDER);
        }

        this.orderStatus = OrderStatus.CANCEL;
    }
}
