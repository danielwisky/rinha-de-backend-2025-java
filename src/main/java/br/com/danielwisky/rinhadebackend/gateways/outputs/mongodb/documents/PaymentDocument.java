package br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.documents;

import static java.util.Optional.ofNullable;

import br.com.danielwisky.rinhadebackend.domains.Payment;
import br.com.danielwisky.rinhadebackend.domains.enums.ProcessorType;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "payments")
public class PaymentDocument {

  @Id
  private String id;
  private Double amount;
  @Indexed
  private String processorType;
  @CreatedDate
  private LocalDateTime createdDate;

  public PaymentDocument(final Payment payment) {
    this.id = payment.getId();
    this.amount = payment.getAmount();
    this.processorType = ofNullable(payment.getProcessorType())
        .map(Enum::name)
        .orElse(null);
    this.createdDate = payment.getCreatedAt();
  }

  public Payment toDomain() {
    return Payment.builder()
        .id(this.id)
        .amount(this.amount)
        .processorType(ofNullable(this.processorType)
            .map(ProcessorType::valueOf)
            .orElse(null))
        .createdAt(this.createdDate)
        .build();
  }
}
