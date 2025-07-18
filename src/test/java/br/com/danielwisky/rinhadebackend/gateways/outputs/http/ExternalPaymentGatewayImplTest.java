package br.com.danielwisky.rinhadebackend.gateways.outputs.http;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import br.com.danielwisky.rinhadebackend.gateways.outputs.http.client.PaymentClient;
import br.com.danielwisky.rinhadebackend.gateways.outputs.http.client.resources.request.PaymentRequest;
import br.com.danielwisky.rinhadebackend.supports.TestSupport;
import br.com.danielwisky.rinhadebackend.templates.domains.PaymentTemplate;
import br.com.danielwisky.rinhadebackend.templates.resources.PaymentResponseTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("ExternalPaymentGatewayImpl Test")
class ExternalPaymentGatewayImplTest extends TestSupport {

  @InjectMocks
  private ExternalPaymentGatewayImpl externalPaymentGateway;

  @Mock
  private PaymentClient paymentClient;

  @Test
  @DisplayName("should process payment successfully")
  void shouldProcessPaymentSuccessfully() {
    // Given
    when(paymentClient.payment(any(PaymentRequest.class)))
        .thenReturn(PaymentResponseTemplate.valid());
    final Payment payment = PaymentTemplate.valid();

    // When
    final Payment result = externalPaymentGateway.payment(payment);

    // Then
    assertNotNull(result);
  }
}