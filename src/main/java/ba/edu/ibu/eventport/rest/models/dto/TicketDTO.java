package ba.edu.ibu.eventport.rest.models.dto;

import ba.edu.ibu.eventport.core.model.Ticket;

public class TicketDTO {
  private String id;
  private int eventId;
  private int userId;
  private String type;
  private double price;
  private boolean isRegistered;

  public TicketDTO(Ticket ticket) {
    this.id = ticket.getId();
    this.eventId = ticket.getEventId();
    this.userId = ticket.getUserId();
    this.type = ticket.getType();
    this.price = ticket.getPrice();
    this.isRegistered = ticket.isRegistered();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
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
