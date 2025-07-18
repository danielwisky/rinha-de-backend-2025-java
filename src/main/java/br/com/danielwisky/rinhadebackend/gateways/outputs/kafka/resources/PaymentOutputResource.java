package br.com.danielwisky.rinhadebackend.gateways.outputs.kafka.resources;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOutputResource implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String correlationId;
  private Double amount;

  public PaymentOutputResource(final Payment payment) {
    this.correlationId = payment.getCorrelationId();
    this.amount = payment.getAmount();
  }
}