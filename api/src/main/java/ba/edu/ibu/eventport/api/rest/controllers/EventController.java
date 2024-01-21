package ba.edu.ibu.eventport.api.rest.controllers;

import ba.edu.ibu.eventport.api.core.model.event.Event;
import ba.edu.ibu.eventport.api.core.service.JwtService;
import ba.edu.ibu.eventport.api.rest.configurations.versioning.ApiVersion;
import ba.edu.ibu.eventport.api.rest.models.auth.User;
import ba.edu.ibu.eventport.api.rest.models.dto.EventRequestDTO;
import ba.edu.ibu.eventport.api.rest.models.dto.EventTicket;
import ba.edu.ibu.eventport.api.rest.models.dto.EventViewDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ba.edu.ibu.eventport.api.core.service.EventService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@ApiVersion(1)
@RequestMapping("api/event")
public class EventController {
  private final EventService eventService;

  public EventController(final EventService eventService) {
    this.eventService = eventService;
  }

  @RequestMapping(method = RequestMethod.GET, path = "")
  public ResponseEntity<Page<EventViewDTO>> getEvents(
    @RequestParam(required = false) String searchText,
    @RequestParam(required = false) Optional<List<String>> geoLocationCountries,
    @RequestParam(required = false) Optional<List<String>> geoLocationCities,
    @RequestParam(required = false) Optional<List<String>> categories,
    @RequestParam(required = false) Optional<String> startDate,
    @RequestParam(required = false) Optional<String> endDate,
    Pageable pageable
  ) {
    return ResponseEntity.ok(
      eventService.getEvents(
          searchText,
          geoLocationCountries,
          geoLocationCities,
          categories,
          startDate,
          endDate,
          pageable
        )
        .map(EventViewDTO::new)
    );
  }

  @RequestMapping(method = RequestMethod.GET, path = "category/{category}")
  public ResponseEntity<List<EventViewDTO>> getEventByCategory(
    @PathVariable String category
  ) {
    return ResponseEntity.ok(eventService.getEventsByCategory(category));
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{id}")
  public ResponseEntity<EventViewDTO> getEventById(
    @PathVariable String id
  ) {
    return ResponseEntity.ok(eventService.getEventById(id));
  }

  @RequestMapping(method = RequestMethod.GET, path = "/user/liked")
  public ResponseEntity<List<Event>> getUserLikedEvents(
    HttpServletRequest request
  ) {
    User user = ((User) request.getAttribute("user"));
    return ResponseEntity.ok(eventService.getUserLikedEvents(user.getId()));
  }

  @RequestMapping(method = RequestMethod.GET, path = "/user/attending")
  public ResponseEntity<List<EventTicket>> getUserAttendingEvents(
    HttpServletRequest request
  ) {
    User user = ((User) request.getAttribute("user"));
    return ResponseEntity.ok(eventService.getUserAttendingEvents(user.getId()));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/user/like/{eventId}")
  public ResponseEntity<EventViewDTO> like(
    HttpServletRequest request,
    @PathVariable String eventId
  ) {
    User user = ((User) request.getAttribute("user"));
    return ResponseEntity.ok(eventService.likeEvent(user.getId(), eventId));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/user/unlike/{eventId}")
  public ResponseEntity<EventViewDTO> unlike(
    HttpServletRequest request,
    @PathVariable String eventId
  ) {
    User user = ((User) request.getAttribute("user"));
    return ResponseEntity.ok(eventService.unlikeEvent(user.getId(), eventId));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/{eventId}/ticket/{ticketName}/buy")
  public ResponseEntity<EventViewDTO> buyTicket(
    HttpServletRequest request,
    @PathVariable String eventId,
    @PathVariable String ticketName
  ) {
    User user = ((User) request.getAttribute("user"));
    return ResponseEntity.ok(eventService.buyTicket(user.getId(), eventId, ticketName));
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
  public ResponseEntity<EventViewDTO> updateEvent(
    @PathVariable String id,
    @RequestBody EventRequestDTO event
  ) {
    return ResponseEntity.ok(eventService.updateEvent(id, event));
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
  public ResponseEntity<Void> deleteEvent(
    @PathVariable String id
  ) {
    eventService.deleteEvent(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
