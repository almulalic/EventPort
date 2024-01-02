import { Layout } from "antd";
import Navbar from "../Navbar/Navbar";
import { About } from "../About/About";
import Footer from "../Footer/Footer";
import EventsInFocus from "../EventsInFocus/EventsInFocus";
import EventSlideshow from "../EventSlideshow/EventSlideshow";

import "./MainLayout.scss";

const { Content } = Layout;

const MainLayout: React.FC = () => {
	return (
		<Layout className="MainLayout">
			<Navbar />
			<Content className="MainLayout-Content">
				<EventSlideshow />
				<EventsInFocus />
				<About />
			</Content>
			<Footer />
		</Layout>
	);
};

export default MainLayout;
