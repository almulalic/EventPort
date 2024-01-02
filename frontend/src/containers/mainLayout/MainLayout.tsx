import { ConfigProvider, Layout, Menu } from "antd";
import EventSlideshow from "../../components/EventSlideshow/EventSlideshow";
import { EventSection } from "../../components/EventSection/EventSection";
import { HowItWorks } from "../../components/HowItWorks/HowItWorks";
import Navbar from "../Navbar/Navbar";

import "./MainLayout.scss";

const { Content, Footer } = Layout;

const MainLayout: React.FC = () => {
	return (
		<ConfigProvider
			theme={{
				components: {
					Layout: {
						headerHeight: 40,
						headerPadding: "2rem 3.5rem",
					},
				},
			}}
		>
			<Layout className="MainLayout">
				<Navbar />
				<Content className="MainLayout-Content">
					<EventSlideshow />
					<EventSection />
					<HowItWorks />
				</Content>
				<Footer style={{ textAlign: "center" }}>Ant Design ©2023 Created by Ant UED</Footer>
			</Layout>
		</ConfigProvider>
	);
};

export default MainLayout;
