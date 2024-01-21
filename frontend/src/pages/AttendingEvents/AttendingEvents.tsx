import { Empty, message } from "antd";
import { useEffect, useState } from "react";
import Page from "../../components/Page/Page";
import { EventTicket } from "../../models/EventTicket";
import EventCard from "../../components/EventCard/EventCard";
import { AuthorizedAPIService } from "../../services/AuthorizedApiService";

import "./AttendingEvents.scss";

export default function AttendingEvents() {
	const [loading, setLoading] = useState(false);
	const [events, setEvents] = useState([]);

	const [messageApi, contextHolder] = message.useMessage();

	async function getAttendingEvents() {
		const response = await AuthorizedAPIService.attendingEvents();

		if (response.status === 200) {
			setEvents(response.data);
			setLoading(true);
		} else {
			messageApi.error("Failed to load your attending events...");
			setLoading(false);
		}
	}

	useEffect(() => {
		getAttendingEvents();
	}, []);

	return (
		<Page id="attending-events">
			{events.length === 0 ? (
				<div className="attending-events-empty-state">
					<Empty />
				</div>
			) : (
				<div className="attending-events-wrapper">
					{events.map((et: EventTicket, i: number) => (
						<EventCard key={i} event={et.event} ticketType={et.ticketType} overview={true} />
					))}
				</div>
			)}

			{contextHolder}
		</Page>
	);
}
