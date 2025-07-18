package br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import br.com.danielwisky.rinhadebackend.gateways.PaymentDataGateway;
import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.documents.PaymentDocument;
import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.repositories.PaymentMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentDataGatewayMongoImpl implements PaymentDataGateway {

  private final PaymentMongoRepository repository;

  @Override
  public Payment save(final Payment payment) {
    return repository.save(new PaymentDocument(payment)).toDomain();
  }
}