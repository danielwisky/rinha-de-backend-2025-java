package br.com.danielwisky.rinhadebackend.gateways.intputs.kafka.resources;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInputResource implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String correlationId;
  private Double amount;

  public Payment toDomain() {
    return Payment.builder()
        .correlationId(this.correlationId)
        .amount(this.amount)
        .build();
  }
}