package ba.edu.ibu.eventport.api.core.service;

import ba.edu.ibu.eventport.api.core.model.Event;
import ba.edu.ibu.eventport.api.core.repository.EventRepository;
import ba.edu.ibu.eventport.api.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.eventport.api.rest.models.dto.EventRequestDTO;
import ba.edu.ibu.eventport.api.rest.models.dto.EventViewDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {

  private final EventRepository eventRepository;

  public EventService(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  public List<EventViewDTO> getEvents() {
    List<Event> events = eventRepository.findAll();

    return events.stream().map(EventViewDTO::new).collect(Collectors.toList());
  }

  public EventViewDTO getEventById(String id) {
    Optional<Event> event = eventRepository.findById(id);

    if (event.isEmpty()) {
      throw new ResourceNotFoundException("The event with the given ID does not exists.");
    }

    return new EventViewDTO(event.get());
  }

  public List<EventViewDTO> getEventByOrganizerAndAvailable(String identifier) {
    List<Event> events = eventRepository.findAllByOrganizerAndAvailable(identifier, identifier);

    return events.stream().map(EventViewDTO::new).collect(Collectors.toList());
  }

  public EventViewDTO addEvent(EventRequestDTO payload) {
    Event event = eventRepository.save(payload.toEntity());
    return new EventViewDTO(event);
  }

  public EventViewDTO updateEvent(String id, EventRequestDTO payload) {
    Optional<Event> event = eventRepository.findById(id);

    if (event.isEmpty()) {
      throw new ResourceNotFoundException("The event with the given ID does not exists.");
    }

    Event updatedEvent = payload.toEntity();
    updatedEvent.setId(id);
    updatedEvent = eventRepository.save(updatedEvent);
    return new EventViewDTO(updatedEvent);
  }

  public void deleteEvent(String id) {
    Optional<Event> event = eventRepository.findById(id);
    event.ifPresent(eventRepository::delete);
  }
}
