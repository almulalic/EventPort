package ba.edu.ibu.eventport.api.core.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoLocation {
  private String iso2code;
  private String country;
  private String city;
}
