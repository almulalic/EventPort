package ba.edu.ibu.eventport.api.rest.models.dto;

import ba.edu.ibu.eventport.api.core.model.event.Event;
import ba.edu.ibu.eventport.api.core.model.event.GeoLocation;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;
import ba.edu.ibu.eventport.api.core.model.event.TicketType;
import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventViewDTO {
  private String id;
  private String name;
  private String description;
  private String bannerImageURL;
  private LocalDateTime dateTime;
  private GeoLocation geoLocation;
  private List<String> participants;
  private String venue;
  private String organizer;
  private String type;
  private EventStatus status;
  private LocalDateTime registrationDeadline;
  private List<TicketType> ticketTypes;
  private String googleMapsLink;
  private List<String> likedBy;

  public EventViewDTO(Event event) {
    this.id = event.getId().toString();
    this.name = event.getName();
    this.description = event.getDescription();
    this.bannerImageURL = event.getBannerImageURL();
    this.dateTime = event.getDateTime();
    this.geoLocation = event.getGeoLocation();
    this.participants = event.getParticipants().stream().map(ObjectId::toString).collect(Collectors.toList());
    this.venue = event.getVenue();
    this.status = event.getStatus();
    this.registrationDeadline = event.getRegistrationDeadline();
    this.ticketTypes = event.getTicketTypes();
    this.googleMapsLink = event.getGoogleMapsLink();
    this.likedBy = event.getLikedBy().stream().map(ObjectId::toString).collect(Collectors.toList());
  }
}
