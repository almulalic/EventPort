package ba.edu.ibu.eventport.core.repository;

import java.util.List;

import ba.edu.ibu.eventport.core.model.Event;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {
  @Aggregation(
    pipeline = {
      "{ $match: { organizerName: ?0 } }",
      "{ $project: { organizerName: 1, participants: 1, capacity: 1, participantCount: { $size: '$participants' } } }",
      "{ $match: { participantCount: { $lt: '$capacity' } } }"
    }
  )
  List<Event> findAllByOrganizerAndAvailable(String email, String username);
}