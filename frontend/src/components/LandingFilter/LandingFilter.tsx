import dayjs from "dayjs";
import { useEffect, useState } from "react";
import { SearchOutlined } from "@ant-design/icons";
import { cascaderOptionsToURI } from "../../utils/utils";
import { Button, Cascader, DatePicker, Flex, Input } from "antd";
import { createSearchParams, useNavigate } from "react-router-dom";

import "./LandingFilter.scss";
import { MetadatService } from "../../services/MetadataService";

const { RangePicker } = DatePicker;
const DATE_TIME_FORMAT = "YYYY-MM-DD HH";

export const LandingFilter = () => {
	const navigate = useNavigate();

	const [isLoading, setLoading] = useState(false);
	const [searchText, setSearchText] = useState("");

	const [availableCategories, setAvailableCategories] = useState({
		options: [],
	});

	const [selectedCategories, setSelectedCategories] = useState([]);

	const [datePickerState, setDatePickerState] = useState({
		startDate: dayjs().hour(8).minute(0),
		endDate: dayjs().add(6, "month"),
	});

	async function loadFilterMetadata() {
		await getCategories();
	}

	useEffect(() => {
		loadFilterMetadata();
	}, []);

	async function getCategories() {
		const categories = await MetadatService.getCategories();

		if (categories.status == 200) {
			setAvailableCategories({
				options: categories.data.map((category: any) => ({
					label: category.name,
					value: category.name,
				})),
			});
		}
	}

	const onSearchChange = (e: any) => {
		setSearchText(e.target.value);
	};

	const onCategoryChange = (_: any, newValue: any) => {
		setSelectedCategories(newValue);
	};

	const onDateRangeChange = (value: any) => {
		if (value) {
			const [startDate, endDate] = value;

			setDatePickerState({
				startDate: startDate,
				endDate: endDate,
			});
		}
	};

	const onFilterSubmit = () => {
		navigate({
			pathname: "/events",
			search: `?${createSearchParams({
				searchText: searchText,
				categories: cascaderOptionsToURI(selectedCategories),
				startDate: datePickerState.startDate.toISOString(),
				endDate: datePickerState.endDate.toISOString(),
			})}`,
		});
	};

	return (
		<div className="LandingFilter">
			<div className="LandingFilter-Card">
				<Flex vertical gap="middle" justify="center" align="start">
					<Input
						className="LandingFilter-Search"
						placeholder="Find events by name, venue, city..."
						size="large"
						value={searchText}
						allowClear
						onChange={onSearchChange}
						prefix={<SearchOutlined />}
						disabled={isLoading}
					/>

					<Flex gap="middle" justify="center" align="start" style={{ width: "100%" }}>
						<Cascader
							className="CategoryCascader"
							options={availableCategories.options}
							onChange={onCategoryChange}
							multiple
							maxTagCount={5}
							placeholder="Select categories..."
							loading={isLoading}
						/>

						<RangePicker
							showTime={{ format: "HH" }}
							format={DATE_TIME_FORMAT}
							defaultValue={[datePickerState.startDate, datePickerState.endDate]}
							allowEmpty={[true, true]}
							separator="to"
							onChange={onDateRangeChange}
						/>
					</Flex>

					<Flex className="search-button-container" gap="middle" justify="center" align="start">
						<Button className="search-button" type="primary" shape="default" onClick={onFilterSubmit}>
							Search
						</Button>
					</Flex>
				</Flex>
			</div>
		</div>
	);
};
