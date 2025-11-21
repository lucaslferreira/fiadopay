package edu.ucsal.fiadopay.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CreditCardStrategy implements PaymentStrategy {
    
    @Override
    public PaymentResult process(BigDecimal amount, Integer installments) {
        Double interest = null;
        BigDecimal total = amount;
        
        if (installments != null && installments > 1) {
            interest = 1.0;
            var base = new BigDecimal("1.01");
            var factor = base.pow(installments);
            total = amount.multiply(factor).setScale(2, RoundingMode.HALF_UP);
        }
        
        return new PaymentResult(total, interest);
    }
}