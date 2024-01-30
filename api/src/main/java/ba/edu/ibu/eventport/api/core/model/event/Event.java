package ba.edu.ibu.eventport.api.core.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "Builder", builderClassName = "Builder", setterPrefix = "with")
public class Event {
  @Id
  private ObjectId id;
  private String name;
  private String description;
  private List<ObjectId> likedBy;
  private LocalDateTime dateTime;
  private String venue;
  private GeoLocation geoLocation;
  private String googleMapsLink;
  private List<ObjectId> participants;
  private EventStatus status;
  private String category;
  private int capacity;
  private LocalDateTime registrationDeadline;
  private String bannerImageURL;
  private String priceRange;
  private List<TicketType> ticketTypes;
}
