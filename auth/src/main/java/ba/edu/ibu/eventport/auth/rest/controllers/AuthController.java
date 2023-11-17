package ba.edu.ibu.eventport.auth.rest.controllers;

import ba.edu.ibu.eventport.auth.core.service.AuthService;
import ba.edu.ibu.eventport.auth.rest.models.dto.UserRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @RequestMapping(method = RequestMethod.POST, path = "/register")
  public ResponseEntity<UserRequestDTO> register(
    @RequestBody UserRequestDTO dto
  ) {
    return ResponseEntity.ok(this.authService.register(dto));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/token/generate")
  public ResponseEntity<String> generateToken(
    @RequestBody UserRequestDTO dto
  ) {
    return ResponseEntity.ok(this.authService.signIn(dto));
  }

}
