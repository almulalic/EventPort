package ba.edu.ibu.eventport.api.core.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "Builder", builderClassName = "Builder", setterPrefix = "with")
public class Event {
  @Id
  private String id;
  private String name;
  private String description;
  private Date dateTime;
  private String location;
  private List<Integer> participants;
  private String type;
  private EventStatus status;
  private int capacity;
  private Date registrationDeadline;
  private String bannerImageURL;
}
