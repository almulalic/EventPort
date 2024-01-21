import { message } from "antd";
import { Event } from "../../models/Event";
import { useEffect, useState } from "react";
import Page from "../../components/Page/Page";
import { AuthorizedAPIService } from "../../services/AuthorizedApiService";

import "./LikedEvents.scss";
import EventCard from "../../components/EventCard/EventCard";

export default function LikedEvents() {
	const [loading, setLoading] = useState(false);
	const [events, setEvents] = useState([]);

	const [messageApi, contextHolder] = message.useMessage();

	async function getLikedEvents() {
		const response = await AuthorizedAPIService.likedEvents();

		if (response.status === 200) {
			setEvents(response.data);
			setLoading(true);
		} else {
			messageApi.error("Failed to load your liked events...");
			setLoading(false);
		}
	}

	useEffect(() => {
		getLikedEvents();
	}, []);

	return (
		<Page id="my-events">
			{events.map((x: Event, i: number) => (
				<EventCard key={i} event={x} overview={true} />
			))}
			{contextHolder}
		</Page>
	);
}
