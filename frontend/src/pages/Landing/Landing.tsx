import Navbar from "../../containers/Navbar/Navbar";
import { About } from "../../containers/About/About";
import Layout, { Content } from "antd/es/layout/layout";
import EventsInFocus from "../../containers/EventsInFocus/EventsInFocus";
import EventSlideshow from "../../containers/EventSlideshow/EventSlideshow";

import "./Landing.scss";
import Footer from "../../containers/Footer/Footer";

export default function Landing() {
	return (
		<Layout id="landing">
			<Navbar />
			<Content id="landing-content">
				<EventSlideshow />
				<EventsInFocus />
				<About />
			</Content>
			<Footer />
		</Layout>
	);
}
