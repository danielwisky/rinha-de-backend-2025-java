package br.com.danielwisky.rinhadebackend.templates.resources;

import br.com.danielwisky.rinhadebackend.gateways.intputs.http.resources.request.PaymentRequest;

public class PaymentRequestTemplate {

  public static PaymentRequest valid() {
    final var request = new PaymentRequest();
    request.setAmount(100.0);
    request.setCorrelationId("12345");
    return request;
  }
}