package br.com.danielwisky.rinhadebackend.templates.documents;

import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.documents.PaymentDocument;
import java.time.LocalDateTime;

public class PaymentDocumentTemplate {

  public static PaymentDocument validDefaultOne() {
    final var payment = new PaymentDocument();
    payment.setAmount(250.75);
    payment.setProcessorType("DEFAULT");
    payment.setCorrelationId("corr-default-001");
    payment.setCreatedDate(LocalDateTime.of(2025, 1, 15, 10, 30, 0));
    return payment;
  }

  public static PaymentDocument validDefaultTwo() {
    final var payment = new PaymentDocument();
    payment.setAmount(89.99);
    payment.setProcessorType("DEFAULT");
    payment.setCorrelationId("corr-default-002");
    payment.setCreatedDate(LocalDateTime.of(2025, 1, 15, 14, 45, 30));
    return payment;
  }

  public static PaymentDocument validDefaultThree() {
    final var payment = new PaymentDocument();
    payment.setAmount(1500.00);
    payment.setProcessorType("DEFAULT");
    payment.setCorrelationId("corr-default-003");
    payment.setCreatedDate(LocalDateTime.of(2025, 1, 15, 16, 20, 15));
    return payment;
  }

  public static PaymentDocument validFallbackOne() {
    final var payment = new PaymentDocument();
    payment.setAmount(350.50);
    payment.setProcessorType("FALLBACK");
    payment.setCorrelationId("corr-fallback-001");
    payment.setCreatedDate(LocalDateTime.of(2025, 1, 15, 11, 15, 45));
    return payment;
  }

  public static PaymentDocument validFallbackTwo() {
    final var payment = new PaymentDocument();
    payment.setAmount(75.25);
    payment.setProcessorType("FALLBACK");
    payment.setCorrelationId("corr-fallback-002");
    payment.setCreatedDate(LocalDateTime.of(2025, 1, 15, 18, 0, 0));
    return payment;
  }
}