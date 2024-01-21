import { DateTime } from "luxon";
import { Button, Modal } from "antd";
import { Event } from "../../models/Event";
import { TicketType } from "../../models/TicketType";
import { createSearchParams, useNavigate } from "react-router-dom";
import { ClockCircleFilled, EnvironmentFilled, LikeFilled, UserOutlined } from "@ant-design/icons";

import "./EventModal.scss";

export interface EventModalProps {
	event: Event;
	isModalOpen: boolean;
	handleCancel: () => any;
}

export default function EventModal({ event, isModalOpen, handleCancel }: EventModalProps) {
	const navigate = useNavigate();

	const renderTicketType = (ticketType: TicketType, i: number) => {
		return (
			<div className="event-modal-ticket" key={i}>
				<div className="event-modal-ticket-title-container">
					<h3>{ticketType.name}</h3>
					<div className="event-modal-ticket-price">
						{ticketType.price} {ticketType.currency}
					</div>
				</div>
				<div className="event-modal-ticket-description">{ticketType.description}</div>
				<Button
					size="large"
					onClick={() =>
						navigate({
							pathname: "/payment/process",
							search: `?${createSearchParams({
								eventId: event.id,
								ticketName: ticketType.name,
							})}`,
						})
					}
				>
					Buy
				</Button>
			</div>
		);
	};

	return (
		<Modal
			className="event-modal"
			wrapClassName="event-modal-wrapper"
			title={event.name}
			open={isModalOpen}
			onCancel={handleCancel}
			centered
			closable
			footer={null}
		>
			<div className="event-modal-content">
				<div className="event-modal-image">
					<img src={event.bannerImageURL} />
				</div>
				<hr />
				<div className="event-modal-description">{event.description}</div>
				<hr />
				<div className="event-modal-location-wrapper">
					<div className="event-modal-location">
						<div className="event-modal-label">
							<span className="event-modal-label-icon">
								<EnvironmentFilled />
							</span>
							<span className="event-modal-label-name event-modal-label-link">
								<a target="_blank" href={event.googleMapsLink}>
									{event.geoLocation.country},{event.geoLocation.city} @ {event.venue}
								</a>
							</span>
						</div>

						<div className="event-modal-label">
							<span className="event-modal-label-icon">
								<ClockCircleFilled />
							</span>
							<span className="event-modal-label-name">
								{DateTime.fromJSDate(new Date(event.dateTime)).toFormat("dd-MM-yyyy HH:mm")}
							</span>
						</div>

						<div className="event-modal-label">
							<span className="event-modal-label-icon">
								<UserOutlined />
							</span>
							<span className="event-modal-label-name">
								{event.participants.length} / {event.capacity}
							</span>
						</div>

						<div className="event-modal-label">
							<span className="event-modal-label-icon">
								<LikeFilled />
							</span>
							<span className="event-modal-label-name">{event.likedBy.length}</span>
						</div>
					</div>
				</div>
				<hr />
				<div className="event-modal-tickets">
					<h2>Tickets</h2>
					<div className="event-modal-tickets-wrapper">{event.ticketTypes.map(renderTicketType)}</div>
				</div>
				<hr />
			</div>
		</Modal>
	);
}
