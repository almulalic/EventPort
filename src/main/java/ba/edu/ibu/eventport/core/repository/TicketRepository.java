package ba.edu.ibu.eventport.core.repository;

import ba.edu.ibu.eventport.core.model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, String> {
  Ticket findFirstByIdAndRegisteredIsFalse(String id);
}
