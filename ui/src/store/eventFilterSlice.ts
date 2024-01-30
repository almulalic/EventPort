import { flatMap } from "lodash";
import { createSlice } from "@reduxjs/toolkit";
import dayjs from "dayjs";
import { cascaderOptionsToURI } from "../utils/utils";

interface OrderDropdownItem<T> {
	value: T;
	label: string;
}

export const ORDER_BY_OPTIONS: OrderDropdownItem<string>[] = [
	{
		value: "1",
		label: "Price (DESC)",
	},
	{
		value: "2",
		label: "Price (ASC)",
	},
	{
		value: "3",
		label: "Likes (DESC)",
	},
	{
		value: "4",
		label: "Likes (ASC)",
	},
];

export const PAGE_SIZE_OPTIONS: OrderDropdownItem<number>[] = [
	{
		value: 10,
		label: "10",
	},
	{
		value: 25,
		label: "25",
	},
	{
		value: 50,
		label: "50",
	},
	{
		value: 100,
		label: "100",
	},
];

export interface EventFilterState {
	isLoading: boolean;
	geolocationCities: any[];
	geolocationCountries: any[];
	geolocationCitiesURI: string;
	geolocationCountriesURI: string;
	categories: any[];
	categoriesURI: string;
	startDate: string;
	endDate: string;
	order: string;
	pageSize: number;
}

const initialState: EventFilterState = {
	isLoading: true,
	geolocationCities: [],
	geolocationCountries: [],
	geolocationCitiesURI: "",
	geolocationCountriesURI: "",
	categories: [],
	categoriesURI: "",
	startDate: dayjs().hour(8).minute(0).toISOString(),
	endDate: dayjs().add(1, "year").toISOString(),
	order: ORDER_BY_OPTIONS[0].value,
	pageSize: PAGE_SIZE_OPTIONS[0].value,
};

const eventFilterSlice = createSlice({
	name: "eventFilter",
	initialState,
	reducers: {
		set_loading: (state) => {
			state.isLoading = true;
		},
		set_loaded: (state) => {
			state.isLoading = false;
		},
		page_size_change: (state, data) => {
			state.pageSize = data.payload;
		},
		geolocation_change: (state, data) => {
			state.geolocationCountries = data.payload.filter((x: any[]) => x.length == 1);
			state.geolocationCountriesURI = cascaderOptionsToURI(state.geolocationCountries);

			state.geolocationCities = data.payload.filter((x: any[]) => x.length > 1);
			state.geolocationCitiesURI = cascaderOptionsToURI(state.geolocationCities);
			console.log(state.geolocationCountries, state.geolocationCities, data.payload);
		},
		category_change: (state, data) => {
			state.categories = data.payload;
			state.categoriesURI = cascaderOptionsToURI(data.payload as any[]);
		},
		category_change_param: (state, data) => {
			state.categoriesURI = data.payload;
		},
		date_range_change: (state, data) => {
			state.startDate = data.payload.startDate;
			state.endDate = data.payload.endDate;
		},
		order_change: (state, data) => {
			state.order = data.payload.order;
		},
	},
});

export const {
	set_loading,
	set_loaded,
	page_size_change,
	geolocation_change,
	category_change,
	category_change_param,
	date_range_change,
	order_change,
} = eventFilterSlice.actions;
export default eventFilterSlice.reducer;
