package br.com.danielwisky.rinhadebackend.gateways.outputs.kafka;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import br.com.danielwisky.rinhadebackend.supports.TestSupport;
import br.com.danielwisky.rinhadebackend.templates.domains.PaymentTemplate;
import br.com.danielwisky.rinhadebackend.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

@DisplayName("PaymentKafkaGatewayImpl Test")
class PaymentKafkaGatewayImplTest extends TestSupport {

  @InjectMocks
  private PaymentKafkaGatewayImpl paymentKafkaGateway;

  @Mock
  private KafkaTemplate<String, String> kafkaTemplate;

  @Mock
  private JsonUtils jsonUtils;

  @BeforeEach
  void init() {
    ReflectionTestUtils.setField(paymentKafkaGateway, "paymentProcessTopic", "topic");
  }

  @Test
  @DisplayName("should process payment successfully")
  void shouldProcessPaymentSuccessfully() {
    // Given
    when(jsonUtils.toJson(any())).thenReturn("{}");
    final Payment payment = PaymentTemplate.valid();

    // When
    paymentKafkaGateway.process(payment);

    // Then
    verify(kafkaTemplate, Mockito.times(1)).send(Mockito.anyString(), Mockito.anyString());
  }
}