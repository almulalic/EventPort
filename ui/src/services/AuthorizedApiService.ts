import { authorizedAxiosApp } from "./Axios";
import { AxiosResponse } from "axios";

export class AuthorizedAPIService {
	static async like(eventId: string): Promise<AxiosResponse> {
		return authorizedAxiosApp.post(`/event/user/like/${eventId}`);
	}

	static async unlike(eventId: string): Promise<AxiosResponse> {
		return authorizedAxiosApp.post(`/event/user/unlike/${eventId}`);
	}

	static async likedEvents(): Promise<AxiosResponse> {
		return authorizedAxiosApp.get(`/event/user/liked`);
	}

	static async attendingEvents(): Promise<AxiosResponse> {
		return authorizedAxiosApp.get(`/event/user/attending`);
	}

	static async buyTicket(eventId: string, ticketName: string): Promise<AxiosResponse> {
		return authorizedAxiosApp.post(`/event/${eventId}/ticket/${ticketName}/buy`);
	}
}
