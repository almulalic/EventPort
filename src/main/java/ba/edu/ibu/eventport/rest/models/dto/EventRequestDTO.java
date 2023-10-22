package ba.edu.ibu.eventport.rest.models.dto;

import ba.edu.ibu.eventport.core.model.Event;
import ba.edu.ibu.eventport.core.model.enums.EventStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventRequestDTO {
  private String name;
  private String description;
  private Date dateTime;
  private String location;
  private String organizer;
  private List<Integer> participants = new ArrayList<>();
  private String type;
  private EventStatus status;
  private int capacity;
  private Date registrationDeadline;
  private String bannerImageURL;

  public Event toEntity() {
    Event event = new Event();
    event.setName(name);
    event.setDescription(description);
    event.setDateTime(dateTime);
    event.setLocation(location);
    event.setOrganizer(organizer);
    event.setParticipants(participants);
    event.setType(type);
    event.setStatus(status);
    event.setCapacity(capacity);
    event.setRegistrationDeadline(registrationDeadline);
    event.setBannerImageURL(bannerImageURL);
    return event;
  }

}
