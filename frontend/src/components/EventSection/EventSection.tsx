import dayjs from "dayjs";
import Search from "antd/es/input/Search";
import { RangePickerProps } from "antd/es/date-picker";
import { Card, Cascader, DatePicker, DatePickerProps, Flex, Segmented } from "antd";

import "./EventSection.scss";
import { useState } from "react";
import { FieldTimeOutlined } from "@ant-design/icons";

const { RangePicker } = DatePicker;
const DATE_TIME_FORMAT = "YYYY-MM-DD HH";

interface Option {
	value: string | number;
	label: string;
	children?: Option[];
	disableCheckbox?: boolean;
}

export const EventSection = () => {
	const renderCard = (id: string) => {
		return (
			<Card id={id} className="EventSection-Card">
				<Flex gap="middle" justify="flex-start" align="center">
					<FieldTimeOutlined style={{ fontSize: "72px" }} />
				</Flex>
			</Card>
		);
	};

	return (
		<div className="EventSection">
			<Flex gap="middle" justify="center" align="start" className="EventSection-Flex">
				<Flex vertical gap="middle" justify="center" align="start">
					{[0, 1, 2].map((x) => renderCard(x.toFixed()))}
				</Flex>
				<Flex vertical gap="middle" justify="center" align="start">
					{[3, 4, 5].map((x) => renderCard(x.toFixed()))}
				</Flex>
			</Flex>
		</div>
	);
};
