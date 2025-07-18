package br.com.danielwisky.rinhadebackend.supports;

import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

public class KafkaContainerConfiguration extends KafkaContainer {

  private static final String IMAGE_NAME = "apache/kafka";

  private static KafkaContainerConfiguration container;

  private KafkaContainerConfiguration() {
    super(DockerImageName.parse(IMAGE_NAME));
  }

  public static synchronized KafkaContainerConfiguration getInstance() {
    if (container == null) {
      container = new KafkaContainerConfiguration();
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("KAFKA_BOOTSTRAP_SERVERS", container.getBootstrapServers());
  }
}