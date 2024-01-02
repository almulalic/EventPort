import { ConfigProvider } from "antd";
import MainLayout from "./containers/MainLayout/MainLayout";

function App() {
	return (
		<ConfigProvider
			theme={{
				token: {
					colorPrimary: "#161616",
					colorPrimaryBgHover: "#313131",
					colorPrimaryBorder: "#313131",
					colorTextBase: "#ffffff",
					colorBgContainer: "#161616",
					colorBorder: "#212121",
					colorBgElevated: "#212121",
				},
			}}
		>
			<MainLayout />
		</ConfigProvider>
	);
}

export default App;
