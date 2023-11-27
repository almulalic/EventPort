package ba.edu.ibu.eventport.api.rest.models.auth;

public class JwtAuthenticationToken {
  private User user;
  private String token;

  public JwtAuthenticationToken() {
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
