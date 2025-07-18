package br.com.danielwisky.rinhadebackend.supports;

public class MongoDBContainerConfiguration extends MongoDBContainer {

  private static final String DOCKER_IMAGE = "latest";
  private static final String MONGO_DB_NAME = "rinhadebackend";

  private static MongoDBContainerConfiguration container;

  public MongoDBContainerConfiguration() {
    super(DOCKER_IMAGE, MONGO_DB_NAME);
  }

  public static MongoDBContainerConfiguration getInstance() {
    if (container == null) {
      container = new MongoDBContainerConfiguration();
    }

    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("MONGODB_URI", container.getMongoDbUri());
  }
}