package br.com.danielwisky.rinhadebackend.usecases;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.danielwisky.rinhadebackend.gateways.ExternalPaymentGateway;
import br.com.danielwisky.rinhadebackend.gateways.PaymentDataGateway;
import br.com.danielwisky.rinhadebackend.supports.TestSupport;
import br.com.danielwisky.rinhadebackend.templates.domains.PaymentTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("ProcessPayment Use Case Test")
class ProcessPaymentTest extends TestSupport {

  @InjectMocks
  private ProcessPayment processPayment;

  @Mock
  private PaymentDataGateway paymentDataGateway;

  @Mock
  private ExternalPaymentGateway externalPaymentGateway;

  @Test
  @DisplayName("should process payment successfully")
  void shouldProcessPaymentSuccessfully() {
    // Given
    final var payment = PaymentTemplate.valid();
    when(externalPaymentGateway.payment(payment)).thenReturn(payment);

    // When
    processPayment.execute(payment);

    // Then
    verify(paymentDataGateway, times(1)).save(payment);
  }
}