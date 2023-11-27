package ba.edu.ibu.eventport.api.rest.models.dto;

import ba.edu.ibu.eventport.api.core.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", builderMethodName = "Builder", setterPrefix = "with")
public class TicketRequestDTO {
  private String eventId;
  private String userId;
  private String type;
  private double price;
  private boolean isRegistered;

  public Ticket toEntity() {
    return Ticket.Builder()
             .withEventId(eventId)
             .withUserId(userId)
             .withType(type)
             .withPrice(price)
             .withIsRegistered(isRegistered)
             .build();
  }
}
