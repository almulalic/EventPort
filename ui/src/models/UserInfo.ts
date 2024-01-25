export interface UserInfo {
	id: string;
	userType: string;
	authType: string;
	firstName: string;
	lastName: string;
	username: string;
	email: string;
	displayName: string;
	creationDate: string;
	enabled: boolean;
	accountNonExpired: boolean;
	accountNonLocked: boolean;
	credentialsNonExpired: boolean;
	authorities: { authority: string }[];
}
