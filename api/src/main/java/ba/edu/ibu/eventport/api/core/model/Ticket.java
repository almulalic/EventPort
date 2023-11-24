package ba.edu.ibu.eventport.api.core.model;

public class Ticket {
  private String id;
  private String eventId;
  private String userId;
  private String type;
  private double price;
  private boolean isRegistered;

  public Ticket() {
  }

  public Ticket(String id, String eventId, String userId, String type, double price, boolean isRegistered) {
    this.id = id;
    this.eventId = eventId;
    this.userId = userId;
    this.type = type;
    this.price = price;
    this.isRegistered = isRegistered;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public boolean isRegistered() {
    return isRegistered;
  }

  public void setRegistered(boolean registered) {
    isRegistered = registered;
  }
}
