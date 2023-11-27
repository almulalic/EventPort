package ba.edu.ibu.eventport.api.core.model;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
  @Getter
  @Setter
  private String id;

  @Getter
  @Setter
  private String eventId;

  @Getter
  @Setter
  private String userId;

  @Getter
  @Setter
  private String type;

  @Getter
  @Setter
  private double price;

  @Getter
  @Setter
  private boolean isRegistered;
}
