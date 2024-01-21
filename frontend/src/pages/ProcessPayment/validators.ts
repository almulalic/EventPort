import { DateTime } from "luxon";

export const validateCreditCardNumber = (rule: any, value: any) => {
	const cleanedNumber = value.replace(/\D/g, "");

	const digits = Array.from(cleanedNumber, Number);

	const reversedDigits = digits.reverse();

	const doubledDigits = reversedDigits.map((digit, index) => (index % 2 === 1 ? digit * 2 : digit));

	const summedDigits = doubledDigits.map((digit) => (digit > 9 ? digit - 9 : digit));

	const sum = summedDigits.reduce((acc, digit) => acc + digit, 0);

	const isValid = sum % 10 === 0;

	if (!isValid) {
		return Promise.reject("Invalid credit card number");
	}

	return Promise.resolve();
};

export const validateFutureDate = (_: any, value: any) => {
	if (value && value.isBefore(DateTime.now(), "second")) {
		return Promise.reject("Expiration date must be in the future");
	}

	return Promise.resolve();
};

export const validateHasTicker = async (_: any, names: any) => {
	if (!names || names.length < 1) {
		return Promise.reject(new Error("At least 1 ticket type"));
	}
};

export const validateURL = (_: any, value: any) => {
	if (value && !/^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/.test(value)) {
		return Promise.reject("Invalid URL");
	}
	return Promise.resolve();
};

export const validateCategoriesCascader = (_: any, value: any) => {
	console.log(value);

	if (!value || value.length === 0) {
		return Promise.reject("Please select at least one category!");
	} else if (value.length > 3) {
		return Promise.reject("You can select at most 3 categories!");
	}

	return Promise.resolve();
};
