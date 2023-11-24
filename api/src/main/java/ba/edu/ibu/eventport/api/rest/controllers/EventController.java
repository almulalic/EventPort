package ba.edu.ibu.eventport.api.rest.controllers;

import ba.edu.ibu.eventport.api.rest.models.dto.EventRequestDTO;
import ba.edu.ibu.eventport.api.rest.models.dto.EventViewDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ba.edu.ibu.eventport.api.core.service.EventService;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {
  private final EventService eventService;

  public EventController(final EventService eventService) {
    this.eventService = eventService;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/")
  public ResponseEntity<List<EventViewDTO>> getEvents() {
    return ResponseEntity.ok(eventService.getEvents());
  }

  @RequestMapping(method = RequestMethod.GET, path = "organization/{organization}")
  public ResponseEntity<List<EventViewDTO>> getEventByOrganization(
    @PathVariable String organization
  ) {
    return ResponseEntity.ok(eventService.getEventByOrganizerAndAvailable(organization));
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{id}")
  public ResponseEntity<EventViewDTO> getEventById(
    @PathVariable String id
  ) {
    return ResponseEntity.ok(eventService.getEventById(id));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/register")
  public ResponseEntity<EventViewDTO> register(
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
