package ba.edu.ibu.eventport.core.service;

import ba.edu.ibu.eventport.core.model.Ticket;
import ba.edu.ibu.eventport.rest.models.dto.TicketDTO;
import ba.edu.ibu.eventport.rest.models.dto.TicketRequestDTO;
import ba.edu.ibu.eventport.core.repository.TicketRepository;
import ba.edu.ibu.eventport.exceptions.repository.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketService {
  private TicketRepository TicketRepository;

  public TicketService(TicketRepository TicketRepository) {
    this.TicketRepository = TicketRepository;
  }

  public List<TicketDTO> getTickets() {
    List<Ticket> tickets = TicketRepository.findAll();

    return tickets.stream().map(TicketDTO::new).collect(Collectors.toList());
  }

  public TicketDTO getTicketById(String id) {
    Optional<Ticket> ticket = TicketRepository.findById(id);

    if (ticket.isEmpty()) {
      throw new ResourceNotFoundException("The ticket with the given ID does not exists.");
    }

    return new TicketDTO(ticket.get());
  }

  public TicketDTO addTicket(TicketRequestDTO payload) {
    Ticket ticket = TicketRepository.save(payload.toEntity());
    return new TicketDTO(ticket);
  }

  public TicketDTO updateTicket(String id, TicketRequestDTO payload) {
    Optional<Ticket> ticket = TicketRepository.findById(id);

    if (ticket.isEmpty()) {
      throw new ResourceNotFoundException("The ticket with the given ID does not exists.");
    }

    Ticket updatedTicket = payload.toEntity();
    updatedTicket.setId(id);
    updatedTicket = TicketRepository.save(updatedTicket);
    return new TicketDTO(updatedTicket);
  }

  public void deleteTicket(String id) {
    Optional<Ticket> ticket = TicketRepository.findById(id);
    ticket.ifPresent(TicketRepository::delete);
  }
}
