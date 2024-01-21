package ba.edu.ibu.eventport.api.core.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", builderMethodName = "Builder", setterPrefix = "with", toBuilder = true)
public class Ticket {
  private ObjectId id;
  private ObjectId eventId;
  private ObjectId userId;
  private String name;
  private String currency;
  private double price;
}
