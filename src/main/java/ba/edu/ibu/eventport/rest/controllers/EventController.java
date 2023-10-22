package ba.edu.ibu.eventport.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ba.edu.ibu.eventport.core.service.EventService;
import ba.edu.ibu.eventport.rest.models.dto.EventViewDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import ba.edu.ibu.eventport.rest.models.dto.EventRequestDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class EventController {
  private final EventService eventService;

  public EventController(final EventService EventService) {
    this.eventService = EventService;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/")
  public ResponseEntity<List<EventViewDTO>> getEvents() {
    return ResponseEntity.ok(eventService.getEvents());
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{organization}")
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
