package br.com.danielwisky.rinhadebackend.gateways.outputs.kafka;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import br.com.danielwisky.rinhadebackend.gateways.PaymentEventGateway;
import br.com.danielwisky.rinhadebackend.gateways.outputs.kafka.resources.PaymentOutputResource;
import br.com.danielwisky.rinhadebackend.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentKafkaGatewayImpl implements PaymentEventGateway {

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final JsonUtils jsonUtils;

  @Value("${spring.kafka.topics.payment-process}")
  private String paymentProcessTopic;

  @Override
  public void process(final Payment payment) {
    final var paymentOutputResource = new PaymentOutputResource(payment);
    kafkaTemplate.send(paymentProcessTopic, jsonUtils.toJson(paymentOutputResource));
  }
}