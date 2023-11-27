package ba.edu.ibu.eventport.api.rest.models.dto;

import ba.edu.ibu.eventport.api.core.model.Event;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "Builder", builderClassName = "Builder", setterPrefix = "with")
public class EventRequestDTO {
  private String name;
  private String description;
  private Date dateTime;
  private String location;
  private List<Integer> participants = new ArrayList<>();
  private String type;
  private EventStatus status;
  private int capacity;
  private Date registrationDeadline;
  private String bannerImageURL;

  public Event toEntity() {
    return Event.Builder()
             .withName(name)
             .withDescription(description)
             .withDateTime(dateTime)
             .withLocation(location)
             .withParticipants(participants)
             .withType(type)
             .withStatus(status)
             .withCapacity(capacity)
             .withRegistrationDeadline(registrationDeadline)
             .withBannerImageURL(bannerImageURL)
             .build();
  }
}
