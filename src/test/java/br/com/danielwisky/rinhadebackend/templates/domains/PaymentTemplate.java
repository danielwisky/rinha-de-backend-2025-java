package br.com.danielwisky.rinhadebackend.templates.domains;

import static br.com.danielwisky.rinhadebackend.domains.enums.ProcessorType.DEFAULT;
import static java.time.LocalDateTime.now;

import br.com.danielwisky.rinhadebackend.domains.Payment;

public class PaymentTemplate {

  public static Payment valid() {
    return Payment.builder()
        .id("1234567890")
        .amount(100.0)
        .processorType(DEFAULT)
        .correlationId("correlation-id-123")
        .createdAt(now())
        .build();
  }
}