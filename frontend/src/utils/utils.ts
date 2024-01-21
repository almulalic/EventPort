import { flatMap } from "lodash";

export const DATE_TIME_FORMAT = "YYYY-MM-DD HH";

export function cascaderOptionsToURI(arr: any[]) {
	return flatMap(arr)
		.map((x) => x.value)
		.join(",");
}

export function getRandomNumber(min: number, max: number) {
	return Math.floor(Math.random() * (max - min + 1)) + min;
}
