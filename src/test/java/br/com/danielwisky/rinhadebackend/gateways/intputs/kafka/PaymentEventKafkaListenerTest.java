package br.com.danielwisky.rinhadebackend.gateways.intputs.kafka;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

import br.com.danielwisky.rinhadebackend.supports.TestContainerSupport;
import br.com.danielwisky.rinhadebackend.templates.resources.PaymentOutputResourceTemplate;
import br.com.danielwisky.rinhadebackend.templates.resources.PaymentResponseTemplate;
import br.com.danielwisky.rinhadebackend.utils.JsonUtils;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@DisplayName("PaymentEventKafkaListener Test")
class PaymentEventKafkaListenerTest extends TestContainerSupport {

  @Value("${spring.kafka.topics.payment-process}")
  private String topic;

  @Autowired
  private WebApplicationContext webAppContext;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private JsonUtils jsonUtils;

  private MockMvc mockMVC;

  @BeforeEach
  public void setUp() {
    mockServerClient.reset();
    mockMVC = webAppContextSetup(webAppContext).build();
    paymentMongoRepository.deleteAll();
  }

  @Test
  @DisplayName("should process payment event successfully")
  void shouldProcessPaymentEventSuccessfully() throws Exception {

    final var payment = PaymentOutputResourceTemplate.valid();
    final var externalPayment = PaymentResponseTemplate.valid();

    mockServerClient
        .when(request().withPath("/payments"))
        .respond(response().withStatusCode(200).withBody(json(externalPayment)));

    kafkaTemplate.send(topic, jsonUtils.toJson(payment));

    await()
        .atMost(Duration.ofSeconds(5))
        .untilAsserted(() -> assertThat(paymentMongoRepository.count()).isEqualTo(1));
  }
}