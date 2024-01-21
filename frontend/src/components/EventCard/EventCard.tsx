import { message } from "antd";
import { DateTime } from "luxon";
import { useState } from "react";
import { RootState } from "../../store";
import { useSelector } from "react-redux";
import { Event } from "../../models/Event";
import EventModal from "../EventModal/EventModal";
import { TicketType } from "../../models/TicketType";
import { AuthorizedAPIService } from "../../services/AuthorizedApiService";
import { EventOverviewModal } from "../EventOverviewModal/EventOverviewModal";
import { ClockCircleFilled, EnvironmentFilled, HeartFilled } from "@ant-design/icons";

import "./EventCard.scss";
import CountdownTimer from "../Countdown/Countdown";

export interface EventCardProps {
	overview: boolean;
	event: Event;
	ticketType?: TicketType;
}

export default function EventCard({ overview, event, ticketType }: EventCardProps) {
	const [messageApi, contextHolder] = message.useMessage();

	const [isModalOpen, setModalOpen] = useState(false);

	const { isLoggedIn, userInfo } = useSelector((state: RootState) => state.auth);

	const [isLikedByUser, setLikedByUser] = useState(
		userInfo ? (event.likedBy.find((x) => x == userInfo.id) ? true : false) : false
	);

	async function like(e: any) {
		e.stopPropagation();

		if (!isLoggedIn) {
			messageApi.error("You must be logged in to like!");
			return;
		}

		const response = await AuthorizedAPIService.like(event.id);

		if (response.status === 200) {
			event.likedBy.push(userInfo!.id);
			setLikedByUser(true);
			messageApi.success("You like this event, nice!");
		} else {
			messageApi.error(response.data.message);
		}
	}

	async function unlike(e: any) {
		e.stopPropagation();

		if (!isLoggedIn) {
			messageApi.error("You must be logged in to unlike!");
			return;
		}

		const response = await AuthorizedAPIService.unlike(event.id);

		if (response.status === 200) {
			setLikedByUser(false);
			event.likedBy = event.likedBy.filter((x) => x != userInfo!.id);

			messageApi.success("You unliked this event :(");
		} else {
			messageApi.error(response.data.message);
		}
	}

	const handleModalClose = () => {
		setModalOpen(false);
	};

	return (
		<>
			<div className="event-card" onClick={() => setModalOpen(true)}>
				<img className="event-card-img" src={event.bannerImageURL} />
				<div className="event-card-content">
					<div className="event-card-info">
						<span className="event-card-title">{event.name}</span>
						{!overview && (
							<div className="event-card-likes">
								<span className="event-card-likes-count">{event.likedBy.length}</span>
								<span className={`event-card-likes-icon event-card-liked-${isLikedByUser}`}>
									<HeartFilled onClick={isLikedByUser ? unlike : like} />
								</span>
							</div>
						)}
					</div>

					{overview && (
						<span className="event-card-countdown-timer">
							<CountdownTimer targetDate={event.dateTime} />
						</span>
					)}

					<div className="event-card-label">
						<span className="event-card-label-icon">
							<EnvironmentFilled />
						</span>
						<span className="event-card-label-name">
							{event.geoLocation.country}, {event.geoLocation.city} @ {event.venue}
						</span>
					</div>

					<div className="event-card-label">
						<span className="event-card-label-icon">
							<ClockCircleFilled />
						</span>
						<span className="event-card-label-name">
							{DateTime.fromJSDate(new Date(event.dateTime)).toFormat("dd-MM-yyyy HH:mm")}
						</span>
					</div>
				</div>
				{contextHolder}
			</div>
			{overview ? (
				<EventOverviewModal
					event={event}
					ticketType={ticketType!}
					isModalOpen={isModalOpen}
					handleCancel={handleModalClose}
				/>
			) : (
				<EventModal event={event} isModalOpen={isModalOpen} handleCancel={handleModalClose} />
			)}
		</>
	);
}
