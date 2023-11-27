package ba.edu.ibu.eventport.api.rest.controllers;

import ba.edu.ibu.eventport.api.core.model.Event;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;
import ba.edu.ibu.eventport.api.rest.configurations.versioning.ApiVersion;
import ba.edu.ibu.eventport.api.rest.models.dto.EventRequestDTO;
import ba.edu.ibu.eventport.api.rest.models.dto.EventViewDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ba.edu.ibu.eventport.api.core.service.EventService;

import java.util.List;
import java.util.Optional;

@RestController
@ApiVersion(1)
@RequestMapping("api/events")
public class EventController {
  private final EventService eventService;

  public EventController(final EventService eventService) {
    this.eventService = eventService;
  }

  @RequestMapping(method = RequestMethod.GET, path = "")
  public ResponseEntity<Page<Event>> getEvents(
    @RequestParam(required = false) Optional<String> type,
    @RequestParam(required = false) Optional<EventStatus> status,
    Pageable pageable
  ) {
    return ResponseEntity.ok(eventService.getEvents(type, status, pageable));
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

  @RequestMapping(method = RequestMethod.POST, path = "")
  public ResponseEntity<EventViewDTO> add(
    @RequestBody EventRequestDTO event
  ) {
    return ResponseEntity.ok(eventService.addEvent(event));
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
