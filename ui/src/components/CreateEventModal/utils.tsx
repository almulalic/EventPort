import { MinusCircleOutlined } from "@ant-design/icons";
import { Form, Input, InputNumber, Select } from "antd";

const { Option } = Select;

export const selectAfter = (
	<Select defaultValue="USD">
		<Option value="USD">USD</Option>
		<Option value="EUR">EUR</Option>
		<Option value="BAM">BAM</Option>
		<Option value="RSD">RSD</Option>
	</Select>
);

export const renderTicketForm = (field: any, add: any, remove: any, index: any) => {
	return (
		<Form.Item
			label={
				<span className="create-event-modal-tickets-ticket-label">
					Ticket Type
					<span className="create-event-modal-tickets-ticket-delete">
						<MinusCircleOutlined className="dynamic-delete-button" onClick={() => remove(field.name)} />
					</span>
				</span>
			}
			required={false}
			key={field.key}
		>
			<Form.Item
				{...field}
				validateTrigger={["onChange", "onBlur"]}
				rules={[
					{
						required: true,
						whitespace: true,
						message: "Please input ticket name.",
					},
				]}
				name={["first"]}
				noStyle
			>
				<Input placeholder="Name" />
			</Form.Item>
			<Form.Item
				{...field}
				validateTrigger={["onChange", "onBlur"]}
				rules={[
					{
						required: true,
						whitespace: true,
						message: "Please input ticket description.",
					},
				]}
				noStyle
			>
				<Input placeholder="Description" />
			</Form.Item>

			<Form.Item
				{...field}
				validateTrigger={["onChange", "onBlur"]}
				rules={[
					{
						required: true,
						whitespace: true,
						message: "Please input ticket price currency!",
					},
				]}
				noStyle
			>
				<InputNumber addonAfter={selectAfter} defaultValue={100} />
			</Form.Item>
		</Form.Item>
	);
};
