package com.app.dddlite;

import com.app.dddlite.domain.*;
import com.app.dddlite.exception.CustomException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ApplicationTests {

    @Test
    public void order() throws CustomException {
        // 고객이 상품을 주문한다.
        Order order = createOrderByCard();
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.PAYMENT_WAIT);

        // 결제한다.
        PaymentType paymentType = PaymentType.CARD;
        order.payment(paymentType);
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.DELIVERY_READY);
        assertThat(order.getPaymentType()).isEqualTo(paymentType);

        // 결제가 완료 되면 배송을 시작한다.
        order.delivery();
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.DELIVERY_DOING);
    }

    @Test
    public void orderCancel() throws CustomException {
        // 고객이 상품을 주문한다.
        Order order = createOrderByCard();
        // 취소한다.
        order.cancel();
        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
    }

    @Test
    public void orderCancelWhenDelivery() throws CustomException {
        // 고객이 상품을 주문한다.
        Order order = createOrderByCard();
        PaymentType paymentType = PaymentType.MOBILE;
        order.payment(paymentType);
        order.delivery();
        // 배송 상태가 배송 중이라면 결제를 취소할 수 없다.
        assertThrows(CustomException.class, order::cancel);
    }

    private Order createOrderByCard() {
        List<OrderLine> orderLines = createOrderLines(2);
        DeliveryAddress deliveryAddress = createDeliveryAddress();

        return new Order(deliveryAddress, orderLines);
    }

    private List<OrderLine> createOrderLines(int count) {
        List<OrderLine> orderLines = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Product product = new Product(UUID.randomUUID().toString(), "상품명" + i, 10000 * i);
            orderLines.add(new OrderLine(i, product));
        }
        return orderLines;
    }

    public DeliveryAddress createDeliveryAddress() {
        return DeliveryAddress.builder()
                .receiver("test")
                .receiveAddr("seoul")
                .receiveAddrDetail("korea")
                .build();
    }

}
