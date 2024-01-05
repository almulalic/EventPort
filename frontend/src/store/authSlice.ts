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
		login_attempt: (state) => {
			state.loading = true;
		},
		login_failed: (state) => {
			state.loading = false;
		},
		login_sucessfull: (state, data) => {
			state.loading = false;
			state.userInfo = data.payload.user;
			state.userToken = data.payload.token;
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

export const { login_attempt, login_failed, login_sucessfull, logout } = authSlice.actions;
export default authSlice.reducer;
