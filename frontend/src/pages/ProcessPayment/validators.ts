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

export const validateFutureDate = (rule: any, value: any) => {
	if (value && value.isBefore(DateTime.now(), "month")) {
		return Promise.reject("Expiration date must be in the future");
	}

	return Promise.resolve();
};
