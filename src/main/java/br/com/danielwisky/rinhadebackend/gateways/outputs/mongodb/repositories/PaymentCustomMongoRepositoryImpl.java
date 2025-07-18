package br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.repositories;

import static br.com.danielwisky.rinhadebackend.utils.CriteriaUtils.addGreaterThanOrEqualToIfNotNull;
import static br.com.danielwisky.rinhadebackend.utils.CriteriaUtils.addLessThanOrEqualToIfNotNull;
import static br.com.danielwisky.rinhadebackend.utils.CriteriaUtils.reduceWithAndOperator;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import br.com.danielwisky.rinhadebackend.domains.PaymentSummary;
import br.com.danielwisky.rinhadebackend.gateways.outputs.mongodb.documents.PaymentSummaryDocument;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentCustomMongoRepositoryImpl implements PaymentCustomMongoRepository {

  private final MongoTemplate mongoTemplate;

  @Override
  public PaymentSummary getPaymentSummary(final LocalDateTime from, final LocalDateTime to) {
    final var criterion = new ArrayList<Criteria>();
    addGreaterThanOrEqualToIfNotNull(criterion, "createdDate", from);
    addLessThanOrEqualToIfNotNull(criterion, "createdDate", to);
    final var criteria = reduceWithAndOperator(criterion);
    final var aggregation = buildAggregation(criteria);
    final var aggregationResults =
        mongoTemplate.aggregate(aggregation, "payments", PaymentSummaryDocument.class);
    return new PaymentSummary(aggregationResults.getMappedResults());
  }

  private Aggregation buildAggregation(final Criteria criteria) {
    final var matchOperation = Aggregation.match(criteria);
    final var groupByProcessorType = group("processorType")
        .count().as("totalRequests")
        .sum("amount").as("totalAmount");
    final var project = project("totalRequests", "totalAmount")
        .and("_id")
        .as("processorType");
    return newAggregation(matchOperation, groupByProcessorType, project);
  }
}