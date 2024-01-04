import { axiosApp } from "./Axios";

export class AuthService {
	static async login(email: string, password: string, rememberMe: boolean) {
		axiosApp.post("/api/login", { email: email, password: password, rememberMe: rememberMe });
	}
}
