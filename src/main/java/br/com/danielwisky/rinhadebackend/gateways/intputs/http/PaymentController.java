package br.com.danielwisky.rinhadebackend.gateways.intputs.http;

import static org.springframework.http.HttpStatus.ACCEPTED;

import br.com.danielwisky.rinhadebackend.gateways.PaymentEventGateway;
import br.com.danielwisky.rinhadebackend.gateways.intputs.http.resources.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

  private final PaymentEventGateway paymentEventGateway;

  @PostMapping
  @ResponseStatus(ACCEPTED)
  public ResponseEntity<Void> payment(@RequestBody final PaymentRequest paymentRequest) {
    log.debug("payment request: {}", paymentRequest);
    paymentEventGateway.process(paymentRequest.toDomain());
    return ResponseEntity.accepted().build();
  }
}