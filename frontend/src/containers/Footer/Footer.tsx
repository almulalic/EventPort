import { Layout } from "antd";

import "./Footer.scss";
import { FacebookFilled, InstagramFilled, TwitterCircleFilled, YoutubeFilled } from "@ant-design/icons";

export default function Footer() {
	return (
		<Layout.Footer id="footer">
			<div className="footer-row social-media">
				<a href="#">
					<FacebookFilled />
				</a>
				<a href="#">
					<InstagramFilled />
				</a>
				<a href="#">
					<YoutubeFilled />
				</a>
				<a href="#">
					<TwitterCircleFilled />
				</a>
			</div>

			<div className="footer-row links">
				<ul>
					<li>
						<a href="#">Contact us</a>
					</li>
					<li>
						<a href="#">Our Services</a>
					</li>
					<li>
						<a href="#">Privacy Policy</a>
					</li>
					<li>
						<a href="#">Terms & Conditions</a>
					</li>
					<li>
						<a href="#">Career</a>
					</li>
				</ul>
			</div>

			<div className="all-rights-reserved">EventPort Copyright © 2023 Inferno - All rights reserved</div>
		</Layout.Footer>
	);
}
