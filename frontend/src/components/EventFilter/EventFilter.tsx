import {
	set_loaded,
	set_loading,
	ORDER_BY_OPTIONS,
	category_change,
	geolocation_change,
	date_range_change,
	PAGE_SIZE_OPTIONS,
	page_size_change,
} from "../../store/eventFilterSlice";
import dayjs from "dayjs";
import { useEffect, useState } from "react";
import { AppDispatch, RootState } from "../../store";
import { useDispatch, useSelector } from "react-redux";
import { Form, Cascader, DatePicker, Select } from "antd";

import "./EventFilter.scss";
import { useSearchParams } from "react-router-dom";
import { MetadatService } from "../../services/MetadataService";
import { DATE_TIME_FORMAT } from "../../utils/utils";

const { RangePicker } = DatePicker;

export default function EventFilter() {
	const dispatch = useDispatch<AppDispatch>();

	const { isLoading, pageSize, startDate, endDate, order } = useSelector((state: RootState) => state.eventFilter);
	const [searchParams] = useSearchParams();

	const [countryCascader, setCountryCascader] = useState({
		options: [],
	});

	const [selectedCategories, setSelectedCategories] = useState([]);
	const [categoriesCascader, setCategoriesCascader] = useState({
		options: [],
	});

	async function loadFilterMetadata() {
		dispatch(set_loading());

		await getCountries();
		await getCategories();

		dispatch(set_loaded());
	}

	useEffect(() => {
		loadFilterMetadata();
	}, []);

	async function getCountries() {
		const countries = await MetadatService.getCountires();

		if (countries.status == 200) {
			setCountryCascader({
				options: countries.data.map((country: any) => ({
					label: country.name,
					value: country.iso2Code,
					children: country.cities.map((city: any) => ({ label: city, value: city })),
				})),
			});

			if (searchParams.has("categories")) {
				if (searchParams.get("categories")!.length > 0) {
					setSelectedCategories(searchParams.get("categories")?.split(",") as any);
				}
			}
		}
	}

	async function getCategories() {
		const categories = await MetadatService.getCategories();

		if (categories.status == 200) {
			setCategoriesCascader({
				options: categories.data.map((category: any) => ({
					label: category.name,
					value: category.name,
				})),
			});
		}
	}

	const handlePageSizeChange = (value: any) => {
		dispatch(page_size_change(value));
	};

	const onLocationChange = (_: any, all: any) => {
		dispatch(geolocation_change(all));
	};

	const onCategoryChange = (value: any, all: any) => {
		setSelectedCategories(value);
		dispatch(category_change(all));
	};

	const handleOrderChange = () => {};

	const onDateRangeChange = (value: any) => {
		if (value) {
			const [startDate, endDate] = value;

			dispatch(
				date_range_change({
					startDate: startDate ? startDate.toISOString() : "",
					endDate: endDate ? endDate.toISOString() : "",
				})
			);
		}
	};

	return (
		<div id="event-filter">
			<Form
				className="event-filter"
				name="event-filter"
				layout="inline"
				initialValues={{ remember: true }}
				autoComplete="off"
				requiredMark={false}
			>
				<Form.Item label="Showing" colon={false}>
					<Select
						className="event-filter-page-size-select"
						defaultValue={pageSize.toString()}
						disabled={isLoading}
						loading={isLoading}
						options={PAGE_SIZE_OPTIONS}
						onChange={handlePageSizeChange}
					/>
				</Form.Item>
				<Form.Item label="events in" colon={false}>
					<Cascader
						id="location-cascader"
						className="event-filter-cascader"
						options={countryCascader.options}
						onChange={onLocationChange}
						multiple
						maxTagCount={5}
						placeholder="Select location..."
						loading={isLoading}
						disabled={isLoading}
					/>
				</Form.Item>

				<Form.Item label="with category" colon={false}>
					<Cascader
						id="category-cascader"
						className="event-filter-cascader"
						options={categoriesCascader.options}
						onChange={onCategoryChange}
						value={selectedCategories}
						multiple
						maxTagCount={5}
						placeholder="Select category..."
						loading={isLoading}
						disabled={isLoading}
					/>
				</Form.Item>
				<Form.Item label="from" colon={false}>
					<RangePicker
						showTime={{ format: "HH" }}
						format={DATE_TIME_FORMAT}
						defaultValue={[
							dayjs(searchParams.get("startDate") || startDate),
							dayjs(searchParams.get("endDate") || endDate),
						]}
						allowEmpty={[true, true]}
						separator="to"
						onChange={onDateRangeChange}
						disabled={isLoading}
					/>
				</Form.Item>
				<Form.Item label="ordered by" colon={false}>
					<Select
						className="event-filter-order-select"
						defaultValue={order}
						disabled={isLoading}
						loading={isLoading}
						options={ORDER_BY_OPTIONS}
						onChange={handleOrderChange}
					/>
				</Form.Item>
			</Form>
		</div>
	);
}
