package ba.edu.ibu.eventport.api.core.repository;

import java.util.List;

import ba.edu.ibu.eventport.api.core.model.Event;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import ba.edu.ibu.eventport.api.core.repository.generics.filtering.FilterableRepository;

public interface EventRepository extends MongoRepository<Event, String>,
                                         FilterableRepository<Event> {
  @Aggregation(
    pipeline = {
      "{ $match: { organizerName: ?0 } }",
      "{ $project: { organizerName: 1, participants: 1, capacity: 1, participantCount: { $size: '$participants' } } }",
      "{ $match: { participantCount: { $lt: '$capacity' } } }"
    }
  )
  List<Event> findByCategory(String category);
}