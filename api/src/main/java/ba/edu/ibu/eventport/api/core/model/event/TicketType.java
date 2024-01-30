package ba.edu.ibu.eventport.api.core.model.event;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketType {
  private String name;
  private String description;
  private String currency;
  private double price;
  private List<String> options;
}
