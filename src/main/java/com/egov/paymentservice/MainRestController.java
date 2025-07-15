package com.egov.paymentservice;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MainRestController {
    private static final Logger logger = LoggerFactory.getLogger(MainRestController.class);


    @Autowired
    TokenService tokenService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("payments")
    public ResponseEntity<Payment> process(@RequestBody Payment payment) throws JsonProcessingException {
        return ResponseEntity.ok(paymentService.processPayment(payment));
    }

    @GetMapping("payments/{paymentId}")
    public ResponseEntity<Payment> getById(@PathVariable String paymentId) {
        return paymentService.getById(paymentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("payments/reservation/{reservationId}")
    public ResponseEntity<List<Payment>> getByReservation(@PathVariable String reservationId) {
        return ResponseEntity.ok(paymentService.getByReservation(reservationId));
    }


}
