package ba.edu.ibu.eventport.api.core.model.event;

import ba.edu.ibu.eventport.api.core.model.event.GeoLocation;
import lombok.*;
import org.springframework.data.annotation.Id;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
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
  private int likes;
  private LocalDateTime dateTime;
  private String venue;
  private GeoLocation geoLocation;
  private String googleMapsLink;
  private List<String> participants;
  private EventStatus status;
  private String category;
  private int capacity;
  private LocalDateTime registrationDeadline;
  private String bannerImageURL;
  private String priceRange;
  private List<TicketType> ticketTypes;
}
