package com.egov.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PaymentServiceApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }

}
