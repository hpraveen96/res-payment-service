package com.egov.paymentservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    Producer producer;

    public Payment processPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentDate(LocalDate.now());

        // Mocking payment logic — you can later call a real gateway
        if (payment.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            payment.setStatus(PaymentStatus.COMPLETED);
            Payment newPayment = paymentRepository.save(payment);

            PaymentEvent paymentEvent = new PaymentEvent();
            paymentEvent.setPaymentId(newPayment.getPaymentId());
            paymentEvent.setPhone(newPayment.getCustomerPhone());
            paymentEvent.setReservationId(newPayment.getReservationId());
            paymentEvent.setStatus(newPayment.getStatus());

            producer.pubProjectEvent(paymentEvent);


        } else {
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
        }

        return paymentRepository.save(payment);
    }

    public List<Payment> getByReservation(String reservationId) {
        return paymentRepository.findByReservationId(reservationId);
    }

    public Optional<Payment> getById(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

}
