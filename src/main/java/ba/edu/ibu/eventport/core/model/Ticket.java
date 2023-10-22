package ba.edu.ibu.eventport.core.model;

public class Ticket {
  private int id;
  private int eventId;
  private int userId;
  private String type;
  private double price;
  private boolean isRegistered;

  public Ticket() {
  }

  public Ticket(int id, int eventId, int userId, String type, double price, boolean isRegistered) {
    this.id = id;
    this.eventId = eventId;
    this.userId = userId;
    this.type = type;
    this.price = price;
    this.isRegistered = isRegistered;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getEventId() {
    return eventId;
  }

  public void setEventId(int eventId) {
    this.eventId = eventId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
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
