import { Event } from "./Event";
import { TicketType } from "./TicketType";

export interface EventTicket {
	event: Event;
	ticketType: TicketType;
}
