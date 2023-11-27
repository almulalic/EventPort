package ba.edu.ibu.eventport.api.rest.models.dto;

import ba.edu.ibu.eventport.api.core.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", builderMethodName = "Builder", setterPrefix = "with") public class TicketDTO {
  private String id;
  private String eventId;
  private String userId;
  private String type;
  private double price;
  private boolean isRegistered;

  public TicketDTO(Ticket ticket) {
    this.id = ticket.getId();
    this.eventId = ticket.getEventId();
    this.userId = ticket.getUserId();
    this.type = ticket.getType();
    this.price = ticket.getPrice();
    this.isRegistered = ticket.isRegistered();
  }
}
