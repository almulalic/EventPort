package ba.edu.ibu.eventport.api.rest.models.dto;

import ba.edu.ibu.eventport.api.core.model.Event;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventViewDTO {
  private String id;
  private String name;
  private String description;
  private String bannerImageURL;
  private Date dateTime;
  private String location;
  private String organizer;
  private String type;
  private EventStatus status;
  private Date registrationDeadline;

  public EventViewDTO(Event event) {
    this.id = event.getId();
    this.name = event.getName();
    this.description = event.getDescription();
    this.bannerImageURL = event.getBannerImageURL();
    this.dateTime = event.getDateTime();
    this.location = event.getLocation();
    this.type = event.getType();
    this.status = event.getStatus();
    this.registrationDeadline = event.getRegistrationDeadline();
  }
}
