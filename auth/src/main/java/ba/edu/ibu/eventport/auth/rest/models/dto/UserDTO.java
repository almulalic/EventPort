package ba.edu.ibu.eventport.auth.rest.models.dto;

import ba.edu.ibu.eventport.auth.core.model.User;
import ba.edu.ibu.eventport.auth.core.model.enums.AuthType;
import ba.edu.ibu.eventport.auth.core.model.enums.UserType;

import java.util.Date;

public class UserDTO {
  private String id;
  private UserType userType;
  private AuthType authType;
  private String name;
  private String email;
  private String displayName;
  private Date creationDate;

  public UserDTO(User user) {
    this.id = user.getId();
    this.name = user.getFirstName() + " " + user.getLastName();
    this.displayName = user.getDisplayName();
    this.userType = user.getUserType();
    this.authType = user.getAuthType();
    this.email = user.getEmail();
    this.creationDate = user.getCreationDate();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }

  public AuthType getAuthType() {
    return authType;
  }

  public void setAuthType(AuthType authType) {
    this.authType = authType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }
}
