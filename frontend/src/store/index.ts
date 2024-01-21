import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./authSlice";
import eventFilterReducer from "./eventFilterSlice";

const store = configureStore({
	reducer: {
		auth: authReducer,
		eventFilter: eventFilterReducer,
	},
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export default store;
