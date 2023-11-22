package ba.edu.ibu.eventport.auth.rest.models.dto;


import ba.edu.ibu.eventport.auth.core.model.User;
import ba.edu.ibu.eventport.auth.core.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = false)
public class CreateUserDTO {
  @NotBlank(message = "First Name can not be empty")
  private String firstName;
  @NotBlank(message = "Last mame can not be empty!")
  private String lastName;
  @Email
  @NotBlank(message = "Email can not be empty!")
  private String email;
  @NotBlank(message = "Username can not be empty!")
  private String username;
  @NotBlank(message = "Password can not be empty!")
  private String password;

  public CreateUserDTO() {
  }

  public CreateUserDTO(User user) {
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.email = user.getEmail();
    this.username = user.getUsername();
    this.password = user.getPassword();
  }

  public User toEntity() {
    User user = new User();
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setEmail(email);
    user.setUsername(username);
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