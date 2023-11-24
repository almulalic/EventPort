package ba.edu.ibu.eventport.api.rest.models.dto;

import ba.edu.ibu.eventport.api.core.model.Event;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;

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

  public List<Integer> getParticipants() {
    return participants;
  }

  public void setParticipants(List<Integer> participants) {
    this.participants = participants;
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

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public Date getRegistrationDeadline() {
    return registrationDeadline;
  }

  public void setRegistrationDeadline(Date registrationDeadline) {
    this.registrationDeadline = registrationDeadline;
  }

  public String getBannerImageURL() {
    return bannerImageURL;
  }

  public void setBannerImageURL(String bannerImageURL) {
    this.bannerImageURL = bannerImageURL;
  }
}
