package ba.edu.ibu.eventport.auth.rest.models.dto;

import ba.edu.ibu.eventport.auth.core.model.User;

public class TokenResponseDTO {
  private User user;
  private String token;

  public TokenResponseDTO() {
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

  public static final class Builder {
    private User user;
    private String token;

    public Builder() {
    }

    public static Builder aTokenResponseDTO() {
      return new Builder();
    }

    public Builder withUser(User user) {
      this.user = user;
      return this;
    }

    public Builder withToken(String token) {
      this.token = token;
      return this;
    }

    public TokenResponseDTO build() {
      TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
      tokenResponseDTO.setUser(user);
      tokenResponseDTO.setToken(token);
      return tokenResponseDTO;
    }
  }
}
