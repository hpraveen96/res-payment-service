package com.egov.paymentservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentEvent {
    String paymentId;
    String reservationId;
    String phone;
    PaymentStatus status;

    public String toString() {
        return "PaymentEvent{" +
                "paymentId='" + paymentId + '\'' +
                ", reservationId='" + reservationId + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
