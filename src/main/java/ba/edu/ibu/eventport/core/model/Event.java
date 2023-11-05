package ba.edu.ibu.eventport.core.model;

import org.springframework.data.annotation.Id;
import ba.edu.ibu.eventport.core.model.enums.EventStatus;

import java.util.Date;
import java.util.List;

public class Event {
  @Id
  private String id;
  private String name;
  private String description;
  private Date dateTime;
  private String location;
  private String organizer;
  private List<Integer> participants;
  private String type;
  private EventStatus status;
  private int capacity;
  private Date registrationDeadline;
  private String bannerImageURL;

  public Event() {
  }

  public Event(
    String id,
    String name,
    String description,
    Date dateTime,
    String location,
    String organizer,
    List<Integer> participants,
    String type,
    EventStatus status,
    int capacity,
    Date registrationDeadline,
    String bannerImageURL
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.dateTime = dateTime;
    this.location = location;
    this.organizer = organizer;
    this.participants = participants;
    this.type = type;
    this.status = status;
    this.capacity = capacity;
    this.registrationDeadline = registrationDeadline;
    this.bannerImageURL = bannerImageURL;
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
