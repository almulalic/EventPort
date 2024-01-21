import { RefObject, useRef } from "react";
import EventCard from "../../components/EventCard/EventCard";
import { ArrowLeftOutlined, ArrowRightOutlined } from "@ant-design/icons";

import "./EventsInFocus.scss";

const events = [
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?music",
		title: "Music Festival",
		price: "$50",
		likeCount: 120,
		location: "City Concert Hall",
	},
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?art",
		title: "Art Exhibition",
		price: "$25",
		likeCount: 80,
		location: "Downtown Art Gallery",
	},
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?food",
		title: "Food Tasting",
		price: "$30",
		likeCount: 100,
		location: "Beach",
	},
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?tech",
		title: "Tech Conference",
		price: "$75",
		likeCount: 50,
		location: "Conference Center",
	},
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?outdoor",
		title: "Outdoor Adventure",
		price: "$40",
		likeCount: 90,
		location: "Park",
	},
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?fashion",
		title: "Fashion Show",
		price: "$60",
		likeCount: 110,
		location: "Museum",
	},
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?film",
		title: "Film Premiere",
		price: "$55",
		likeCount: 70,
		location: "Community Center",
	},
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?science",
		title: "Science Fair",
		price: "$20",
		likeCount: 30,
		location: "Beach",
	},
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?charity",
		title: "Charity Gala",
		price: "$100",
		likeCount: 150,
		location: "Park",
	},
	{
		backgroundUrl: "https://source.unsplash.com/800x600/?book",
		title: "Book Launch",
		price: "$35",
		likeCount: 60,
		location: "Museum",
	},
];

export default function EventsInFocus() {
	const eventsInFocusRef: RefObject<HTMLDivElement> = useRef(null);

	function getStep(direction: number) {
		const fallback = 400 * direction;

		if (eventsInFocusRef.current) {
			let element: Element = eventsInFocusRef.current.getElementsByClassName("event-card")[0];

			return element ? element.clientWidth * direction : fallback;
		} else {
			return fallback;
		}
	}

	function onNext() {
		if (eventsInFocusRef.current) {
			console.log(getStep(1));
			eventsInFocusRef.current.scrollTo({
				left: eventsInFocusRef.current.scrollLeft + getStep(1),
				behavior: "smooth",
			});
		}
	}

	function onPrevious() {
		if (eventsInFocusRef.current) {
			eventsInFocusRef.current.scrollTo({
				left: eventsInFocusRef.current.scrollLeft + getStep(-1),
				behavior: "smooth",
			});
		}
	}

	return (
		<div id="events-in-focus" className="section">
			<div className="header">
				<h1>Events In Focus</h1>
				<div className="navigation">
					<ArrowLeftOutlined className="navigation-button" onClick={onPrevious} />
					<ArrowRightOutlined className="navigation-button" onClick={onNext} />
				</div>
			</div>
			<div className="events" ref={eventsInFocusRef}>
				{events.map((x, i) => (
					<EventCard
						key={i}
						backgroundUrl={x.backgroundUrl}
						title={x.title}
						price={x.price}
						likeCount={x.likeCount}
						location={x.location}
					/>
				))}
			</div>
		</div>
	);
}
