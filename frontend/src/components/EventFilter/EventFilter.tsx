import dayjs from "dayjs";
import { useState } from "react";
import { Form, Cascader, DatePicker } from "antd";

interface LocationCascaderOption {
	value: string | number;
	label: string;
	children?: LocationCascaderOption[];
	disableCheckbox?: boolean;
}

const { RangePicker } = DatePicker;
const DATE_TIME_FORMAT = "YYYY-MM-DD HH";

export default function EventFilter() {
	const [cascaderState, setCascaderState] = useState({
		options: [
			{
				label: "Light",
				value: "light",
				children: new Array(20).fill(null).map((_, index) => ({ label: `Number ${index}`, value: index })),
			},
			{
				label: "Bamboo",
				value: "bamboo",
				children: [
					{
						label: "Little",
						value: "little",
						children: [
							{
								label: "Toy Fish",
								value: "fish",
								disableCheckbox: true,
							},
							{
								label: "Toy Cards",
								value: "cards",
							},
							{
								label: "Toy Bird",
								value: "bird",
							},
						],
					},
				],
			},
		] as LocationCascaderOption[],
	});

	const onCascaderChange = (value: any) => {
		console.log(value);
	};

	const [datePickerState, setDatePickerState] = useState({
		startDate: dayjs().set("hour", 8).set("minute", 0),
		endDate: dayjs().add(1, "day").set("hour", 8).set("minute", 0),
	});

	const onFinish = () => {};

	return (
		<div className="events-filter">
			<h1>Available events</h1>
			<Form
				className="events-filter"
				name="events-filter"
				layout="inline"
				initialValues={{ remember: true }}
				onFinish={onFinish}
				autoComplete="off"
				requiredMark={false}
			>
				<Form.Item label="Showing events in" colon={false}>
					<Cascader
						id="location-cascader"
						className="events-filter-category-cascader"
						options={cascaderState.options}
						onChange={onCascaderChange}
						multiple
						maxTagCount="responsive"
						placeholder="Select location..."
					/>
				</Form.Item>
				<Form.Item label="with category" colon={false}>
					<Cascader
						id="location-cascader"
						className="events-filter-category-cascader"
						options={cascaderState.options}
						onChange={onCascaderChange}
						multiple
						maxTagCount="responsive"
						placeholder="Select category..."
					/>
				</Form.Item>
				<Form.Item label="from" colon={false}>
					<RangePicker
						showTime={{ format: "HH" }}
						format={DATE_TIME_FORMAT}
						defaultValue={[datePickerState.startDate, datePickerState.endDate]}
						allowEmpty={[true, true]}
						separator="to"
						// onChange={onChange}
						// onOk={onOk}
					/>
				</Form.Item>
				<Form.Item label="ordered by" colon={false}>
					<RangePicker
						showTime={{ format: "HH" }}
						format={DATE_TIME_FORMAT}
						defaultValue={[datePickerState.startDate, datePickerState.endDate]}
						allowEmpty={[true, true]}
						// onChange={onChange}
						// onOk={onOk}
					/>
				</Form.Item>
			</Form>
		</div>
	);
}
