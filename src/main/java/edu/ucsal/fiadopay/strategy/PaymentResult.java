package edu.ucsal.fiadopay.strategy;

import java.math.BigDecimal;

public record PaymentResult(BigDecimal total, Double interest) {}