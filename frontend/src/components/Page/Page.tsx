import { ReactNode } from "react";
import Navbar from "../../containers/Navbar/Navbar";
import Footer from "../../containers/Footer/Footer";

import "./Page.scss";

export interface PageProps {
	id: string;
	className?: string;
	children: ReactNode;
}

export default function Page({ id, className, children }: PageProps) {
	return (
		<div id={id} className={className}>
			<Navbar />
			<div className="page-content">{children}</div>
			<Footer />
		</div>
	);
}
