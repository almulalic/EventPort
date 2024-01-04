import { Button, Flex, Layout, Menu } from "antd";
import "./Navbar.scss";

import React from "react";

const { Header } = Layout;

const items = [
	{
		key: 0,
		label: <a>Concerts</a>,
	},
	{
		key: 1,
		label: <a>Sports</a>,
	},
	{
		key: 2,
		label: <a>Theater</a>,
	},
	{
		key: 3,
		label: <a>Festivals</a>,
	},
	{
		key: 4,
		label: <a>Workshops</a>,
	},
	{
		key: 5,
		label: <a>Exibitions</a>,
	},
];

export default function Navbar() {
	return (
		<header className="navbar">
			<div className="navbar-account-header">
				<div className="navbar-logo">
					<a href="">EventPort</a>
				</div>

				<div className="navbar-account-actions">
					<Button className="account-action account-action-signup" size="large" type="primary">
						Sign Up
					</Button>
					<Button className="account-action account-action-login" size="large">
						Log In
					</Button>
				</div>
			</div>
			<div className="navbar-categories-header">
				<hr className="navbar-hr" />
				<div className="navbar-categories-menu">
					{items.map((x) => (
						<span key={x.key.toString()} className="navbar-categories-menu-item">
							{x.label}
						</span>
					))}
				</div>
				<hr className="navbar-hr" />
			</div>
		</header>
	);
}
