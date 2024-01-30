import {} from "../../models/TicketType";
import { Event } from "../../models/Event";
import TextArea from "antd/es/input/TextArea";
import { Button, Cascader, DatePicker, Form, Input, Modal, Select } from "antd";
import { PlusOutlined } from "@ant-design/icons";
import {
	validateCategoriesCascader,
	validateFutureDate,
	validateHasTicker,
	validateURL,
} from "../../pages/ProcessPayment/validators";

import "./CreateEventModal.scss";
import { useEffect, useState } from "react";
import { MetadatService } from "../../services/MetadataService";
import { renderTicketForm } from "./utils";
import dayjs from "dayjs";
import { DATE_TIME_FORMAT } from "../../utils/utils";

const { Option } = Select;

export interface EventModalProps {
	event?: Event;
	isModalOpen: boolean;
	handleCancel: () => any;
}

export default function CreateEventModal({ event, isModalOpen, handleCancel }: EventModalProps) {
	const [isLoading, setLoading] = useState(false);

	const [countriesCascader, setCountriesCascader] = useState({
		options: [],
	});

	const [categoriesCascader, setCategoriesCascader] = useState({
		options: [],
	});

	async function loadFilterMetadata() {
		setLoading(true);
		await getCountries();
		await getCategories();
		setLoading(false);
	}

	async function getCountries() {
		const countries = await MetadatService.getCountires();

		if (countries.status == 200) {
			setCountriesCascader({
				options: countries.data.map((country: any) => ({
					label: country.name,
					value: country.iso2Code,
					children: country.cities.map((city: any) => ({ label: city, value: city })),
				})),
			});
		}
	}

	async function getCategories() {
		const categories = await MetadatService.getCategories();

		if (categories.status == 200) {
			setCategoriesCascader({
				options: categories.data.map((category: any) => ({
					label: category.name,
					value: category.name,
				})),
			});
		}
	}

	useEffect(() => {
		loadFilterMetadata();
	}, []);

	const onFinish = (event: any) => {
		console.log(event);
	};

	return (
		<Modal
			className="create-event-modal"
			wrapClassName="create-event-modal-wrapper"
			title={event ? "Edit Event" : "Create Event"}
			open={isModalOpen}
			onCancel={handleCancel}
			centered
			closable
			footer={null}
		>
			<Form className="create-event-modal-form" layout="vertical" onFinish={onFinish}>
				<hr />
				<h2>What is your event?</h2>
				<div className="create-event-modal-info">
					<div className="create-event-modal-info-name-description">
						<Form.Item label="Event Name" name="name" rules={[{ required: true, message: "Please enter a name!" }]}>
							<Input placeholder="Event" />
						</Form.Item>
						<Form.Item
							label="Category"
							name="category"
							required
							rules={[
								{
									validator: validateCategoriesCascader,
								},
							]}
						>
							<Cascader
								id="category-cascader"
								className="event-filter-cascader"
								options={categoriesCascader.options}
								multiple
								maxTagCount={3}
								placeholder="Select category..."
								loading={isLoading}
								disabled={isLoading}
							/>
						</Form.Item>
						<Form.Item
							label="Description"
							name="description"
							rules={[
								{ required: true, message: "Please enter a description!" },
								{ max: 255, message: "Maximum of 255 charachters is allowed." },
							]}
						>
							<TextArea placeholder="Get ready for an event so spectacular that even your pet rock wouldn't want to miss it..." />
						</Form.Item>
					</div>
					<div className="create-event-modal-info-image">
						<Form.Item
							label="Image URL"
							name="image"
							rules={[{ required: true, message: "Please enter a valid URL!" }, { validator: validateURL }]}
						>
							<Input placeholder="Past or enter image URL" />
						</Form.Item>
						<img src="" className="create-event-modal-info-image-preview" />
					</div>
				</div>
				<hr />
				<h2>When and where is your event?</h2>
				<div className="create-event-modal-location">
					<div className="create-event-modal-location-date">
						<Form.Item
							label="Date and time"
							name="dateTime"
							required
							rules={[
								{
									validator: validateFutureDate,
								},
							]}
						>
							<DatePicker
								showTime={{ format: "HH" }}
								format={DATE_TIME_FORMAT}
								value={dayjs().add(1, "day").hour(8).minute(0)}
							/>
						</Form.Item>
					</div>
					<div className="create-event-modal-location-geoLocation">
						<Form.Item
							label="Location"
							name="location"
							rules={[{ required: true, message: "Please select a location!" }]}
						>
							<Cascader
								id="location-cascader"
								className="event-filter-cascader"
								options={countriesCascader.options}
								maxTagCount={1}
								placeholder="Select location..."
								loading={isLoading}
								disabled={isLoading}
							/>
						</Form.Item>

						<Form.Item label="Venue" name="venue" rules={[{ required: true, message: "Please enter venue name!" }]}>
							<Input placeholder="City Hall" />
						</Form.Item>
						<Form.Item
							label="Google Maps Link"
							name="googleMapsLink"
							rules={[{ required: true, message: "Please enter a valid Google Maps URL!" }, { validator: validateURL }]}
						>
							<Input placeholder="https://maps.google.com/..." />
						</Form.Item>
					</div>
				</div>
				<hr />
				<h2>When kind of tickets do you offer?</h2>
				<Form.List
					name="tickets"
					rules={[
						{
							validator: validateHasTicker,
						},
					]}
				>
					{(fields, { add, remove }, { errors }) => (
						<div className="create-event-modal-tickets">
							{fields.map((field, index) => renderTicketForm(field, add, remove, index))}
							<Form.Item>
								<Button type="dashed" onClick={add} style={{ width: "100%" }} icon={<PlusOutlined />}>
									Add field
								</Button>

								<Form.ErrorList errors={errors} />
							</Form.Item>
						</div>
					)}
				</Form.List>

				<Button block size="large" htmlType="submit">
					Submit
				</Button>
			</Form>
		</Modal>
	);
}
