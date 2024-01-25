import Login from "./pages/Login/Login";
import Events from "./pages/Events/Events";
import SignUp from "./pages/SignUp/SignUp";
import { ConfigProvider, theme } from "antd";
import Landing from "./pages/Landing/Landing";
import { Route, Routes } from "react-router-dom";
import AttendingEvents from "./pages/AttendingEvents/AttendingEvents";
import LikedEvents from "./pages/LikedEvents/LikedEvents";
import ProcessPayment from "./pages/ProcessPayment/ProcessPayment";

function App() {
	return (
		<ConfigProvider
			theme={{
				algorithm: theme.darkAlgorithm,
				token: {
					colorBgMask: "rgba(110, 220, 142, 0.45)",
					colorBgSpotlight: "rgba(211, 122, 213, 0.85)",

					colorPrimary: "#f29727",
					colorPrimaryActive: "#b86e00",
					colorPrimaryBg: "#f29727",
					colorPrimaryBgHover: "#f5e3c6",
					colorPrimaryBorder: "#f9dec6",
					colorPrimaryBorderHover: "#f2c17a",
					colorPrimaryHover: "#e08200",
					colorPrimaryText: "#cc7000",
					colorPrimaryTextActive: "#b86e00",
					colorPrimaryTextHover: "#e08200",

					colorLink: "#f29727",
				},
			}}
		>
			<Routes>
				<Route path="/" element={<Landing />} />
				<Route path="/events" element={<Events />} />
				<Route path="/login" element={<Login />} />
				<Route path="/signup" element={<SignUp />} />
				<Route path="/payment/process" element={<ProcessPayment />} />
				<Route path="/me/attending" element={<AttendingEvents />} />
				<Route path="/me/liked" element={<LikedEvents />} />
			</Routes>
		</ConfigProvider>
	);
}

export default App;
