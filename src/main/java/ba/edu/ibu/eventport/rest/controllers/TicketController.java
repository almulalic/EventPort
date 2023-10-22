package ba.edu.ibu.eventport.rest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ba.edu.ibu.eventport.rest.models.dto.TicketDTO;
import ba.edu.ibu.eventport.core.service.TicketService;
import ba.edu.ibu.eventport.rest.models.dto.TicketRequestDTO;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketController {
  private final TicketService ticketService;

  public TicketController(final TicketService ticketService) {
    this.ticketService = ticketService;
  }

  @RequestMapping(method = RequestMethod.GET, path = "/")
  public ResponseEntity<List<TicketDTO>> getTickets() {
    return ResponseEntity.ok(ticketService.getTickets());
  }

  @RequestMapping(method = RequestMethod.GET, path = "/{id}")
  public ResponseEntity<TicketDTO> getTicketById(
    @PathVariable String id
  ) {
    return ResponseEntity.ok(ticketService.getTicketById(id));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/register")
  public ResponseEntity<TicketDTO> register(
    @RequestBody TicketRequestDTO ticket
  ) {
    return ResponseEntity.ok(ticketService.addTicket(ticket));
  }

  @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
  public ResponseEntity<TicketDTO> updateTicket(
    @PathVariable String id,
    @RequestBody TicketRequestDTO ticket
  ) {
    return ResponseEntity.ok(ticketService.updateTicket(id, ticket));
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
  public ResponseEntity<Void> deleteTicket(
    @PathVariable String id
  ) {
    ticketService.deleteTicket(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
