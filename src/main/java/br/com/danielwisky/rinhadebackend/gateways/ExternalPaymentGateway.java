package br.com.danielwisky.rinhadebackend.gateways;

import br.com.danielwisky.rinhadebackend.domains.Payment;

public interface ExternalPaymentGateway {

  Payment payment(Payment payment);
}
