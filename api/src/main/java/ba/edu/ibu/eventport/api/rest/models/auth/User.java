package ba.edu.ibu.eventport.api.rest.models.auth;

import ba.edu.ibu.eventport.api.rest.models.auth.enums.AuthType;
import ba.edu.ibu.eventport.api.rest.models.auth.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document
public class User implements UserDetails {
  private String id;
  private UserType userType;
  private AuthType authType;
  private String firstName;
  private String lastName;
  private String email;
  private String username;
  @NotEmpty
  @JsonIgnore
  private String password;
  private Date creationDate;

  public User() {
  }

  public User(
    String id,
    UserType userType,
    AuthType authType,
    String firstName,
    String lastName,
    String email,
    String username,
    String password,
    Date creationDate
  ) {
    this.id = id;
    this.userType = userType;
    this.authType = authType;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
    this.creationDate = creationDate;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(userType.name()));
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

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }
}

