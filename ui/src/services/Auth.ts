import { axiosAuth } from "./Axios";
import { AxiosResponse } from "axios";

export class AuthService {
	static async login(email: string, password: string, rememberMe: boolean): Promise<AxiosResponse> {
		return axiosAuth.post("/auth/token/generate", { email: email, password: password, rememberMe: rememberMe });
	}

	static async signup(
		firstName: string,
		lastName: string,
		displayName: string,
		email: string,
		password: string
	): Promise<AxiosResponse> {
		return axiosAuth.post("/auth/signup", {
			firstName: firstName,
			lastName: lastName,
			email: email,
			displayName: displayName,
			password: password,
		});
	}
}
