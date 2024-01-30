package ba.edu.ibu.eventport.api.core.repository;

import ba.edu.ibu.eventport.api.core.model.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class FilterableEventRepositoryImpl {
  private final MongoTemplate mongoTemplate;

  public FilterableEventRepositoryImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  public Page<Event> findAll(
    String searchText,
    List<String> countries,
    List<String> cities,
    List<String> categories,
    String startDate,
    String endDate,
    Pageable pageable
  ) {
    Query query = new Query();

    if (searchText != null && !searchText.isEmpty()) {
      Criteria searchTextCriteria = new Criteria().orOperator(
        Criteria.where("name").regex(searchText, "i"),
        Criteria.where("description").regex(searchText, "i"),
        Criteria.where("geoLocation.iso2Code").regex(searchText, "i"),
        Criteria.where("geoLocation.country").regex(searchText, "i"),
        Criteria.where("geoLocation.city").regex(searchText, "i"),
        Criteria.where("venue").regex(searchText, "i")
      );

      query.addCriteria(searchTextCriteria);
    }

    if (countries != null && countries.size() > 0) {
      query.addCriteria(Criteria.where("geoLocation.iso2Code").in(countries));
    }

    if (cities != null && cities.size() > 0) {
      query.addCriteria(Criteria.where("geoLocation.city").in(cities));
    }

    if (categories != null && categories.size() > 0) {
      query.addCriteria(Criteria.where("category").in(categories));
    }

    if (startDate.length() > 0 && endDate.length() > 0) {
      query.addCriteria(
        Criteria.where("dateTime")
          .gte(LocalDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME))
          .lte(LocalDateTime.parse(endDate, DateTimeFormatter.ISO_DATE_TIME))
      );
    } else if (startDate.length() > 0) {
      query.addCriteria(Criteria.where("dateTime").gte(startDate));
    }

    query.with(pageable);

    List<Event> events = mongoTemplate.find(query, Event.class);
    long totalCount = mongoTemplate.count(query, Event.class);

    return new PageImpl<>(events, pageable, totalCount);
  }
}
