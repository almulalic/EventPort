package ba.edu.ibu.eventport.rest.models.dto;

import ba.edu.ibu.eventport.core.model.Event;
import ba.edu.ibu.eventport.core.model.enums.EventStatus;

import java.util.Date;

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

  public EventViewDTO(
    String id,
    String name,
    String description,
    String bannerImageURL,
    Date dateTime,
    String location,
    String organizer,
    String type,
    EventStatus status,
    Date registrationDeadline
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.bannerImageURL = bannerImageURL;
    this.dateTime = dateTime;
    this.location = location;
    this.organizer = organizer;
    this.type = type;
    this.status = status;
    this.registrationDeadline = registrationDeadline;
  }

  public EventViewDTO(Event event) {
    this.id = event.getId();
    this.name = event.getName();
    this.description = event.getDescription();
    this.bannerImageURL = event.getBannerImageURL();
    this.dateTime = event.getDateTime();
    this.location = event.getLocation();
    this.organizer = event.getOrganizer();
    this.type = event.getType();
    this.status = event.getStatus();
    this.registrationDeadline = event.getRegistrationDeadline();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBannerImageURL() {
    return bannerImageURL;
  }

  public void setBannerImageURL(String bannerImageURL) {
    this.bannerImageURL = bannerImageURL;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public void setDateTime(Date dateTime) {
    this.dateTime = dateTime;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getOrganizer() {
    return organizer;
  }

  public void setOrganizer(String organizer) {
    this.organizer = organizer;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public EventStatus getStatus() {
    return status;
  }

  public void setStatus(EventStatus status) {
    this.status = status;
  }

  public Date getRegistrationDeadline() {
    return registrationDeadline;
  }

  public void setRegistrationDeadline(Date registrationDeadline) {
    this.registrationDeadline = registrationDeadline;
  }
}
