import dayjs from "dayjs";
import Search from "antd/es/input/Search";
import { RangePickerProps } from "antd/es/date-picker";
import { Button, Card, Cascader, DatePicker, DatePickerProps, Flex, Input } from "antd";

import "./LandingFilter.scss";
import { useState } from "react";
import { SearchOutlined } from "@ant-design/icons";

const { RangePicker } = DatePicker;
const DATE_TIME_FORMAT = "YYYY-MM-DD HH";

interface Option {
	value: string | number;
	label: string;
	children?: Option[];
	disableCheckbox?: boolean;
}

export const LandingFilter = () => {
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
		] as Option[],
	});
	const [datePickerState, setDatePickerState] = useState({
		startDate: dayjs().set("hour", 8).set("minute", 0),
		endDate: dayjs().add(1, "day").set("hour", 8).set("minute", 0),
	});

	const onChange = (
		value: DatePickerProps["value"] | RangePickerProps["value"],
		dateString: [string, string] | string
	) => {
		console.log("Selected Time: ", value);
		console.log("Formatted Selected Time: ", dateString);
	};

	const onOk = (value: DatePickerProps["value"] | RangePickerProps["value"]) => {
		console.log("onOk: ", value);
	};

	const onCascaderChange = (value: any) => {
		console.log(value);
	};

	return (
		<div className="LandingFilter">
			<div className="LandingFilter-Card">
				<Flex vertical gap="middle" justify="center" align="start">
					<Input
						className="LandingFilter-Search"
						placeholder="Find events by name, venue, city..."
						size="large"
						allowClear
						prefix={<SearchOutlined />}
					/>

					<Flex gap="middle" justify="center" align="start" style={{ width: "100%" }}>
						<Cascader
							className="CategoryCascader"
							options={cascaderState.options}
							onChange={onCascaderChange}
							multiple
							maxTagCount="responsive"
							placeholder="Select categories..."
						/>

						<RangePicker
							showTime={{ format: "HH" }}
							format={DATE_TIME_FORMAT}
							defaultValue={[datePickerState.startDate, datePickerState.endDate]}
							allowEmpty={[true, true]}
							onChange={onChange}
							onOk={onOk}
						/>
					</Flex>

					<Flex gap="middle" justify="center" align="start" style={{ width: "100%" }}>
						<Button type="primary" shape="default" size="large">
							Search
						</Button>
					</Flex>
				</Flex>
			</div>
		</div>
	);
};
