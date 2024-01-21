package ba.edu.ibu.eventport.api.rest.models.auth;

import ba.edu.ibu.eventport.api.rest.models.auth.enums.AuthType;
import ba.edu.ibu.eventport.api.rest.models.auth.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mongodb.lang.NonNull;
import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", builderMethodName = "Builder", setterPrefix = "with")
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

  public static User fromJwt(Claims claims) {

    User user = new User();
    user.setId(claims.get("id", String.class));
    user.setUsername(claims.get("email", String.class));
    user.setFirstName(claims.get("firstName", String.class));
    user.setLastName(claims.get("lastName", String.class));
    user.setEmail(claims.get("email", String.class));

    return user;
  }
}

