package ba.edu.ibu.eventport.rest.models.dto;

import ba.edu.ibu.eventport.core.model.User;
import ba.edu.ibu.eventport.core.model.enums.AuthType;
import ba.edu.ibu.eventport.core.model.enums.UserType;

import java.util.Date;

public class UserRequestDTO {
  private UserType userType;
  private AuthType authType;
  private String firstName;
  private String lastName;
  private String organization;
  private String email;
  private String username;
  private String password;

  public UserRequestDTO() {
  }

  public UserRequestDTO(User user) {
    this.userType = user.getUserType();
    this.authType = user.getAuthType();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.organization = user.getOrganization();
    this.email = user.getEmail();
    this.username = user.getUsername();
    this.password = user.getPassword();
  }

  public User toEntity() {
    User user = new User();
    user.setUserType(userType);
    user.setAuthType(authType);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setOrganization(organization);
    user.setEmail(email);
    user.setUsername(username);
    user.setPassword(password);
    user.setCreationDate(new Date());
    return user;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}