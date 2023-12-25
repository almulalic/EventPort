import { Carousel } from "antd";
import { LandingFilter } from "../LandingFilter/LandingFilter";

import "./EventSlideshow.scss";

const eventPictureUrls = [
	"https://images.pexels.com/photos/1190297/pexels-photo-1190297.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
	"https://teatrocolon.org.ar/sites/default/files/styles/d10/public/teatro-colon-argentina-image_1366_768.jpg?itok=0sbBdjrl",
	"https://picsum.photos/800/400/?image=1015",
	"https://picsum.photos/800/400/?image=1018",
];

const EventSlideshow: React.FC = () => {
	return (
		<div className="EventSlideshow">
			<LandingFilter />

			<Carousel autoplay autoplaySpeed={7000} speed={1500} fade={true} className="EventSlideshow-Carousel">
				{eventPictureUrls.map((url, index) => (
					<div key={index}>
						<div id="overlay"></div>
						<img
							src={url}
							alt={`Event ${index + 1}`}
							style={{ position: "relative", width: "100%", height: "720px" }}
						/>
					</div>
				))}
			</Carousel>
		</div>
	);
};

export default EventSlideshow;
