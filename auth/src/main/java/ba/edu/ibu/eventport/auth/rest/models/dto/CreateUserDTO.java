package ba.edu.ibu.eventport.auth.rest.models.dto;


import ba.edu.ibu.eventport.auth.core.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@JsonIgnoreProperties()
public class CreateUserDTO {
  @NotBlank(message = "First Name can not be empty")
  private String firstName;
  @NotBlank(message = "Last mame can not be empty!")
  private String lastName;
  @Email
  @NotBlank(message = "Email can not be empty!")
  private String email;
  @NotBlank(message = "Display name can not be empty!")
  private String displayName;
  @NotBlank(message = "Password can not be empty!")
  private String password;

  public CreateUserDTO() {
  }

  public CreateUserDTO(User user) {
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.email = user.getEmail();
    this.displayName = user.getDisplayName();
    this.password = user.getPassword();
  }

  public User toEntity() {
    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setDisplayName(displayName);
    user.setPassword(password);
    user.setCreationDate(new Date());
    return user;
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

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}