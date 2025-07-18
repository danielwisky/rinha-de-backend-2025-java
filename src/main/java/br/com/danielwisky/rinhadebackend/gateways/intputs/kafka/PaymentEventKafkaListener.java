package br.com.danielwisky.rinhadebackend.gateways.intputs.kafka;

import br.com.danielwisky.rinhadebackend.gateways.intputs.kafka.resources.PaymentInputResource;
import br.com.danielwisky.rinhadebackend.usecases.ProcessPayment;
import br.com.danielwisky.rinhadebackend.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEventKafkaListener {

  private final ProcessPayment processPayment;
  private final JsonUtils jsonUtils;

  @KafkaListener(topics = "${spring.kafka.topics.payment-process}")
  public void receive(final String message) {
    try {
      log.debug("reading the message: {}", message);
      final var resource = jsonUtils.toObject(message, PaymentInputResource.class);
      processPayment.execute(resource.toDomain());
    } catch (Exception ex) {
      log.error("error processing message: {}", message, ex);
    }
  }
}