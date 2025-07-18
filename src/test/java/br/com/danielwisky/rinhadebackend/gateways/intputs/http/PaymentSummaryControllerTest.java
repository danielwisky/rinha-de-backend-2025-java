package br.com.danielwisky.rinhadebackend.gateways.intputs.http;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import br.com.danielwisky.rinhadebackend.supports.TestContainerSupport;
import br.com.danielwisky.rinhadebackend.templates.documents.PaymentDocumentTemplate;
import br.com.danielwisky.rinhadebackend.utils.JsonUtils;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@DisplayName("PaymentSummaryController Test")
class PaymentSummaryControllerTest extends TestContainerSupport {

  @Autowired
  private WebApplicationContext webAppContext;

  @Autowired
  private JsonUtils jsonUtils;

  private MockMvc mockMVC;

  @BeforeEach
  public void setUp() {
    mockMVC = webAppContextSetup(webAppContext).build();
    paymentMongoRepository.deleteAll();
  }

  @Test
  @DisplayName("should return payment summary successfully")
  void shouldReturnPaymentSummarySuccessfully() throws Exception {

    paymentMongoRepository.saveAll(List.of(PaymentDocumentTemplate.validDefaultOne(),
        PaymentDocumentTemplate.validDefaultTwo(),
        PaymentDocumentTemplate.validDefaultThree(),
        PaymentDocumentTemplate.validFallbackOne(),
        PaymentDocumentTemplate.validFallbackTwo()
    ));

    mockMVC.perform(get("/payments-summary")
            .param("from", "2025-01-01T00:00:00.000Z")
            .param("to", "2025-01-31T23:59:59.000Z")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.default.totalRequests").value(3))
        .andExpect(jsonPath("$.default.totalAmount").value(1840.74))
        .andExpect(jsonPath("$.fallback.totalRequests").value(2))
        .andExpect(jsonPath("$.fallback.totalAmount").value(425.75));
  }
}