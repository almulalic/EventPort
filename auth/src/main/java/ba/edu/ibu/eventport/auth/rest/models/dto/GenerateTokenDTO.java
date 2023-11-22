package ba.edu.ibu.eventport.auth.rest.models.dto;

public class GenerateTokenDTO {
  private String email;
  private String password;

  public GenerateTokenDTO() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
