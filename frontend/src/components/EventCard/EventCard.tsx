import React from "react";
import { EnvironmentFilled, HeartFilled } from "@ant-design/icons";

import "./EventCard.scss";

export interface EventCardProps {
	backgroundUrl: string;
	title: string;
	price: string;
	likeCount: number;
	location: string;
}

export default function EventCard(props: EventCardProps) {
	return (
		<div className="event-card">
			<img className="event-card-img" src={props.backgroundUrl} />
			<div className="event-card-content">
				<div className="event-card-info">
					<span className="event-card-title">{props.title}</span>
					<div className="event-card-location">
						<span className="icon">
							<EnvironmentFilled />
						</span>
						<span className="event-card-location-name">{props.location}</span>
					</div>
				</div>
				<div className="event-card-details">
					<div className="event-card-likes">
						<span className="event-card-likes-count">{props.likeCount}</span>
						<span>
							<HeartFilled />
						</span>
					</div>
					<span className="event-card-price">{props.price}</span>
				</div>
			</div>
		</div>
	);
}
