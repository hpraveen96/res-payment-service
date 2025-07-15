package com.egov.paymentservice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Document(collection = "payments")
public class Payment {

    @Id
    private String paymentId;
    private String reservationId;
    private String customerPhone;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String paymentMethod; // CARD, UPI, WALLET
    private PaymentStatus status;

}