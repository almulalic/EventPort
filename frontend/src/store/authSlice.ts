import { UserInfo } from "../models/UserInfo";
import { createSlice } from "@reduxjs/toolkit";
import { LoginFormData } from "../pages/Login/Login";

export interface AuthState {
	loading: boolean;
	userInfo: UserInfo | null;
	isLoggedIn: boolean;
	userToken: string | null;
	error: any;
	success: boolean;
	loginData: LoginFormData;
}

const initialState: AuthState = {
	loading: false,
	userInfo: localStorage.getItem("userInfo") ? JSON.parse(localStorage.getItem("userInfo") || "") : null,
	isLoggedIn: localStorage.getItem("userToken") ? true : false,
	userToken: localStorage.getItem("userToken") ? localStorage.getItem("userToken") : null,
	error: null,
	success: false,
	loginData: {
		email: "",
		password: "",
		rememberMe: false,
	},
};

const authSlice = createSlice({
	name: "auth",
	initialState,
	reducers: {
		login_attempt: (state) => {
			state.loading = true;
		},
		login_failed: (state) => {
			state.loading = false;
		},
		login_sucessfull: (state, data) => {
			state.loading = false;
			state.isLoggedIn = true;
			state.userInfo = data.payload.user;
			state.userToken = data.payload.token;
			localStorage.setItem("userToken", data.payload.token);
			localStorage.setItem("userInfo", JSON.stringify(data.payload.user));
		},
		logout: (state) => {
			localStorage.removeItem("userToken");
			state.loading = false;
			state.isLoggedIn = false;
			state.userInfo = null;
			state.userToken = null;
			state.error = null;
		},
	},
});

export const { login_attempt, login_failed, login_sucessfull, logout } = authSlice.actions;
export default authSlice.reducer;
