package br.com.danielwisky.rinhadebackend.utils;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.mongodb.core.query.Criteria;

public class CriteriaUtils {

  public static void addGreaterThanOrEqualToIfNotNull(
      final List<Criteria> criterion,
      final String key,
      final LocalDateTime value) {
    if (Objects.nonNull(value)) {
      criterion.add(where(key).gte(value));
    }
  }

  public static void addLessThanOrEqualToIfNotNull(
      final List<Criteria> criterion,
      final String key,
      final LocalDateTime value) {
    if (Objects.nonNull(value)) {
      criterion.add(where(key).lte(value));
    }
  }

  public static Criteria reduceWithAndOperator(final List<Criteria> criterion) {
    return CollectionUtils.isEmpty(criterion)
        ? new Criteria()
        : new Criteria().andOperator(criterion.toArray(new Criteria[0]));
  }
}
