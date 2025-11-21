package edu.ucsal.fiadopay.strategy;

import java.math.BigDecimal;

public interface PaymentStrategy {
    PaymentResult process(BigDecimal amount, Integer installments);
}