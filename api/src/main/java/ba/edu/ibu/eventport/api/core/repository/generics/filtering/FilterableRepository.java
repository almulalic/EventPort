package ba.edu.ibu.eventport.api.core.repository.generics.filtering;

import ba.edu.ibu.eventport.api.core.repository.generics.filtering.models.Filter;
import ba.edu.ibu.eventport.api.core.repository.generics.filtering.models.Filtering;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface FilterableRepository<T> {
  Page<T> findAllWithFilter(Class<T> typeParameterClass, Filtering filtering, Pageable pageable);

  List<Object> getAllPossibleValuesForFilter(Class<T> typeParameterClass, Filtering filtering, String filterKey);

  default Query constructQueryFromFiltering(Filtering filtering) {
    Query query = new Query();
    Map<String, Criteria> criteriaMap = new HashMap<>();
    for (Filter filter : filtering.getFilterList()) {
      switch (filter.getOperator()) {
        case eq -> criteriaMap.put(filter.getKey(), Criteria.where(filter.getKey()).is(filter.getValue()));
        case gt -> {
          if (criteriaMap.containsKey(filter.getKey())) {
            criteriaMap.get(filter.getKey()).gt(filter.getValue());
          } else {
            criteriaMap.put(filter.getKey(), Criteria.where(filter.getKey()).gt(filter.getValue()));
          }
        }
        case gte -> {
          if (criteriaMap.containsKey(filter.getKey())) {
            criteriaMap.get(filter.getKey()).gte(filter.getValue());
          } else {
            criteriaMap.put(filter.getKey(), Criteria.where(filter.getKey()).gte(filter.getValue()));
          }
        }
        case in -> criteriaMap.put(
          filter.getKey(),
          Criteria.where(filter.getKey()).in((HashSet<Object>) filter.getValue())
        );
        case lt -> {
          if (criteriaMap.containsKey(filter.getKey())) {
            criteriaMap.get(filter.getKey()).lt(filter.getValue());
          } else {
            criteriaMap.put(filter.getKey(), Criteria.where(filter.getKey()).lt(filter.getValue()));
          }
        }
        case lte -> {
          if (criteriaMap.containsKey(filter.getKey())) {
            criteriaMap.get(filter.getKey()).lte(filter.getValue());
          } else {
            criteriaMap.put(filter.getKey(), Criteria.where(filter.getKey()).lte(filter.getValue()));
          }
        }
        case ne -> criteriaMap.put(filter.getKey(), Criteria.where(filter.getKey()).ne(filter.getValue()));
        case nin -> criteriaMap.put(
          filter.getKey(),
          Criteria.where(filter.getKey()).nin((HashSet<Object>) filter.getValue())
        );
        case regex -> criteriaMap.put(
          filter.getKey(),
          Criteria.where(filter.getKey()).regex((String) filter.getValue())
        );
        default -> throw new IllegalArgumentException("Unknown operator: " + filter.getOperator());
      }
    }
    criteriaMap.values().forEach(query::addCriteria);
    return query;
  }
}
