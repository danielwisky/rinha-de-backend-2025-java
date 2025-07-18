package br.com.danielwisky.rinhadebackend.gateways.outputs.http;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import br.com.danielwisky.rinhadebackend.gateways.ExternalPaymentGateway;
import br.com.danielwisky.rinhadebackend.gateways.outputs.http.client.PaymentClient;
import br.com.danielwisky.rinhadebackend.gateways.outputs.http.client.resources.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExternalPaymentGatewayImpl implements ExternalPaymentGateway {

  private final PaymentClient paymentClient;

  @Override
  public Payment payment(final Payment payment) {
    paymentClient.payment(new PaymentRequest(payment));
    return payment;
  }
}