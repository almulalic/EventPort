package ba.edu.ibu.eventport.api.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Event {
  @Id
  @Getter
  @Setter
  private String id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String description;

  @Getter
  @Setter
  private Date dateTime;

  @Getter
  @Setter
  private String location;

  @Getter
  @Setter
  private List<Integer> participants;

  @Getter
  @Setter
  private String type;

  @Getter
  @Setter
  private EventStatus status;

  @Getter
  @Setter
  private int capacity;

  @Getter
  @Setter
  private Date registrationDeadline;

  @Getter
  @Setter
  private String bannerImageURL;
}
