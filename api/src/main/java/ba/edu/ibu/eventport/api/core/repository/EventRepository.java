package ba.edu.ibu.eventport.api.core.repository;

import java.util.List;

import ba.edu.ibu.eventport.api.core.model.event.Event;
import ba.edu.ibu.eventport.api.rest.models.dto.EventTicket;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends MongoRepository<Event, String>,
                                         PagingAndSortingRepository<Event, String> {
  @Aggregation(
    pipeline = {
      "{ $match: { organizerName: ?0 } }",
      "{ $project: { organizerName: 1, participants: 1, capacity: 1, participantCount: { $size: '$participants' } } }",
      "{ $match: { participantCount: { $lt: '$capacity' } } }"
    }
  )
  List<Event> findByCategory(String category);

  @Query("{'likedBy': ?0}")
  List<Event> findUserLikedEvents(String userId);
}