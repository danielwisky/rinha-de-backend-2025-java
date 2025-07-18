package br.com.danielwisky.rinhadebackend.supports;

import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.repositories.PaymentMongoRepositoryImpl;
import org.mockserver.client.MockServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("container-test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class TestContainerSupport extends TestSupport {

  protected static final KafkaContainerConfiguration kafkaContainer;
  protected static final MongoDBContainerConfiguration mongoContainer;
  protected static final MockServerContainerConfiguration mockServerContainer;
  protected static final MockServerClient mockServerClient;

  static {
    kafkaContainer = KafkaContainerConfiguration.getInstance();
    kafkaContainer.start();
    mongoContainer = MongoDBContainerConfiguration.getInstance();
    mongoContainer.start();
    mockServerContainer = MockServerContainerConfiguration.getInstance();
    mockServerContainer.start();
    mockServerClient =
        new MockServerClient(mockServerContainer.getHost(), mockServerContainer.getServerPort());
  }

  @Autowired
  protected PaymentMongoRepositoryImpl paymentMongoRepository;
}