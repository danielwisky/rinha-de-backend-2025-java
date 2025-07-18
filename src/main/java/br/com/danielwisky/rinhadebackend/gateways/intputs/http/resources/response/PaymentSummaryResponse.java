package br.com.danielwisky.rinhadebackend.gateways.intputs.http.resources.response;

import static java.util.Optional.ofNullable;

import br.com.danielwisky.rinhadebackend.domains.PaymentSummary;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class PaymentSummaryResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @JsonProperty("default")
  private SummaryResponse defaultSummary;
  @JsonProperty("fallback")
  private SummaryResponse fallbackSummary;

  public PaymentSummaryResponse(final PaymentSummary paymentSummary) {
    this.defaultSummary = ofNullable(paymentSummary.getDefaultSummary())
        .map(SummaryResponse::new)
        .orElse(null);
    this.fallbackSummary = ofNullable(paymentSummary.getFallbackSummary())
        .map(SummaryResponse::new)
        .orElse(null);
  }
}