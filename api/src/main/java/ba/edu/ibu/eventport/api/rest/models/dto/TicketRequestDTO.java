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
public class TicketRequestDTO {
  private ObjectId eventId;
  private ObjectId userId;
  private String name;
  private String currency;
  private double price;

  public Ticket toEntity() {
    return Ticket.Builder()
             .withEventId(eventId)
             .withUserId(userId)
             .withName(name)
             .withCurrency(currency)
             .withPrice(price)
             .build();
  }
}
