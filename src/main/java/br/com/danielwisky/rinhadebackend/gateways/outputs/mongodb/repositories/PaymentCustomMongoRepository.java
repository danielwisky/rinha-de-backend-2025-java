package br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.repositories;

import br.com.danielwisky.rinhadebackend.domains.PaymentSummary;
import java.time.LocalDateTime;

public interface PaymentCustomMongoRepository {

  PaymentSummary getPaymentSummary(LocalDateTime from, LocalDateTime to);
}