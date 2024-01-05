import { Cascader } from "antd";
import { useState } from "react";

export interface LocationCascaderOption {
	value: string | number;
	label: string;
	children?: LocationCascaderOption[];
	disableCheckbox?: boolean;
}

export default function LocationCascader() {
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

	return (
		<Cascader
			id="location-cascader"
			className="CategoryCascader"
			options={cascaderState.options}
			onChange={onCascaderChange}
			multiple
			maxTagCount="responsive"
			placeholder="Select location..."
		/>
	);
}
