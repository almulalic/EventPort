package ba.edu.ibu.eventport.auth.rest.controllers;

import ba.edu.ibu.eventport.auth.core.model.User;
import ba.edu.ibu.eventport.auth.core.service.AuthService;
import ba.edu.ibu.eventport.auth.rest.models.dto.CreateUserDTO;
import ba.edu.ibu.eventport.auth.rest.models.dto.GenerateTokenDTO;
import ba.edu.ibu.eventport.auth.rest.models.dto.TokenResponseDTO;
import jakarta.validation.Valid;
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

  @RequestMapping(method = RequestMethod.POST, path = "/signup")
  public ResponseEntity<User>   createUser(
    @Valid
    @RequestBody
    CreateUserDTO dto
  ) {
    return ResponseEntity.ok(this.authService.createUser(dto));
  }

  @RequestMapping(method = RequestMethod.POST, path = "/token/generate")
  public ResponseEntity<TokenResponseDTO> generateToken(
    @Valid
    @RequestBody
    GenerateTokenDTO dto
  ) {
    return ResponseEntity.ok(this.authService.generateToken(dto));
  }
}
