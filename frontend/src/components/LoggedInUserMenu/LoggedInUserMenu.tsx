import { useDispatch } from "react-redux";
import { AppDispatch } from "../../store";
import { logout } from "../../store/authSlice";
import { DownOutlined } from "@ant-design/icons";
import { Button, Dropdown, MenuProps, Space } from "antd";

import "./LoggedInUserMenu.scss";

const items: MenuProps["items"] = [
	{
		label: <a href="/me/attending">Attending Events</a>,
		key: "0",
	},
	{
		label: <a href="/me/liked">Liked Events</a>,
		key: "1",
	},
	{
		type: "divider",
	},
	{
		label: (
			<a target="_blank" rel="noopener noreferrer" href="https://www.aliyun.com">
				Create Event
			</a>
		),
		key: "2",
	},
];

export const LoggedInUserMenu = () => {
	const dispatch = useDispatch<AppDispatch>();

	return (
		<div id="logged-in-user-menu">
			<Dropdown className="logged-in-user-action-dropdown" menu={{ items }}>
				<a onClick={(e) => e.preventDefault()}>
					<Space>
						My Events
						<DownOutlined />
					</Space>
				</a>
			</Dropdown>

			<Button className="account-action account-action-login" size="large" onClick={() => dispatch(logout())}>
				Log out
			</Button>
		</div>
	);
};
