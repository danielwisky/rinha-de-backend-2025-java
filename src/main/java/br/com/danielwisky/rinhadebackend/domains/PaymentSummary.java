package br.com.danielwisky.rinhadebackend.domains;

import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.documents.PaymentSummaryDocument;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentSummary {

  private Summary defaultSummary;
  private Summary fallbackSummary;

  public PaymentSummary(List<PaymentSummaryDocument> aggregationResults) {
    this.defaultSummary = new Summary();
    this.fallbackSummary = new Summary();
    populateSummaries(aggregationResults);
  }

  private void populateSummaries(List<PaymentSummaryDocument> results) {
    for (PaymentSummaryDocument result : results) {
      if ("DEFAULT".equals(result.getProcessorType())) {
        this.defaultSummary.setTotalRequests(result.getTotalRequests());
        this.defaultSummary.setTotalAmount(result.getTotalAmount());
      } else if ("FALLBACK".equals(result.getProcessorType())) {
        this.fallbackSummary.setTotalRequests(result.getTotalRequests());
        this.fallbackSummary.setTotalAmount(result.getTotalAmount());
      }
    }
  }
}