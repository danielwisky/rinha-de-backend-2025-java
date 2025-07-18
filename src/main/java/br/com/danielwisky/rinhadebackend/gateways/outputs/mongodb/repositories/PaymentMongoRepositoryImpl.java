package br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.repositories;

import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.documents.PaymentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentMongoRepositoryImpl
    extends MongoRepository<PaymentDocument, String>, PaymentCustomMongoRepository {

}