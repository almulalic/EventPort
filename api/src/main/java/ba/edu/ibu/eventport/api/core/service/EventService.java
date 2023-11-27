package ba.edu.ibu.eventport.api.core.service;

import ba.edu.ibu.eventport.api.core.model.Event;
import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;
import ba.edu.ibu.eventport.api.core.repository.EventRepository;
import ba.edu.ibu.eventport.api.core.repository.generics.filtering.models.Filtering;
import ba.edu.ibu.eventport.api.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.eventport.api.rest.models.dto.EventRequestDTO;
import ba.edu.ibu.eventport.api.rest.models.dto.EventViewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EventService {

  private final EventRepository eventRepository;

  public Page<Event> getEvents(Optional<String> type, Optional<EventStatus> status, Pageable pageable) {
    Filtering filtering = new Filtering();

    type.ifPresent(s -> filtering.addFilter("type", Filtering.Operator.eq, s));
    status.ifPresent(eventStatus -> filtering.addFilter("status", Filtering.Operator.eq, eventStatus));

    return eventRepository.findAllWithFilter(Event.class, filtering, pageable);
  }

  public EventViewDTO getEventById(String id) {
    Optional<Event> event = eventRepository.findById(id);

    if (event.isEmpty()) {
      throw new ResourceNotFoundException("The event with the given ID does not exists.");
    }

    return new EventViewDTO(event.get());
  }

  public List<EventViewDTO> getEventsByCategory(String category) {
    List<Event> events = eventRepository.findByCategory(category);

    if (events.size() == 0) {
      throw new ResourceNotFoundException("The event with the given category does not exists.");
    }

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
