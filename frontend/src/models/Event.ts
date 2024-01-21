import { TicketType } from "./TicketType";
import { EventStatus } from "./EventStatus";
import { GeoLocation } from "./GeoLocation";

export interface Event {
	id: string;
	name: string;
	description: string;
	likedBy: string[];
	dateTime: string;
	venue: string;
	googleMapsLink: string;
	geoLocation: GeoLocation;
	participants: string[];
	status: EventStatus;
	category: string;
	capacity: number;
	registrationDeadline: string;
	bannerImageURL: string;
	priceRange: string;
	ticketTypes: TicketType[];
}
