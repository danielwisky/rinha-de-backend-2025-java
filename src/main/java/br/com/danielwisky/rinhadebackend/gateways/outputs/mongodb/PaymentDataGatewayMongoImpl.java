package br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import br.com.danielwisky.rinhadebackend.domains.PaymentSummary;
import br.com.danielwisky.rinhadebackend.gateways.PaymentDataGateway;
import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.documents.PaymentDocument;
import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.repositories.PaymentMongoRepositoryImpl;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentDataGatewayMongoImpl implements PaymentDataGateway {

  private final PaymentMongoRepositoryImpl repository;

  @Override
  public Payment save(final Payment payment) {
    return repository.save(new PaymentDocument(payment)).toDomain();
  }

  @Override
  public PaymentSummary getPaymentSummary(final LocalDateTime from, final LocalDateTime to) {
    return repository.getPaymentSummary(from, to);
  }
}