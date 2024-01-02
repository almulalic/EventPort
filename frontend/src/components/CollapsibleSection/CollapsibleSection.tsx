import { ReactNode, useEffect, useRef, useState } from "react";
import { ArrowDownOutlined, ArrowUpOutlined } from "@ant-design/icons";

import "./CollapsibleSection.scss";

export interface CollapsibleSectionProps {
	id: number;
	title: string;
	children: ReactNode;
	initialExpanded: boolean;
}

export default function CollapsibleSection(props: CollapsibleSectionProps) {
	const contentRef = useRef<HTMLDivElement>(null);
	const [isExpanded, expandSection] = useState(props.initialExpanded);
	const [contentHeight, setContentHeight] = useState(0);

	useEffect(() => {
		if (contentRef.current) {
			console.log(contentRef.current.clientHeight);
			setContentHeight(contentRef.current.clientHeight);
		}
	}, [isExpanded, props.children]);

	return (
		<div id="collapsible-section">
			<div className="collapisble-section-header">
				<span className="collapsible-section-title">{props.title}</span>
				{isExpanded ? (
					<ArrowUpOutlined className="collapsible-section-arrow" onClick={() => expandSection(false)} />
				) : (
					<ArrowDownOutlined className="collapsible-section-arrow" onClick={() => expandSection(true)} />
				)}
			</div>
			<div
				className={`collapisble-section-content ${isExpanded ? "collapsible-section-content-expanded" : ""}`}
				style={{ height: isExpanded ? contentHeight : "0px" }}
			>
				<div ref={contentRef}>{props.children}</div>
			</div>
			AAAA
		</div>
	);
}
