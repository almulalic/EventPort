import { message } from "antd";
import { useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import Navbar from "../../containers/Navbar/Navbar";
import Footer from "../../containers/Footer/Footer";
import { About } from "../../containers/About/About";
import Layout, { Content } from "antd/es/layout/layout";
import EventsInFocus from "../../containers/EventsInFocus/EventsInFocus";
import EventSlideshow from "../../containers/EventSlideshow/EventSlideshow";

import "./Landing.scss";

export default function Landing() {
	const [searchParams] = useSearchParams();
	const [messageApi, contextHolder] = message.useMessage();

	useEffect(() => {
		if (searchParams.has("messageType") && searchParams.has("message")) {
			const type = searchParams.get("messageType");
			const message = searchParams.get("message");

			switch (type) {
				case "error":
					messageApi.error(message);
					return;
				case "info":
					messageApi.info(message);
					return;
				case "warning":
					messageApi.warning(message);
					return;
				case "success":
					messageApi.success(message);
					return;
			}
		}
	}, []);

	return (
		<Layout id="landing">
			<Navbar />
			<Content id="landing-content">
				<EventSlideshow />
				<EventsInFocus />
				<About />
			</Content>
			<Footer />
			{contextHolder}
		</Layout>
	);
}
