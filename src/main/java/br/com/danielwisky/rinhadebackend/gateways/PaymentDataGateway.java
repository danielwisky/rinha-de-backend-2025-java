package br.com.danielwisky.rinhadebackend.gateways;

import br.com.danielwisky.rinhadebackend.domains.Payment;

public interface PaymentDataGateway {

  Payment save(Payment payment);
}
