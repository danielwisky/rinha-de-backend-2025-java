package br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.documents.PaymentDocument;
import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.repositories.PaymentMongoRepository;
import br.com.danielwisky.rinhadebackend.supports.TestSupport;
import br.com.danielwisky.rinhadebackend.templates.domains.PaymentTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("PaymentDataGatewayMongoImpl Test")
class PaymentDataGatewayMongoImplTest extends TestSupport {

  @InjectMocks
  private PaymentDataGatewayMongoImpl paymentDataGatewayMongo;

  @Mock
  private PaymentMongoRepository paymentMongoRepository;

  @Test
  @DisplayName("should save payment successfully")
  void shouldSavePaymentSuccessfully() {
    // Given
    final Payment payment = PaymentTemplate.valid();
    when(paymentMongoRepository.save(any())).thenReturn(new PaymentDocument(payment));

    // When
    final Payment paymentSaved = paymentDataGatewayMongo.save(payment);

    // Then
    assertEquals(payment, paymentSaved);
  }
}