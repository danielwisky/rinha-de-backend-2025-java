package br.com.danielwisky.rinhadebackend.templates.resources;

import br.com.danielwisky.rinhadebackend.gateways.outputs.kafka.resources.PaymentOutputResource;

public class PaymentOutputResourceTemplate {

  public static PaymentOutputResource valid() {
    final var resource = new PaymentOutputResource();
    resource.setCorrelationId("123456");
    resource.setAmount(100.0);
    return resource;
  }
}