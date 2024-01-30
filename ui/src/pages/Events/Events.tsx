import { Button, Pagination } from "antd";
import { RootState } from "../../store";
import { useSelector } from "react-redux";
import Search from "antd/es/input/Search";
import Page from "../../components/Page/Page";
import { useSearchParams } from "react-router-dom";
import EventCard from "../../components/EventCard/EventCard";
import { RefObject, useEffect, useRef, useState } from "react";
import EventFilter from "../../components/EventFilter/EventFilter";
import { PublicAPIService } from "../../services/PublicApiService";

import "./Events.scss";
import CreateEventModal from "../../components/CreateEventModal/CreateEventModal";

export default function Events() {
	const eventCardRef: RefObject<HTMLDivElement> = useRef(null);

	const [events, setEvents] = useState([] as any[]);
	const [searchParams] = useSearchParams();

	const [searchText, setSearchText] = useState(searchParams.get("searchText") || "");
	const [isLoading, setLoading] = useState(true);
	const [currentPage, setCurrentPage] = useState(0);
	const [totalResults, setTotalResults] = useState(100);

	const { pageSize, geolocationCountriesURI, geolocationCitiesURI, categoriesURI, startDate, endDate } = useSelector(
		(state: RootState) => state.eventFilter
	);

	async function getEvents() {
		setLoading(true);

		const events = await PublicAPIService.getAll(
			searchText,
			geolocationCountriesURI,
			geolocationCitiesURI,
			categoriesURI,
			startDate,
			endDate,
			currentPage,
			pageSize
		);

		setEvents(events.data.content);
		setTotalResults(events.data.totalElements);

		setLoading(false);
	}

	useEffect(() => {
		getEvents();
	}, [searchText, pageSize, geolocationCountriesURI, geolocationCitiesURI, categoriesURI, startDate, endDate]);

	const onPageChange = (page: any) => {
		setCurrentPage(page);
	};

	const onSearchChange = (e: any) => {
		setSearchText(e.target.value);
	};

	const [isCreateModalVisible, setCreateModalVisible] = useState(false);

	return (
		<Page id="events">
			<div className="events-section">
				<div className="events-section-filter">
					<Button
						className="account-action account-action-login"
						size="large"
						onClick={() => setCreateModalVisible(true)}
					>
						Create Event
					</Button>
					<Search
						className="events-section-filter-search"
						placeholder="Find events by name, venue, city..."
						loading={isLoading}
						size="large"
						onChange={onSearchChange}
						value={searchText}
					/>
					<EventFilter />
				</div>
				<div className="events-wrapper" ref={eventCardRef}>
					{events.map((x, i) => (
						<EventCard key={i} event={x} overview={false} />
					))}
				</div>
				<div className="events-pagination">
					<Pagination
						defaultCurrent={currentPage}
						total={totalResults}
						disabled={isLoading}
						showSizeChanger={false}
						onChange={onPageChange}
					/>
					<div className="events-pagination-total">Total {totalResults} items</div>
				</div>
			</div>
			<CreateEventModal isModalOpen={isCreateModalVisible} handleCancel={() => setCreateModalVisible(false)} />
		</Page>
	);
}
