package ba.edu.ibu.eventport.api.core.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", builderMethodName = "Builder", setterPrefix = "with") public class Ticket {
  private String id;
  private String eventId;
  private String userId;
  private String type;
  private double price;
  private boolean isRegistered;
}
