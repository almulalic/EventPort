package ba.edu.ibu.eventport.api.rest.models.dto;

import ba.edu.ibu.eventport.api.core.model.event.Event;
import ba.edu.ibu.eventport.api.core.model.event.TicketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "Builder", builderClassName = "Builder", setterPrefix = "with")
public class EventTicket {
  private Event event;
  private TicketType ticketType;
}
