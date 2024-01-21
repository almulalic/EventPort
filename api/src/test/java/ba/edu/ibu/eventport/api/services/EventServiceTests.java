//package ba.edu.ibu.eventport.api.services;
//
//import ba.edu.ibu.eventport.api.core.model.event.Event;
//import ba.edu.ibu.eventport.api.core.model.enums.EventStatus;
//import ba.edu.ibu.eventport.api.core.repository.EventRepository;
//import ba.edu.ibu.eventport.api.core.service.EventService;
//import ba.edu.ibu.eventport.api.exceptions.repository.ResourceNotFoundException;
//import ba.edu.ibu.eventport.api.rest.models.dto.EventRequestDTO;
//import ba.edu.ibu.eventport.api.rest.models.dto.EventViewDTO;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@SpringBootTest
//class EventServiceTests {
//
//  @Mock
//  private EventRepository eventRepository;
//
//  @InjectMocks
//  private EventService eventService;
//
//  @Test
//  void getEvents() {
//    String type = "TestType";
//    EventStatus status = EventStatus.IN_PROGRESS;
//    Pageable pageable = Pageable.unpaged();
//    Page<Event> mockedPage = mock(Page.class);
//
//    when(eventRepository.findByTypeAndStatus(type, status, pageable))
//      .thenReturn(mockedPage);
//
//    assertEquals(mockedPage, eventService.getEvents(type, status, pageable));
//    verify(eventRepository).findByTypeAndStatus(type, status, pageable);
//  }
//
//  @Test
//  void getEventByIdWithExistingEvent() {
//    String eventId = "qohfu238hkwndori3wey";
//    Event event = new Event(
//      eventId,
//      "Koncert Dine Merlina",
//      "54 koncert Dine Merline u Beogradu.",
//      new Date(),
//      "Beograd",
//      List.of(1, 2, 3),
//      "Koncert",
//      EventStatus.APPROACHING_DEADLINE,
//      100,
//      new Date(),
//      "https://sadkmaskdmas.com"
//    );
//    EventViewDTO expectedEventViewDTO = new EventViewDTO(event);
//
//    when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
//
//    assertThat(expectedEventViewDTO).usingRecursiveComparison()
//      .isEqualTo(eventService.getEventById(eventId));
//    verify(eventRepository).findById(eventId);
//  }
//
//  @Test
//  void getEventByIdWithNonExistingEvent() {
//    String eventId = "nonExistingId";
//
//    when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
//
//    assertThrows(ResourceNotFoundException.class, () -> eventService.getEventById(eventId));
//    verify(eventRepository).findById(eventId);
//  }
//
//  @Test
//  void addEvent() {
//    EventRequestDTO eventRequestDTO = new EventRequestDTO(
//      "Koncert Dine Merlina",
//      "54 koncert Dine Merline u Beogradu.",
//      new Date(),
//      "Beograd",
//      List.of(1, 2, 3),
//      "Koncert",
//      EventStatus.APPROACHING_DEADLINE,
//      100,
//      new Date(),
//      "https://sadkmaskdmas.com"
//    );
//    Event event = eventRequestDTO.toEntity();
//    event.setId("habsdhajsandjand");
//
//    when(eventRepository.save(any(Event.class))).thenReturn(event);
//
//    assertThat(new EventViewDTO(event)).usingRecursiveComparison()
//      .isEqualTo(eventService.addEvent(eventRequestDTO));
//    verify(eventRepository).save(any(Event.class));
//  }
//
//  @Test
//  void updateEventWithExistingEvent() {
//    String eventId = "suygd97qgwsdashubd";
//    EventRequestDTO updatedEventRequestDTO = new EventRequestDTO(
//      "Koncert Dine Merlina",
//      "54 koncert Dine Merline u Beogradu.",
//      new Date(),
//      "Beograd",
//      List.of(1, 2, 3),
//      "Koncert",
//      EventStatus.APPROACHING_DEADLINE,
//      100,
//      new Date(),
//      "https://sadkmaskdmas.com"
//    );
//    Event updatedEvent = updatedEventRequestDTO.toEntity();
//    updatedEvent.setId(eventId);
//
//    when(eventRepository.findById(eventId)).thenReturn(Optional.of(new Event()));
//    when(eventRepository.save(any(Event.class))).thenReturn(updatedEvent);
//
//    assertThat(new EventViewDTO(updatedEvent)).usingRecursiveComparison()
//      .isEqualTo(eventService.updateEvent(eventId, updatedEventRequestDTO));
//
//    verify(eventRepository).findById(eventId);
//    verify(eventRepository).save(any(Event.class));
//  }
//
//  @Test
//  void updateEventWithNonExistingEvent() {
//    String eventId = "nonExistingId";
//    EventRequestDTO updatedEventRequestDTO = new EventRequestDTO(
//      "Koncert Dine Merlina",
//      "54 koncert Dine Merline u Beogradu.",
//      new Date(),
//      "Beograd",
//      List.of(1, 2, 3),
//      "Koncert",
//      EventStatus.APPROACHING_DEADLINE,
//      100,
//      new Date(),
//      "https://sadkmaskdmas.com"
//    );
//
//    when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
//
//    assertThrows(ResourceNotFoundException.class, () -> eventService.updateEvent(eventId, updatedEventRequestDTO));
//
//    verify(eventRepository).findById(eventId);
//    verify(eventRepository, never()).save(any());
//  }
//
//  @Test
//  void deleteEventWithExistingEvent() {
//    String eventId = "wouegf08ndqnq9ni9";
//    Event existingEvent = new Event();
//
//    when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
//
//    eventService.deleteEvent(eventId);
//
//    verify(eventRepository).findById(eventId);
//    verify(eventRepository).delete(existingEvent);
//  }
//
//  @Test
//  void deleteEventWithNonExistingEvent() {
//    String eventId = "nonExistingId";
//
//    when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
//
//    eventService.deleteEvent(eventId);
//
//    verify(eventRepository).findById(eventId);
//    verify(eventRepository, never()).delete(any());
//  }
//}
