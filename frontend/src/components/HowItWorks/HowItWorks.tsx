import React, { useState } from "react";
import { Button, Steps, message } from "antd";

import "./HowItWorks.scss";
import { ArrowLeftOutlined, ArrowRightOutlined } from "@ant-design/icons";

const steps = [
	{
		title: "Find Event",
		content: "First-content",
	},
	{
		title: "Fill Out The Form",
		content: "Second-content",
	},
	{
		title: "Proceed To Payment",
		content: "Last-content",
	},
	{
		title: "Get Your Tickets!",
		content: "Last-content",
	},
];

export function HowItWorks() {
	const [current, setCurrent] = useState(0);

	const next = () => {
		setCurrent(current + 1);
	};

	const prev = () => {
		setCurrent(current - 1);
	};

	const items = steps.map((item) => ({ key: item.title, title: item.title }));

	const contentStyle: React.CSSProperties = {
		lineHeight: "260px",
		textAlign: "center",
		marginTop: 16,
	};

	return (
		<div className="HowItWorks-Wrapper">
			<Steps current={current} items={items} />
			<div style={contentStyle}>{steps[current].content}</div>
			<div className="HowItWorks-Navigation">
				{current < steps.length - 1 && (
					<ArrowRightOutlined
						className="HowItWorks-Navigation-Button HowItWorks-Navigation-Button-Next"
						type="primary"
						onClick={() => next()}
					/>
				)}
				{current === steps.length - 1 && (
					<ArrowRightOutlined
						className="HowItWorks-Navigation-Button HowItWorks-Navigation-Button-Next"
						type="primary"
						onClick={() => message.success("Processing complete!")}
					/>
				)}
				{current > 0 && (
					<ArrowLeftOutlined
						className="HowItWorks-Navigation-Button HowItWorks-Navigation-Button-Previous"
						type="primary"
						onClick={() => prev()}
					/>
				)}
			</div>
		</div>
	);
}
