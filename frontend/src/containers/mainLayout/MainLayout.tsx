import { Layout, Menu } from "antd";
import EventSlideshow from "../../components/EventSlideshow/EventSlideshow";
import { EventSection } from "../../components/EventSection/EventSection";
import "./MainLayout.scss";

const { Header, Content, Footer } = Layout;

const items = [
	{
		key: 0,
		label: <a>How it works</a>,
	},
	{
		key: 1,
		label: <a>Events</a>,
	},
	{
		key: 2,
		label: <a>Create Event</a>,
	},
	{
		key: 3,
		label: <a>Log In</a>,
	},
];

const MainLayout: React.FC = () => {
	return (
		<Layout className="MainLayout">
			<Header className="MainLayout-Header">
				<div className="MainLayout-Header-Logo" />
				<Menu
					theme="dark"
					mode="horizontal"
					items={items}
					style={{ flex: items.length, minWidth: 0, justifyContent: "flex-end" }}
				/>
			</Header>
			<Content className="MainLayout-Content">
				<EventSlideshow />
				<EventSection />
			</Content>
			<Footer style={{ textAlign: "center" }}>Ant Design ©2023 Created by Ant UED</Footer>
		</Layout>
	);
};

export default MainLayout;
