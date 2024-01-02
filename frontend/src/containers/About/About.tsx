import { useState } from "react";
import HowItWorksCard from "./sections/HowItWorksCard";
import CollapsibleSection from "../../components/CollapsibleSection/CollapsibleSection";
import FindEventsSvg from "/Users/admin/Desktop/fagz/Web_Engineering/project/EventPort/frontend/src/assets/svg/undraw_search.svg";
import CreditCardSvg from "/Users/admin/Desktop/fagz/Web_Engineering/project/EventPort/frontend/src/assets/svg/undraw_credit_card.svg";
import UndrawSignUp from "/Users/admin/Desktop/fagz/Web_Engineering/project/EventPort/frontend/src/assets/svg/undraw_sign_up.svg";

import "./About.scss";

export function About() {
	return (
		<div id="about" className="section">
			<h1>About</h1>
			<CollapsibleSection id={0} title="How it works for buyers" initialExpanded={false}>
				<div id="how-it-works-for-buyers">
					<HowItWorksCard
						imgUrl={FindEventsSvg}
						title="Search for Events"
						description="Find exciting concerts, festivals, and parties near you."
					/>
					<HowItWorksCard
						imgUrl={UndrawSignUp}
						title="View Event Details"
						description="Explore event information, including venue, date, and ticket prices."
					/>
					<HowItWorksCard
						imgUrl={CreditCardSvg}
						title="Get your tickets"
						description="Secure your spot at the event by purchasing tickets online."
					/>
				</div>
			</CollapsibleSection>
		</div>
	);
}
