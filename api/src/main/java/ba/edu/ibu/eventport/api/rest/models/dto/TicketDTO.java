package ba.edu.ibu.eventport.api.rest.models.dto;

import ba.edu.ibu.eventport.api.core.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", builderMethodName = "Builder", setterPrefix = "with")
public class TicketDTO {
  private ObjectId id;
  private ObjectId eventId;
  private ObjectId userId;
  private String name;
  private String currency;
  private double price;

  public TicketDTO(Ticket ticket) {
    this.id = ticket.getId();
    this.eventId = ticket.getEventId();
    this.userId = ticket.getUserId();
    this.name = ticket.getName();
    this.currency = ticket.getCurrency();
    this.price = ticket.getPrice();
  }
}
