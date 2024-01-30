import { AxiosResponse } from "axios";
import { publicAxiosApp } from "./Axios";

export class PublicAPIService {
	static async getAll(
		searchText: string,
		geolocationCountriesURI: string,
		geolocationCitiesURI: string,
		categoriesURI: string,
		startDate: string,
		endDate: string,
		currentPage: number,
		pageSize: number
	): Promise<AxiosResponse> {
		return publicAxiosApp.get(`/event?
    searchText=${searchText}&
    geoLocationCountries=${geolocationCountriesURI}&
    geoLocationCities=${geolocationCitiesURI}&
    categories=${categoriesURI}&
    startDate=${startDate}&
    endDate=${endDate}&
    page=${currentPage}&
    size=${pageSize}`);
	}
}
