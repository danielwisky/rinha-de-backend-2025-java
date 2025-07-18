package br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.documents;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentSummaryDocument {

  private String processorType;
  private Integer totalRequests;
  private Double totalAmount;
}