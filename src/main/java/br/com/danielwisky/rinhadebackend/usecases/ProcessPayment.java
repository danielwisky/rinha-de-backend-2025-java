package br.com.danielwisky.rinhadebackend.usecases;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import br.com.danielwisky.rinhadebackend.gateways.ExternalPaymentGateway;
import br.com.danielwisky.rinhadebackend.gateways.PaymentDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProcessPayment {

  private final PaymentDataGateway paymentDataGateway;
  private final ExternalPaymentGateway externalPaymentGateway;

  public void execute(final Payment payment) {
    final var externalPayment = externalPaymentGateway.payment(payment);
    paymentDataGateway.save(externalPayment);
  }
}