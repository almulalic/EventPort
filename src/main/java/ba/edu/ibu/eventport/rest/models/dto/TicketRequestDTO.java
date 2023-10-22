package ba.edu.ibu.eventport.rest.models.dto;

import ba.edu.ibu.eventport.core.model.Event;
import ba.edu.ibu.eventport.core.model.Ticket;

public class TicketRequestDTO {
  private int eventId;
  private int userId;
  private String type;
  private double price;
  private boolean isRegistered;

  public TicketRequestDTO(int eventId, int userId, String type, double price, boolean isRegistered) {
    this.eventId = eventId;
    this.userId = userId;
    this.type = type;
    this.price = price;
    this.isRegistered = isRegistered;
  }

  public Ticket toEntity() {
    Ticket ticket = new Ticket();
    ticket.setEventId(eventId);
    ticket.setUserId(userId);
    ticket.setType(type);
    ticket.setPrice(price);
    ticket.setRegistered(isRegistered);
    return ticket;
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
