package br.com.danielwisky.rinhadebackend.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Summary {

  private Integer totalRequests;
  private Double totalAmount;
}