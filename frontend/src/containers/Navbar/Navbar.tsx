import { Button, Layout } from "antd";
import { AppDispatch, RootState } from "../../store";
import { useDispatch, useSelector } from "react-redux";
import { createSearchParams, useNavigate } from "react-router-dom";
import { logout } from "../../store/authSlice";

import "./Navbar.scss";

const items = [
	{
		key: 0,
		label: "Concerts",
	},
	{
		key: 1,
		label: "Sports",
	},
	{
		key: 2,
		label: "Theater",
	},
	{
		key: 3,
		label: "Festivals",
	},
	{
		key: 4,
		label: "Workshops",
	},
	{
		key: 5,
		label: "Exibitions",
	},
];

export default function Navbar() {
	const navigate = useNavigate();

	const { userInfo } = useSelector((state: RootState) => state.auth);
	const dispatch = useDispatch<AppDispatch>();

	return (
		<header className="navbar">
			<div className="navbar-account-header">
				<div className="navbar-main-menu">
					<span className="navbar-main-menu-logo" onClick={() => navigate("/")}>
						EVENTPORT
					</span>

					<div className="navbar-main-menu-container">
						<span className="navbar-main-menu-item" onClick={() => navigate("/events")}>
							Events
						</span>
					</div>
				</div>

				<div className="navbar-account-actions">
					{userInfo ? (
						<Button className="account-action account-action-login" size="large" onClick={() => dispatch(logout())}>
							Log out
						</Button>
					) : (
						<>
							<Button
								className="account-action account-action-signup"
								size="large"
								type="primary"
								onClick={() => navigate("/signup")}
							>
								Sign Up
							</Button>
							<Button className="account-action account-action-login" size="large" onClick={() => navigate("/login")}>
								Log In
							</Button>
						</>
					)}
				</div>
			</div>
			<div className="navbar-categories-header">
				<hr className="navbar-hr" />
				<div className="navbar-categories-menu">
					{items.map((x) => (
						<span
							key={x.key.toString()}
							className="navbar-categories-menu-item"
							onClick={() =>
								navigate({ pathname: "/events", search: `?${createSearchParams({ category: x.label.toLowerCase() })}` })
							}
						>
							{x.label}
						</span>
					))}
				</div>
				<hr className="navbar-hr" />
			</div>
		</header>
	);
}
