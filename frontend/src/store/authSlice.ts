import { createSlice } from "@reduxjs/toolkit";
import { AuthService } from "../services/Auth";
import { LoginFormData } from "../pages/Login/Login";

const userToken = localStorage.getItem("userToken") ? localStorage.getItem("userToken") : null;

const initialState = {
	loading: false,
	userInfo: null,
	userToken,
	error: null,
	success: false,
	loginData: {
		email: "",
		password: "",
		rememberMe: false,
	} as LoginFormData,
};

// export const registerUser = createAsyncThunk(
//     'auth/register',
//     async (data: RegisterFormData, { rejectWithValue }) => {
//         try {
//             await appAxios.post(
//                 '/auth/register',
//                 data,
//             )
//         } catch (error: any) {
//             // return custom error message from backend if present
//             if (error.response && error.response.data.message) {
//                 return rejectWithValue(error.response.data.message)
//             } else {
//                 return rejectWithValue(error.message)
//             }
//         }
//     }
// )

const authSlice = createSlice({
	name: "auth",
	initialState,
	reducers: {
		login: (state) => {
			console.log(state);
			state.loading = true;
			AuthService.login(state.loginData.email, state.loginData.password, state.loginData.rememberMe);
			state.loading = false;
		},
		logout: (state) => {
			localStorage.removeItem("userToken");
			state.loading = false;
			state.userInfo = null;
			state.userToken = null;
			state.error = null;
		},
	},
});

export const { login, logout } = authSlice.actions;
export default authSlice.reducer;
