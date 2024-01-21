package ba.edu.ibu.eventport.api.core.service;

import ba.edu.ibu.eventport.api.core.model.Category;
import ba.edu.ibu.eventport.api.core.model.Country;
import ba.edu.ibu.eventport.api.core.model.Ticket;
import ba.edu.ibu.eventport.api.core.model.event.Event;
import ba.edu.ibu.eventport.api.core.model.event.TicketType;
import ba.edu.ibu.eventport.api.core.repository.*;
import ba.edu.ibu.eventport.api.exceptions.general.BadRequestException;
import ba.edu.ibu.eventport.api.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.eventport.api.rest.models.auth.User;
import ba.edu.ibu.eventport.api.rest.models.dto.EventRequestDTO;
import ba.edu.ibu.eventport.api.rest.models.dto.EventTicket;
import ba.edu.ibu.eventport.api.rest.models.dto.EventViewDTO;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EventService {

  private final EventRepository eventRepository;
  private final FilterableEventRepositoryImpl filterableEventRepository;

  private final CountryRepository countryRepository;
  private final CategoryRepository categoryRepository;
  private final TicketRepository ticketRepository;

  public Page<Event> getEvents(
    String optionalText,
    Optional<List<String>> geoLocationCities,
    Optional<List<String>> geoLocationCountries,
    Optional<List<String>> categories,
    Optional<String> startDate,
    Optional<String> endDate,
    Pageable pageable
  ) {
    return filterableEventRepository.findAll(
      optionalText,
      geoLocationCities.orElse(List.of()),
      geoLocationCountries.orElse(List.of()),
      categories.orElse(List.of()),
      startDate.orElse(""),
      endDate.orElse(""),
      pageable
    );
  }

  public List<Event> getUserLikedEvents(String userId) {
    return eventRepository.findUserLikedEvents(userId);
  }

  public List<EventTicket> getUserAttendingEvents(String userId) {
    return ticketRepository.findUserAttendingEvents(new ObjectId(userId));
  }

  public EventViewDTO getEventById(String id) {
    Optional<Event> event = eventRepository.findById(id);

    if (event.isEmpty()) {
      throw new ResourceNotFoundException("The event with the given ID does not exists.");
    }

    return new EventViewDTO(event.get());
  }

  public EventViewDTO likeEvent(String userId, String eventId) {
    Optional<Event> possibleEvent = eventRepository.findById(eventId);

    if (possibleEvent.isEmpty()) {
      throw new ResourceNotFoundException("The event with the given ID does not exists.");
    }

    Event event = possibleEvent.get();
    ObjectId userObjectId = new ObjectId(userId);

    if (event.getLikedBy().contains(userObjectId)) {
      throw new BadRequestException("You already liked this event!");
    }

    event.getLikedBy().add(userObjectId);
    event.setId(userObjectId);
    eventRepository.save(event);

    return new EventViewDTO(event);
  }

  public EventViewDTO unlikeEvent(String userId, String eventId) {
    Optional<Event> possibleEvent = eventRepository.findById(eventId);

    if (possibleEvent.isEmpty()) {
      throw new ResourceNotFoundException("The event with the given ID does not exists.");
    }

    Event event = possibleEvent.get();
    ObjectId userObjectId = new ObjectId(userId);

    if (!event.getLikedBy().contains(userObjectId)) {
      throw new BadRequestException("You didn't like this event!");
    }

    event.getLikedBy().remove(userObjectId);
    event.setId(event.getId());
    eventRepository.save(event);

    return new EventViewDTO(event);
  }

  public EventViewDTO buyTicket(String userId, String eventId, String ticketName) {
    Optional<Event> possibleEvent = eventRepository.findById(eventId);

    if (possibleEvent.isEmpty()) {
      throw new ResourceNotFoundException("The event with the given ID does not exists.");
    }

    Event event = possibleEvent.get();
    ObjectId userObjectId = new ObjectId(userId);

    if (event.getParticipants().contains(userObjectId)) {
      throw new BadRequestException("You are already attending this event.");
    }

    TicketType ticketType = event.getTicketTypes()
                              .stream()
                              .filter(x -> x.getName().equals(ticketName))
                              .findFirst()
                              .orElseThrow(() -> new BadRequestException("Ticket type does not exist."));

    event.getParticipants().add(userObjectId);

    Ticket ticket = Ticket.Builder()
                      .withUserId(userObjectId)
                      .withEventId(event.getId())
                      .withName(ticketType.getName())
                      .withPrice(ticketType.getPrice())
                      .build();

    try {
      // Simulate buy
      Thread.sleep(new Random().nextInt(4500 - 2000 + 1) + 2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    eventRepository.save(event);
    ticketRepository.save(ticket);

    return new EventViewDTO(event);
  }

  public List<EventViewDTO> getEventsByCategory(String category) {
    List<Event> events = eventRepository.findByCategory(category);

    if (events.size() == 0) {
      throw new ResourceNotFoundException("The event with the given category does not exists.");
    }

    return events.stream().map(EventViewDTO::new).collect(Collectors.toList());
  }

  public EventViewDTO addEvent(EventRequestDTO payload) {
    Optional<Country> country = countryRepository.findCountryByCitiesContainsAndIso2Code(
      payload.getCity(),
      payload.getCountryIso2Code()
    );

    if (country.isEmpty()) {
      throw new BadRequestException("Country for city %s was not found.".formatted(payload.getCity()));
    }

    Optional<Category> category = categoryRepository.findById(payload.getCategory());

    if (category.isEmpty()) {
      throw new BadRequestException("Category %s does not exist.".formatted(payload.getCity()));
    }

    Event event = eventRepository.save(payload.toEntity());

    //TODO Notify participant

    return new EventViewDTO(event);
  }

  public EventViewDTO updateEvent(String id, EventRequestDTO payload) {
    Optional<Event> event = eventRepository.findById(id);

    if (event.isEmpty()) {
      throw new ResourceNotFoundException("The event with the given ID does not exists.");
    }

    Event updatedEvent = payload.toEntity();

    updatedEvent.setId(event.get().getId());
    updatedEvent = eventRepository.save(updatedEvent);
    return new EventViewDTO(updatedEvent);
  }

  public void deleteEvent(String id) {
    Optional<Event> event = eventRepository.findById(id);
    event.ifPresent(eventRepository::delete);
  }
}
