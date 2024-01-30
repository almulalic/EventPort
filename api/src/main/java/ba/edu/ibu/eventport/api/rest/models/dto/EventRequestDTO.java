package ba.edu.ibu.eventport.api.rest.models.dto;

import ba.edu.ibu.eventport.api.core.model.event.Event;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "Builder", builderClassName = "Builder", setterPrefix = "with")
public class EventRequestDTO {
  @NotEmpty
  private String name;

  @NotEmpty
  private String description;

  @NotEmpty
  @Future
  private LocalDateTime dateTime;

  @NotEmpty
  private String venue;

  @NotEmpty
  private String city;

  @NotEmpty
  private String countryIso2Code;

  @Email.List(
    {
      @Email(message = "Invalid email address"),
    }
  )
  private List<Integer> notificationList = new ArrayList<>();

  @NotEmpty
  private String category;

  private EventStatus status;

  @NotEmpty
  @Positive
  private int capacity;

  @NotEmpty
  @Future
  private LocalDateTime registrationDeadline;

  @NotEmpty
  private String bannerImageURL;

  public Event toEntity() {
    return Event.Builder()
             .withName(name)
             .withDescription(description)
             .withDateTime(dateTime)
             .withVenue(venue)
             .withStatus(status)
             .withCapacity(capacity)
             .withRegistrationDeadline(registrationDeadline)
             .withBannerImageURL(bannerImageURL)
             .build();
  }
}
