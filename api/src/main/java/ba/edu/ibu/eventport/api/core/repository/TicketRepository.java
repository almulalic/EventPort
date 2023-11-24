package ba.edu.ibu.eventport.api.core.repository;

import ba.edu.ibu.eventport.api.core.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
  Ticket findFirstByIdAndRegisteredIsFalse(String id);
}
